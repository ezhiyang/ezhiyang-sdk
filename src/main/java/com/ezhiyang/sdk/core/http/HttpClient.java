package com.ezhiyang.sdk.core.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ezhiyang.sdk.core.model.RequestWrapper;
import com.ezhiyang.sdk.core.model.RequestWrapper.HttpBodyType;
import com.ezhiyang.sdk.core.model.RequestWrapper.HttpMethodName;
import com.ezhiyang.sdk.core.model.ResponseWrapper;
import com.ezhiyang.sdk.util.JsonUtils;
import com.ezhiyang.sdk.util.VelocityUtils;
/**
 * http client util
 * @author Theo Zhou
 *
 */
public enum HttpClient{
  /**
   * instance
   */
  INSTANCE;
  
  public static HttpClient getInstance() {
    return INSTANCE;
  }

  private Logger logger = LoggerFactory.getLogger(HttpClient.class);

  /**
   * execute http request
   * @param requestWrapper request  
   * @return response  
   */
  public ResponseWrapper execute(RequestWrapper requestWrapper,Integer connectTimeout,Integer socketTimeout) {
    return execute(requestWrapper, null,connectTimeout,socketTimeout);
  }

  /**
   * execute http reuqest
   * @param requestWrapper request  
   * @param responseWrapper response  
   * @return  response  RequestWrapper
   */
  public ResponseWrapper execute(RequestWrapper requestWrapper, ResponseWrapper responseWrapper,Integer connectTimeout,Integer socketTimeout) {
    logger.info("\n\nSend Request:\n{}", requestWrapper);

    // get uri
    String uri = requestWrapper.getUrl();
    uri = VelocityUtils.parseString(uri, requestWrapper.getBody());

    logger.info("\nuri:\t{}", uri);

    // 根据参数创建相应的请求 GET/POST/PUT/DELETE
    Request request = createRequest(requestWrapper.getMethodName(), uri);

    // 将相应的Header添加到请求
    Map<String, String> headers = requestWrapper.getHeaders();
    for(Entry<String, String> head : headers.entrySet()) {
      request.addHeader(head.getKey(), head.getValue());
    }

    // build body
    if (!HttpMethodName.GET.equals(requestWrapper.getMethodName())) {
      buildBody(request, requestWrapper.getBody(), requestWrapper.getBodyType());
    }

    String content = null;
    int statusCode = 0;
    try {
      // 发送请求
      // Response response = request.execute();
      Executor executor = Executor.newInstance(getHttpClient(connectTimeout,socketTimeout));
      Response response = executor.execute(request);
      // 获取返回结果
      HttpResponse httpResponse = response.returnResponse();

      HttpEntity httpEntity = httpResponse.getEntity();

      ContentType contentType = ContentType.get(httpEntity);
      Charset charset = contentType.getCharset();

      // 返回值，转化成字符串
      content = IOUtils.toString(httpResponse.getEntity().getContent(), charset);
      logger.info("\n\\n\nXXX Request: {}",request.toString());
      logger.info("\n\n\nXXX Response:\nuri:{}:content:{}\n\n\n", uri, content);

      // HTTP相应值
      statusCode = httpResponse.getStatusLine().getStatusCode();
      
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // 尝试将返回结果以JSON形式转化成Map
    Map<String, Object> result = null;
    try {
      result = JsonUtils.toMap(content);
    } catch (Exception e) {
      result = new HashMap<String, Object>();
      result.put("result", content);
    }
    if (responseWrapper == null) {
      responseWrapper = new ResponseWrapper();
    }
    responseWrapper.setHttpCode(statusCode);
    responseWrapper.setBody(result);

    return responseWrapper;
  }

  /**
   * 根据请求类型创建 request
   * 
   * @param methodName 请求类型
   * @param uri 请求的 uri
   * @return
   */
  private Request createRequest(HttpMethodName methodName, String uri) {
    switch (methodName) {
    case GET: return Request.Get(uri);
    case PUT: return Request.Put(uri);
    case DELETE: return Request.Delete(uri);
    default: return Request.Post(uri);
    }
  }

  /**
   * 构建请求的 body
   * 
   * @param request 请求对象
   * @param body 参数
   */
  private Request buildBody(Request request, Map<String, Object> body, HttpBodyType bodyType) {
    IRequestBodyWrapper requestWrapper = RequestBodyWrapperFactory.getWrapper(bodyType);
    return requestWrapper.wrapRequestBody(request, body);
  }

  

  public static CloseableHttpClient getHttpClient(Integer connectTimeout,Integer socketTimeout) {
    SSLContextBuilder builder = new SSLContextBuilder();
    try {
      builder.loadTrustMaterial(null, new TrustStrategy() {

        @Override
        public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
            throws CertificateException {
          return true;
        }
      });
    } catch (NoSuchAlgorithmException | KeyStoreException e) {
      throw new RuntimeException(e);
    }
    SSLConnectionSocketFactory sslsf = null;
    try {
      sslsf = new SSLConnectionSocketFactory(builder.build(), new NoopHostnameVerifier());
    } catch (KeyManagementException | NoSuchAlgorithmException e1) {
      e1.printStackTrace();
    }
    RequestConfig requestConfig = RequestConfig.custom()  
        // 设置连接超时时间
        .setConnectTimeout(connectTimeout) 
        // 请求获取数据的超时时间(即响应时间)
        .setSocketTimeout(socketTimeout).build(); 
    return HttpClients.custom().setDefaultRequestConfig(requestConfig).setSSLSocketFactory(sslsf).build();
  }

}
