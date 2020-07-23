package com.ezhiyang.sdk.core.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * response wrapper 
 * @author Theo Zhou
 *
 */
public class ResponseWrapper implements Serializable{

  private static final long serialVersionUID = -5201315L;
  private Map<String, String> headers = new HashMap<String, String>(10);
  private Map<String, Object> body = new HashMap<String, Object>();
  private int httpCode = 200;
  

  public int getHttpCode() {
    return httpCode;
  }

  public void setHttpCode(int httpCode) {
    this.httpCode = httpCode;
  }

  public void addHeader(String key, String value) {
    headers.put(key, value);
  }
  
  public void addBody(String key, Object value) {
    body.put(key, value);
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public Map<String, Object> getBody() {
    return body;
  }

  public void setBody(Map<String, Object> body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return "ResponseWrapper [headers=" + headers + ", body=" + body + "]";
  }
  
  @Override
  protected void finalize() throws Throwable {
    headers.clear();
    body.clear();
    headers = null;
    body = null;
  }

  
}
