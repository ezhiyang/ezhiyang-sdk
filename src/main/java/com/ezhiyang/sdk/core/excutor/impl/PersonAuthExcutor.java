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
  
  /**
   * 姓名
   * @param name name
   * @return PersonAuthExcutor
   */
  public PersonAuthExcutor setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * 身份证号
   * @param certNo certNo
   * @return PersonAuthExcutor
   */
  public PersonAuthExcutor setCertNo(String certNo) {
    this.certNo = certNo;
    return this;
  }

  /**
   * 手机号
   * @param mobile mobile
   * @return PersonAuthExcutor
   */
  public PersonAuthExcutor setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  /**
   * 收款账号类型:1-银行卡，2-支付宝
   * @param accountType accountType
   * @return PersonAuthExcutor
   */
  public PersonAuthExcutor setAccountType(Integer accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * 收款账号
   * @param accountNo accountNo
   * @return PersonAuthExcutor
   */
  public PersonAuthExcutor setAccountNo(String accountNo) {
    this.accountNo = accountNo;
    return this;
  }

  /**
   * 姓名
   * @return String
   */
  public String getName() {
    return name;
  }

  /**
   * 身份证号
   * @return String
   */
  public String getCertNo() {
    return certNo;
  }

  /**
   * 手机号
   * @return String
   */
  public String getMobile() {
    return mobile;
  }

  /**
   * 收款账号类型:1-银行卡，2-支付宝
   * @return Integer
   */
  public Integer getAccountType() {
    return accountType;
  }

  /**
   * 收款账号
   * @return String
   */
  public String getAccountNo() {
    return accountNo;
  }

  @Override
  protected boolean needSign() {
    return true;
  }

  @Override
  protected String signPropsIn() {
    return "name,certNo,mobile,accountType,accountNo";
  }
  
}
