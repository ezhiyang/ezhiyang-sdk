package com.ezhiyang.sdk.core.http;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ezhiyang.sdk.core.exception.SdkException;
import com.ezhiyang.sdk.core.model.RequestWrapper.HttpBodyType;
import com.ezhiyang.sdk.util.StringUtils;

/**
 * form request body wrapper
 * @author Theo Zhou
 *
 */
public class FormRequestBodyWrapper implements IRequestBodyWrapper{
  
  
  
  private Logger logger = LoggerFactory.getLogger(FormRequestBodyWrapper.class);
  
  private static final String CHAR_SET = "UTF-8";
  
  private FormRequestBodyWrapper() {};
  
  private static IRequestBodyWrapper instance = new FormRequestBodyWrapper();
  
  public static IRequestBodyWrapper getInstance() {
    return instance;
  }

  @Override
  public Request wrapRequestBody(Request request, Map<String, Object> body) {
      Collection<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
      try {
        for (Entry<String, Object> entry : body.entrySet()) {
          String key = entry.getKey();
          Object value = entry.getValue();
          if(StringUtils.isNotBlank(key)) {
            key = URLEncoder.encode(key, CHAR_SET);
          }
          if(Objects.nonNull(value) && StringUtils.isNotBlank(value.toString())) {
            value = URLEncoder.encode(value.toString(), CHAR_SET);
          }
          NameValuePair nameValuePair = new BasicNameValuePair(key,value.toString());
          nameValuePairs.add(nameValuePair);
        }
        logger.info("\nBody:\n{}\n\n\n", nameValuePairs);
      } catch (Exception e) {
        throw new SdkException("form request wrapper failed",e);
      }
      return request.bodyForm(nameValuePairs, Charset.forName("utf-8"));
  }

  @Override
  public HttpBodyType getSupport() {
    return HttpBodyType.FORM;
  }

}
