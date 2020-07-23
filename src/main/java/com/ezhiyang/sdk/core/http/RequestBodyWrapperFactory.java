package com.ezhiyang.sdk.core.http;

import com.ezhiyang.sdk.core.model.RequestWrapper.HttpBodyType;

/**
 * request body wrapper factory
 * @author Theo Zhou
 *
 */
public class RequestBodyWrapperFactory {
  
  public static IRequestBodyWrapper getWrapper(HttpBodyType bodyType) {
    switch(bodyType) {
    case FORM: return FormRequestBodyWrapper.getInstance();
    case JSON: return JsonRequestBodyWrapper.getInstance();
    default: return JsonRequestBodyWrapper.getInstance();
    }
  }
  
}
