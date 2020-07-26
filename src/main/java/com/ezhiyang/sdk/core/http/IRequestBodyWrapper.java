package com.ezhiyang.sdk.core.http;

import java.util.Map;

import org.apache.http.client.fluent.Request;

import com.ezhiyang.sdk.core.model.RequestWrapper.HttpBodyType;

/**
 * request body wrapper interface
 * @author Theo Zhou
 *
 */
public interface IRequestBodyWrapper {
  
  /**
   * wrap and set request,then return wrapped request
   * @param request request
   * @param body param
   * @return request @see Request
   */
  Request wrapRequestBody(Request request, Map<String, Object> body);
  
  /**
   * supportType
   * @return http body type (form/json) @see HttpBodyType
   */
  HttpBodyType getSupport();
  
}
