package com.ezhiyang.sdk.core.auth;

import com.ezhiyang.sdk.core.cache.AbstractAuthCache;
import com.ezhiyang.sdk.core.model.ClientConfig;
import com.ezhiyang.sdk.core.model.RequestWrapper;

/**
 * 
 * @author Theo Zhou
 *
 */
public abstract class AbstractAuthHandler{
  
  
  public AbstractAuthHandler() {
  }
  
  /**
   * auth and get token
   * @param clientConfig client config
   * @param cache token cache
   * @return token
   */
  public abstract String auth(ClientConfig clientConfig,AbstractAuthCache cache);
  
  /**
   * wrap request
   * @param request request @see RequestWrapper
   * @param token token
   * @return wrapped reqeust @see RequestWrapper
   */
  public abstract RequestWrapper wrapRequest(RequestWrapper request, String token);

}
