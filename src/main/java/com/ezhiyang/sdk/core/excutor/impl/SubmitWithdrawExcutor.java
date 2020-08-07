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
  
  @Override
  protected boolean needSign() {
    return true;
  }

  @Override
  protected String signPropsIn() {
    return "[withdrawList,bizId,name,certNo,mobile,accountType,accountNo,amount]";
  }

  /**
   * 提现参数
   * @author Theo Zhou
   *
   */
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
    
    /**
     * 客户提供的唯一提现ID
     * @return String
     */
    public String getBizId() {
      return bizId;
    }
    
    /**
     * 客户提供的唯一提现ID
     * @param bizId bizId
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setBizId(String bizId) {
      this.bizId = bizId;
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
     * 姓名 
     * @param name name
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setName(String name) {
      this.name = name;
      return this;
    }
    
    /**
     * 身份证号
     * @return String
     */
    public String getCertNo() {
      return certNo;
    }
    
    /**
     * 身份证号
     * @param certNo certNo
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setCertNo(String certNo) {
      this.certNo = certNo;
      return this;
    }
    
    /**
     * 手机号
     * @return String
     */
    public String getMobile() {
      return mobile;
    }
    
    /**
     * 手机号
     * @param mobile mobile
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setMobile(String mobile) {
      this.mobile = mobile;
      return this;
    }
    
    /**
     * 收款账号类型:1-银行卡，2-支付宝
     * @return Integer
     */
    public Integer getAccountType() {
      return accountType;
    }
    
    /**
     * 收款账号类型:1-银行卡，2-支付宝
     * @param accountType accountType
     * @return @see SubmitWithdrawParam
     */
    public SubmitWithdrawParam setAccountType(Integer accountType) {
      this.accountType = accountType;
      return this;
    }
    
    /**
     * 收款账号
     * @return String
     */
    public String getAccountNo() {
      return accountNo;
    }
    
    /**
     * 收款账号
     * @param accountNo accountNo
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setAccountNo(String accountNo) {
      this.accountNo = accountNo;
      return this;
    }
    
    /**
     * 金额
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
      return amount;
    }
    
    /**
     * 金额
     * @param amount BigDecimal
     * @return  SubmitWithdrawParam
     */
    public SubmitWithdrawParam setAmount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }
    
    /**
     * 企业标识(多企业必填)
     * @return String
     */
    public String getCompanyCode() {
      return companyCode;
    }

    /**
     * 企业标识(多企业必填)
     * @param companyCode companyCode
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setCompanyCode(String companyCode) {
      this.companyCode = companyCode;
      return this;
    }

    /**
     * 任务名称(无模板必填)
     * @return String
     */
    public String getTaskName() {
      return taskName;
    }

    /**
     * 任务名称(无模板必填)
     * @param taskName taskName
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setTaskName(String taskName) {
      this.taskName = taskName;
      return this;
    }

    /**
     * 任务类型：参见附表2-任务类型(无模板必填)
     * @return Integer
     */
    public Integer getTaskType() {
      return taskType;
    }

    /**
     * 任务类型：参见附表2-任务类型(无模板必填)
     * @param taskType taskType
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setTaskType(Integer taskType) {
      this.taskType = taskType;
      return this;
    }

    /**
     * 任务开始日期(yyyy-MM-dd HH:mm:ss)(无模板必填)
     * @return Date
     */
    public Date getTaskStartDate() {
      return taskStartDate;
    }

    /**
     * 任务开始日期(yyyy-MM-dd HH:mm:ss)(无模板必填)
     * @param taskStartDate taskStartDate
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setTaskStartDate(Date taskStartDate) {
      this.taskStartDate = taskStartDate;
      return this;
    }

    /**
     * 任务结束日期(yyyy-MM-dd HH:mm:ss)(无模板必填)
     * @return Date
     */
    public Date getTaskEndDate() {
      return taskEndDate;
    }

    /**
     * 任务结束日期(yyyy-MM-dd HH:mm:ss)(无模板必填)
     * @param taskEndDate taskEndDate
     * @return SubmitWithdrawParam
     */
    public SubmitWithdrawParam setTaskEndDate(Date taskEndDate) {
      this.taskEndDate = taskEndDate;
      return this;
    }

    /**
     * 任务详情(无模板必填)
     * @return String
     */
    public String getTaskDetail() {
      return taskDetail;
    }

    /**
     * 任务详情(无模板必填)
     * @param taskDetail taskDetail
     * @return SubmitWithdrawParam
     */
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
