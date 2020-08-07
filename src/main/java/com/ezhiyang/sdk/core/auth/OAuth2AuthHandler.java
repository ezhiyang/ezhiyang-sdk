package com.ezhiyang.sdk.core.auth;

import java.util.Base64;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ezhiyang.sdk.core.cache.AbstractAuthCache;
import com.ezhiyang.sdk.core.cache.MemoryAuthCache;
import com.ezhiyang.sdk.core.http.HttpClient;
import com.ezhiyang.sdk.core.model.ClientConfig;
import com.ezhiyang.sdk.core.model.RequestWrapper;
import com.ezhiyang.sdk.core.model.RequestWrapper.HttpBodyType;
import com.ezhiyang.sdk.core.model.RequestWrapper.HttpMethodName;
import com.ezhiyang.sdk.core.model.ResponseWrapper;
/**
 * oauth2.0 authorization handler
 * @author Theo Zhou
 *
 */
public class OAuth2AuthHandler extends AbstractAuthHandler{
  
  private Logger logger = LoggerFactory.getLogger(OAuth2AuthHandler.class);
  
  private final static String AUTHORIZATION = "Authorization";
  private final static String AUTHORIZATION_HEAD = "Basic ";
  private final static String REQUEST_HEAD = "Bearer ";
  
  
  
  @Override
  public String auth(ClientConfig clientConfig,AbstractAuthCache cache) {
    
    // judge if cache is null, init default cache
    if(cache == null) {
      cache = new MemoryAuthCache();
    }
    // check params
    if(!clientConfig.selfCheck()) {
      logger.error("params invalid");
      return null;
    }
    
    String clientId = clientConfig.getClientId();
    String secret = clientConfig.getSecret();
    long expiredTime = clientConfig.getExpiredTime();
    
    //get key in cache if exists
    String token = cache.getTokenInCache(clientId);
    if(token == null) {
      //request token
      RequestWrapper authRequest = new RequestWrapper();
      authRequest.setMethodName(HttpMethodName.POST);
      authRequest.setUrl(clientConfig.getAuthUrl());
      authRequest.setBodyType(HttpBodyType.FORM);
      // fixed params
      authRequest.addBody("grant_type", "client_credentials");
      
      String authorization = AUTHORIZATION_HEAD + 
          Base64.getEncoder().encodeToString((clientId+":"+secret).getBytes()) ;
      authRequest.addHeader(AUTHORIZATION,authorization);
      
      HttpClient client = HttpClient.getInstance();
      ResponseWrapper response = client.execute(authRequest,
          clientConfig.getConnectTimeout(),clientConfig.getSocketTimeout());
      Object newToken = response.getBody().get("access_token");
      if(newToken != null) {
        token = newToken.toString();
      }
      if(StringUtils.isNotBlank(token)) {
        cache.updateTokenInCache(clientId, token, expiredTime);
      }
    }
    
    return token;
  }

  @Override
  public RequestWrapper wrapRequest(RequestWrapper request, String token) {
    return request.addHeader(AUTHORIZATION, REQUEST_HEAD+token);
  }

}
