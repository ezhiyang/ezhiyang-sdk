package com.ezhiyang.sdk.core.cache;
/**
 * abstract authentication cache
 * @author Theo Zhou
 *
 */
public abstract class AbstractAuthCache {

  public static final String CACHE_PREFIX = "ezhiyang:ralph:";
  
  /**
   * build cache key
   * @param clientId current clientId
   * @return
   */
  protected abstract String buildKey(String clientId);
  
  /**
   * get token by key
   * @param key
   * @return
   */
  protected abstract String getToken(String key);
  
  /**
   * update token by key 
   * @param key
   * @param token
   * @param expiredTime
   */
  protected abstract void updateToken(String key,String token,long expiredTime);
  
  public String getTokenInCache(String clientId) {
    if(clientId == null) {
      return null;
    }
    String key = buildKey(clientId);
    if(key == null) {
      return null;
    }
    return getToken(key);
  }
  
  public void updateTokenInCache(String clientId,String token,long expiredTime) {
    if(clientId == null) {
      return;
    }
    String key = buildKey(clientId);
    if(key == null) {
      return;
    }
    updateToken(key,token,expiredTime);
  }
  
}
