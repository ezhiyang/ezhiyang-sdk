package com.ezhiyang.sdk.core.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis authentication cache
 * @author Theo Zhou
 *
 */
public class RedisAuthCache extends AbstractAuthCache{

  private RedisTemplate<Object,Object> redisTemplate;
  
  public RedisAuthCache(RedisTemplate<Object,Object> redisTemplate) {
    this.redisTemplate = redisTemplate; 
  }
  
  @Override
  protected String buildKey(String clientId) {
    return CACHE_PREFIX+clientId;
  }

  @Override
  protected String getToken(String key) {
    return (String) redisTemplate.opsForValue().get(key);
  }

  @Override
  protected void updateToken(String key, String token, long expiredTime) {
    redisTemplate.opsForValue().set(key, token, expiredTime, TimeUnit.SECONDS);
  }
  
  
}
