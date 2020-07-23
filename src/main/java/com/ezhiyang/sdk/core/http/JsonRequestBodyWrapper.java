package com.ezhiyang.sdk.core.http;

import java.util.Map;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ezhiyang.sdk.core.model.RequestWrapper.HttpBodyType;
import com.ezhiyang.sdk.util.JsonUtils;

/**
 * json request body wrapper implements
 * @author Theo Zhou
 *
 */
public class JsonRequestBodyWrapper implements IRequestBodyWrapper{
  
  private Logger logger = LoggerFactory.getLogger(JsonRequestBodyWrapper.class);
  
  private JsonRequestBodyWrapper() {};
  
  private static IRequestBodyWrapper instance = new JsonRequestBodyWrapper();
  
  public static IRequestBodyWrapper getInstance() {
    return instance;
  }
  
  @Override
  public Request wrapRequestBody(Request request, Map<String, Object> body) {
    String jsonContent = null;
    jsonContent = JsonUtils.toJson(body);
    logger.info("\nBody:\n{}\n\n\n", jsonContent);
    return request.bodyString(jsonContent, ContentType.APPLICATION_JSON);
  }

  @Override
  public HttpBodyType getSupport() {
    return HttpBodyType.JSON;
  }

  
  
}
