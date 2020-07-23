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
   * @param clientConfig
   * @param cache
   * @return
   */
  public abstract String auth(ClientConfig clientConfig,AbstractAuthCache cache);
  
  /**
   * wrap request
   * @param request
   * @param token
   * @return
   */
  public abstract RequestWrapper wrapRequest(RequestWrapper request, String token);

}
