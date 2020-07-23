package com.ezhiyang.sdk.core.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * default authentication cache
 * @author Theo Zhou
 *
 */
public class MemoryAuthCache extends AbstractAuthCache{

  private Logger logger = LoggerFactory.getLogger(MemoryAuthCache.class);
  
  /**
   * cacheMap key tokenKey, value token
   */
  public Map<String, String> cacheMap = new HashMap<String, String>(10);
  
  @Override
  protected String buildKey(String clientId) {
    return CACHE_PREFIX+clientId;
  }

  @Override
  protected String getToken(String key) {
    return cacheMap.get(key);
  }

  @Override
  protected void updateToken(String key, String token, long expiredTime) {
    cacheMap.put(key, token);
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
        1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    threadPool.execute(new MemoryKiller(key,expiredTime));
    threadPool.shutdown();
  }
  
  public static void main(String [] args) throws Exception {
    MemoryAuthCache cache = new MemoryAuthCache();
    cache.updateToken("123", "1", 5);
    cache.updateToken("345", "2", 5);
    Object o = new Object();
    Thread.sleep(4999);
    System.out.println(cache.getToken("123"));
    System.out.println(cache.getToken("345"));
    
  }
  
  private class MemoryKiller extends Thread {

    private String key;
    private long expiredTime;
    
    private Object lock = new Object();
    
    protected MemoryKiller(String key,long expiredTime) {
      this.key = key;
      this.expiredTime = expiredTime;
      super.setName("memory killer thread");
    }
    
    @Override
    public void run(){
      try {
        synchronized (this.lock) {
          lock.wait(expiredTime*1000);
          cacheMap.remove(key);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
        logger.error(e.getMessage(),e);
      }
     
    }
    
  }
  
  
}
