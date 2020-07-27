package com.ezhiyang.sdk.core.excutor.impl;

import java.util.Map;

import com.ezhiyang.sdk.core.excutor.AbstractExcuteHandler;
import com.ezhiyang.sdk.core.excutor.ret.PersonAuthVo;
/**
 * 信息认证接口
 * @author Theo Zhou
 *
 */
public final class PersonAuthExcutor extends AbstractExcuteHandler<PersonAuthVo,Map<String,Object>>{

  private static final long serialVersionUID = -4984012293112519874L;
  /**
   * 姓名
   */
  private String name;
  /**
   * 身份证号
   */
  private String certNo;
  
  /**
   * 手机号
   */
  private String mobile;
  
  /**
   * 收款账号类型:1-银行卡，2-支付宝
   */
  private Integer accountType;
  
  /**
   * 收款账号

   */
  private String accountNo;
  
  @Override
  protected String getTypeCode() {
    return "ralph.personAuth";
  }

  @Override
  protected PersonAuthVo wrapResponse(Map<String,Object> data) {
    PersonAuthVo vo = new PersonAuthVo();
    return vo;
  }
  
  public PersonAuthExcutor setName(String name) {
    this.name = name;
    return this;
  }

  public PersonAuthExcutor setCertNo(String certNo) {
    this.certNo = certNo;
    return this;
  }

  public PersonAuthExcutor setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public PersonAuthExcutor setAccountType(Integer accountType) {
    this.accountType = accountType;
    return this;
  }

  public PersonAuthExcutor setAccountNo(String accountNo) {
    this.accountNo = accountNo;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getCertNo() {
    return certNo;
  }

  public String getMobile() {
    return mobile;
  }

  public Integer getAccountType() {
    return accountType;
  }

  public String getAccountNo() {
    return accountNo;
  }
  
}
