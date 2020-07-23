
package com.ezhiyang.sdk.core.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * request wraper
 * @author Theo Zhou
 *
 */
public class RequestWrapper implements Serializable{
  
  private static final long serialVersionUID = -5201314L;
  private String url;
  private HttpMethodName methodName = HttpMethodName.POST;
  private HttpBodyType bodyType = HttpBodyType.JSON;
  private Map<String, String> headers = new HashMap<String, String>(10);
  private Map<String, Object> body = new HashMap<String, Object>(20);
  
  /**
   * http method enum
   * @author Theo Zhou
   *
   */
  public static enum HttpMethodName{
    /**
     * get
     */
    GET,
    /**
     * post
     */
    POST,
    /**
     * put
     */
    PUT,
    /**
     * delete
     */
    DELETE;
  }
  
  /**
   * http body type FORM/JSON
   * @author Theo Zhou
   *
   */
  public static enum HttpBodyType{
    /**
     * form
     */
    FORM,
    /**
     * json
     */
    JSON
  }
  
  public RequestWrapper addHeader(String key,String value) {
    headers.put(key, value);
    return this;
  }
  
  public RequestWrapper addBody(String key,Object value) {
    body.put(key, value);
    return this;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public HttpMethodName getMethodName() {
    return methodName;
  }

  public void setMethodName(HttpMethodName methodName) {
    this.methodName = methodName;
  }

  public HttpBodyType getBodyType() {
    return bodyType;
  }

  public void setBodyType(HttpBodyType bodyType) {
    this.bodyType = bodyType;
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
    return "RequestWrapper [url=" + url + ", methodName=" + methodName + ", bodyType=" + bodyType + ", headers="
        + headers + ", body=" + body + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((body == null) ? 0 : body.hashCode());
    result = prime * result + ((bodyType == null) ? 0 : bodyType.hashCode());
    result = prime * result + ((headers == null) ? 0 : headers.hashCode());
    result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
    result = prime * result + ((url == null) ? 0 : url.hashCode());
    return result;
  }
  
  @Override
  protected void finalize() throws Throwable {
    headers.clear();
    body.clear();
    headers = null;
    body = null;
  }
  
}
