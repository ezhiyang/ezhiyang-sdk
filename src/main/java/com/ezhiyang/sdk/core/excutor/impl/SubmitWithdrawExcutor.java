package com.ezhiyang.sdk.core.excutor.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.ezhiyang.sdk.core.excutor.AbstractExcuteHandler;
import com.ezhiyang.sdk.core.excutor.ret.SubmitWithdrawVo;
import com.ezhiyang.sdk.core.excutor.ret.SubmitWithdrawVo.WithdrawListVo;

/**
 * 
 * @author Theo Zhou
 *
 */
public class SubmitWithdrawExcutor extends AbstractExcuteHandler<SubmitWithdrawVo,List<Map<String,Object>>>{

  private static final long serialVersionUID = 4510592217295189968L;
  
  private List<SubmitWithdrawParam> withdrawList;
  
  public List<SubmitWithdrawParam> getWithdrawList() {
    return withdrawList;
  }
  
  public void addWithdrawParam(SubmitWithdrawParam param) {
    if(withdrawList == null) {
      withdrawList = new LinkedList<SubmitWithdrawExcutor.SubmitWithdrawParam>();
    }
    withdrawList.add(param);
  }

  public SubmitWithdrawExcutor setWithdrawList(List<SubmitWithdrawParam> withdrawList) {
    this.withdrawList = withdrawList;
    return this;
  }

  @Override
  protected String getTypeCode() {
    return "ralph.submitWithdraw";
  }

  @Override
  protected SubmitWithdrawVo wrapResponse(List<Map<String,Object>> data) {
    SubmitWithdrawVo ret = new SubmitWithdrawVo();
    if(data != null) {
      List<WithdrawListVo> l = new ArrayList<WithdrawListVo>();
      for(Map<String,Object> d : data) {
        WithdrawListVo vo = new WithdrawListVo();
        vo.setBizId(MapUtils.getString(d, "bizId"))
          .setResult(MapUtils.getInteger(d, "result"))
          .setTaskNo(MapUtils.getString(d, "taskNo"));
        l.add(vo);
      }
      ret.setWithdrawList(l);
    }
    return ret;
  }

  public static class SubmitWithdrawParam implements Serializable{
    
    private static final long serialVersionUID = 3467606633311543192L;
    private String bizId;
    private String name;
    private String certNo;
    private String mobile;
    private Integer accountType;
    private String accountNo;
    private BigDecimal amount;
    
    public String getBizId() {
      return bizId;
    }
    
    public SubmitWithdrawParam setBizId(String bizId) {
      this.bizId = bizId;
      return this;
    }
    public String getName() {
      return name;
    }
    public SubmitWithdrawParam setName(String name) {
      this.name = name;
      return this;
    }
    public String getCertNo() {
      return certNo;
    }
    public SubmitWithdrawParam setCertNo(String certNo) {
      this.certNo = certNo;
      return this;
    }
    public String getMobile() {
      return mobile;
    }
    public SubmitWithdrawParam setMobile(String mobile) {
      this.mobile = mobile;
      return this;
    }
    public Integer getAccountType() {
      return accountType;
    }
    public SubmitWithdrawParam setAccountType(Integer accountType) {
      this.accountType = accountType;
      return this;
    }
    public String getAccountNo() {
      return accountNo;
    }
    public SubmitWithdrawParam setAccountNo(String accountNo) {
      this.accountNo = accountNo;
      return this;
    }
    public BigDecimal getAmount() {
      return amount;
    }
    public SubmitWithdrawParam setAmount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }
    @Override
    public String toString() {
      return "SubmitWithdrawParam [bizId=" + bizId + ", name=" + name + ", certNo=" + certNo + ", mobile=" + mobile
          + ", accountType=" + accountType + ", accountNo=" + accountNo + ", amount=" + amount + "]";
    }
    
    
    
  }

}
