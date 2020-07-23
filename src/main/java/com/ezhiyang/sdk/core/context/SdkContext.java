package com.ezhiyang.sdk.core.context;

import com.ezhiyang.sdk.core.auth.AbstractAuthHandler;
import com.ezhiyang.sdk.core.auth.OAuth2AuthHandler;
import com.ezhiyang.sdk.core.cache.AbstractAuthCache;
import com.ezhiyang.sdk.core.cache.MemoryAuthCache;
import com.ezhiyang.sdk.core.model.ClientConfig;
import com.ezhiyang.sdk.core.model.RequestWrapper;

/**
 * sdk 上下文
 * @author Theo Zhou
 *
 */
public class SdkContext {
  
  protected ClientConfig clientConfig;
  
  protected AbstractAuthCache cache;
  
  protected AbstractAuthHandler authHandler;
  
  
  public SdkContext(ClientConfig clientConfig, AbstractAuthCache cache) {
    super();
    this.clientConfig = clientConfig;
    this.cache = cache;
    authHandler = new OAuth2AuthHandler();
  }
  
  public SdkContext(ClientConfig clientConfig) {
    this.clientConfig = clientConfig;
    this.cache = new MemoryAuthCache();
    authHandler = new OAuth2AuthHandler();
  }

  public AbstractAuthHandler getAuthHandler() {
    return authHandler;
  }

  public void setAuthHandler(AbstractAuthHandler authHandler) {
    this.authHandler = authHandler;
  }

  public ClientConfig getClientConfig() {
    return clientConfig;
  }

  public void setClientConfig(ClientConfig clientConfig) {
    this.clientConfig = clientConfig;
  }
  
  public AbstractAuthCache getCache() {
    return cache;
  }

  public void setCache(AbstractAuthCache cache) {
    this.cache = cache;
  }

  /**
   * get open url
   * @return
   */
  public String getOpenUrl() {
    if(clientConfig == null) {
      return null;
    }
    return clientConfig.getOpenUrl();
  }
  
  /**
   * get private key
   * @return
   */
  public String getPrivatekey() {
    if(clientConfig == null) {
      return null;
    }
    return clientConfig.getPrivatekey();
  }
  
  /**
   * authentication
   * @param request
   * @return
   */
  public boolean authentication(RequestWrapper request) {
    if(authHandler == null) {
      return false;
    }
    String token = authHandler.auth(this.clientConfig,this.cache);
    if(token != null) {
      authHandler.wrapRequest(request, token);
    }
    return token == null ? false:true;
  }
  
}
