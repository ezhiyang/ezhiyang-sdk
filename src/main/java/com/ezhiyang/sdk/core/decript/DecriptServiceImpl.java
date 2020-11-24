package com.ezhiyang.sdk.core.decript;

import com.ezhiyang.sdk.core.exception.SdkException;

public class DecriptServiceImpl implements DecriptService{

  private String myPrivateKeyContent;
  
  private String zyPublicKeyContent;
  
  public DecriptServiceImpl(String myPrivateKeyContent,String zyPublicKeyContent) {
    if(zyPublicKeyContent == null) {
      throw new SdkException("请设置智阳公钥");
    }
    this.myPrivateKeyContent = myPrivateKeyContent;
    this.zyPublicKeyContent = zyPublicKeyContent;
  }
  
  @Override
  public String decript(String encriptedJson) {
    return DecriptCallbackUtil.decript(encriptedJson, zyPublicKeyContent, myPrivateKeyContent);
  }

}
