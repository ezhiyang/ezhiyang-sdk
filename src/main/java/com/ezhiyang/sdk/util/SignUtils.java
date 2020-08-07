package com.ezhiyang.sdk.util;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * sign utils
 * @author ZY
 *
 */
public class SignUtils {
  
  private static Logger logger = LoggerFactory.getLogger(SignUtils.class);

  public static Map<String, Object> sign(Object data,String type,String privateKeyStr,String signPropsIn) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String signDate = sdf.format(new Date());

    JsonNode jsonNode = JsonUtils.toJsonNode(data);
    StringBuilder builder = new StringBuilder();
    JsonNodeSerializeUtil.jsonNodeToStr(builder, jsonNode.get("data"),
        signPropsIn);
    builder.append("&signDate=").append(signDate);

    String signature;
    try {
      PrivateKey privateKey = RsaUtils.loadPrivateKey(privateKeyStr);

      signature = RsaUtils.sign(builder.toString(), privateKey);
    } catch (Exception e) {
      logger.error("签名异常:{}", e);
      throw new RuntimeException("签名异常:{}",e);
    }

    Map<String, Object> result = new HashMap<>(10);
    result.put("signature", signature);
    result.put("signDate", signDate);

    return result;
  }
  
}
