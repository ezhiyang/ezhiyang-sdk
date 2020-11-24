package com.ezhiyang.sdk.core.decript;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ezhiyang.sdk.core.exception.DecriptException;
import com.ezhiyang.sdk.core.exception.SdkException;
import com.ezhiyang.sdk.core.exception.VerifyException;
import com.ezhiyang.sdk.util.JsonUtils;
import com.ezhiyang.sdk.util.RsaUtils;
import com.ezhiyang.sdk.util.StringUtils;
import com.ezhiyang.sdk.util.TripleDesUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 解密回调报文工具
 * @author Theo Zhou
 *
 */
public class DecriptCallbackUtil {

  private static Logger logger = LoggerFactory.getLogger(DecriptCallbackUtil.class);
  
  public static String decript(String body,String zyPublicKeyContent,String myPrivateKeyContent) {
    PublicKey zyPublicKey;
    PrivateKey myPrivateKey;
    try {
      zyPublicKey = RsaUtils.loadPublicKey(zyPublicKeyContent);
      myPrivateKey = RsaUtils.loadPrivateKey(myPrivateKeyContent);
    } catch (Exception e) {
      logger.error(e.getMessage(),e);
      throw new SdkException("加载秘钥失败",e);
    }
    return decript(body,zyPublicKey,myPrivateKey);
  }
  
  public static String decript(String body,PublicKey zyPublicKey,PrivateKey myPrivateKey) {
    JsonNode bodyNode = JsonUtils.toJsonNode(body);
    String ret = null;
    // 1. 根据加密报文（encriptedData）和签名日期（signDate），使用智阳提供的公钥验签
    String encriptedData = bodyNode.get("encriptedData").asText();
    if(StringUtils.isBlank(encriptedData)) {
      throw new SdkException("加密报文获取失败");
    }
    String encriptedKey = bodyNode.get("encriptedKey").asText();
    if(StringUtils.isBlank(encriptedKey)) {
      throw new SdkException("秘钥获取失败");
    }
    
    JsonNode sign = bodyNode.get("sign") == null ? null : bodyNode.get("sign");
    String signDate = sign.get("signDate").asText();
    String encryptedHash = sign.get("encryptedHash").asText();
    if(StringUtils.isBlank(signDate)) {
      throw new SdkException("验签时间获取失败");
    }
    if(StringUtils.isBlank(encryptedHash)) {
      throw new SdkException("签名字符串获取失败");
    }
    
    StringBuilder verifyStr = new StringBuilder().append(encriptedData).append('&').append(signDate);
    logger.debug("待验签字符串：{}",verifyStr);
    boolean verifyResult = RsaUtils.verify(verifyStr.toString(), encryptedHash, zyPublicKey);
    if(!verifyResult) {
      logger.error("验签失败");
      throw new VerifyException("验签失败");
    }
    String myDes3Key;
    try {
      //2. 根据自己的私钥解密 encriptedKey 得到Des3秘钥
      myDes3Key = RsaUtils.decrypt(encriptedKey, myPrivateKey);
      //3. 使用des3秘钥解密报文
      byte[] decryptDatas = TripleDesUtil.tripleDesDecrypt(java.util.Base64.getDecoder().decode(encriptedData),  myDes3Key.getBytes());
      ret = new String(decryptDatas,"utf-8");
    } catch (Exception e) {
      logger.error(e.getMessage(),e);
      throw new DecriptException("解密失败");
    }
    
    return ret;
  }
  
  
}
