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
   * @return the key of the cahce
   */
  protected abstract String buildKey(String clientId);
  
  /**
   * get token by key
   * @param key key of the cahce
   * @return token
   */
  protected abstract String getToken(String key);
  
  /**
   * update token by key 
   * @param key key of the cahce
   * @param token token
   * @param expiredTime expired time(s) default 7200
   */
  protected abstract void updateToken(String key,String token,long expiredTime);
  
  /**
   * get token in cache
   * @param clientId clientId
   * @return token in cache
   */
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
  
  /**
   * update token in cache
   * @param clientId clientId
   * @param token token
   * @param expiredTime expired time(s) default 7200
   */
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
