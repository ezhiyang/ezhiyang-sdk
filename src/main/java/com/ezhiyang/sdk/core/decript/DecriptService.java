package com.ezhiyang.sdk.core.decript;

public interface DecriptService {

  /**
   * 解密回调报文
   * @param encriptedJson 回调报文
   * @return java.lang.String 回调报文的明文
   */
  public String decript(String encriptedJson);
  
}
