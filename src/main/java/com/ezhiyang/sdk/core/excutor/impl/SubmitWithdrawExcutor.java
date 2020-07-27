package com.ezhiyang.sdk.core.excutor.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.ezhiyang.sdk.core.excutor.AbstractExcuteHandler;
import com.ezhiyang.sdk.core.excutor.ret.SubmitWithdrawVo;
import com.ezhiyang.sdk.core.excutor.ret.SubmitWithdrawVo.WithdrawListVo;

/**
 * 提现发起接口
 * @author Theo Zhou
 *
 */
public class SubmitWithdrawExcutor extends AbstractExcuteHandler<SubmitWithdrawVo,List<Map<String,Object>>>{

  private static final long serialVersionUID = 4510592217295189968L;
  
  /**
   * 提现信息列表(列表项最大1000)
   */
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
    /**
     * 客户提供的唯一提现ID
     */
    private String bizId;
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
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 企业标识(多企业必填)
     */
    private String companyCode;
    /**
     * 任务名称(无模板必填)
     */
    private String taskName;
    /**
     * 任务类型：参见附表2-任务类型(无模板必填)
     */
    private Integer taskType;
    /**
     * 任务开始日期(yyyy-MM-dd HH:mm:ss)(无模板必填)
     */
    private Date taskStartDate;
    /**
     * 任务结束日期(yyyy-MM-dd HH:mm:ss)(无模板必填)
     */
    private Date taskEndDate;
    /**
     * 任务详情(无模板必填)
     */
    private String taskDetail;
    
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
    
    public String getCompanyCode() {
      return companyCode;
    }

    public SubmitWithdrawParam setCompanyCode(String companyCode) {
      this.companyCode = companyCode;
      return this;
    }

    public String getTaskName() {
      return taskName;
    }

    public SubmitWithdrawParam setTaskName(String taskName) {
      this.taskName = taskName;
      return this;
    }

    public Integer getTaskType() {
      return taskType;
    }

    public SubmitWithdrawParam setTaskType(Integer taskType) {
      this.taskType = taskType;
      return this;
    }

    public Date getTaskStartDate() {
      return taskStartDate;
    }

    public SubmitWithdrawParam setTaskStartDate(Date taskStartDate) {
      this.taskStartDate = taskStartDate;
      return this;
    }

    public Date getTaskEndDate() {
      return taskEndDate;
    }

    public SubmitWithdrawParam setTaskEndDate(Date taskEndDate) {
      this.taskEndDate = taskEndDate;
      return this;
    }

    public String getTaskDetail() {
      return taskDetail;
    }

    public SubmitWithdrawParam setTaskDetail(String taskDetail) {
      this.taskDetail = taskDetail;
      return this;
    }

    @Override
    public String toString() {
      return "SubmitWithdrawParam [bizId=" + bizId + ", name=" + name + ", certNo=" + certNo + ", mobile=" + mobile
          + ", accountType=" + accountType + ", accountNo=" + accountNo + ", amount=" + amount + ", companyCode="
          + companyCode + ", taskName=" + taskName + ", taskType=" + taskType + ", taskStartDate=" + taskStartDate
          + ", taskEndDate=" + taskEndDate + ", taskDetail=" + taskDetail + "]";
    }
    
  }

}
