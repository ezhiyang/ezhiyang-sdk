package com.ezhiyang.sdk.core.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Theo Zhou
 *
 */
public final class ClientConfig implements Serializable{

  private Logger logger = LoggerFactory.getLogger(ClientConfig.class);
  
  private static final long serialVersionUID = -5201314L;
  
  private static final String AUTH_PATH = "/open/authorize";
  
  private static final String INTF_PATH = "/open/rest";

  private String url;
  
  private String clientId;
  
  private String secret;
  
  private String privatekey;
  /**
   * 缓存超时时间
   */
  private long expiredTime = 3600;
  /**
   * 连接超时时间
   */
  private Integer connectTimeout = 5000;
  /**
   * socket超时时间
   */
  private Integer socketTimeout = 30000;
  
  public ClientConfig(String url, String clientId, String secret, String privatekey) {
    super();
    this.url = url;
    this.clientId = clientId;
    this.secret = secret;
    this.privatekey = privatekey;
  }
  
  public boolean selfCheck() {
    if(StringUtils.isBlank(url)) {
      logger.error("please init param[url]");
      return false;
    }
    if(StringUtils.isBlank(clientId)) {
      logger.error("please init param[clientId]");
      return false;
    }
    if(StringUtils.isBlank(secret)) {
      logger.error("please init param[secret]");
      return false;
    }
    if(StringUtils.isBlank(privatekey)) {
      logger.error("please init param[privatekey]");
      return false;
    }
    return true;
  }

  public String getAuthUrl() {
    return new StringBuilder(this.url).append(AUTH_PATH).toString();
  }
  
  public String getOpenUrl() {
    return new StringBuilder(this.url).append(INTF_PATH).toString();
  }

  public String getClientId() {
    return clientId;
  }

  public String getSecret() {
    return secret;
  }

  
  public String getPrivatekey() {
    return privatekey;
  }

  public ClientConfig setExpiredTime(long expiredTime) {
    this.expiredTime =  expiredTime;
    return this;
  }
  
  public long getExpiredTime() {
    return this.expiredTime;
  }

  public Integer getConnectTimeout() {
    return connectTimeout;
  }

  public void setConnectTimeout(Integer connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public Integer getSocketTimeout() {
    return socketTimeout;
  }

  public void setSocketTimeout(Integer socketTimeout) {
    this.socketTimeout = socketTimeout;
  }
  
  
 
}
