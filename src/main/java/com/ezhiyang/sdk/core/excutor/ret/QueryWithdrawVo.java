package com.ezhiyang.sdk.core.excutor.ret;

import java.io.Serializable;
import java.util.List;

import com.ezhiyang.sdk.core.model.BaseReturnVo;
/**
 * 查询提现结果
 * @author Theo Zhou
 *
 */
public class QueryWithdrawVo extends BaseReturnVo{

  private static final long serialVersionUID = -8705043261008322121L;
  /**
   * 业务数据列表(code为0时返回)
   */
  List<QueryListVo> queryList;
  
  public List<QueryListVo> getQueryList() {
    return queryList;
  }

  public void setQueryList(List<QueryListVo> queryList) {
    this.queryList = queryList;
  }

  @Override
  public String toString() {
    return "QueryWithdrawVo [queryList=" + queryList + ", getCode()=" + getCode() + ", getMsg()=" + getMsg() + "]";
  }


  public static class QueryListVo implements Serializable{

    private static final long serialVersionUID = -2830190768520152275L;
    /**
     * 客户提供的唯一提现ID
     */
    private String bizId;
    /**
     * 状态:1-已受理，2-已付款(可结算)，3-不存在，4-结算中，5-已结算，98-结算失败，99-已关闭
     */
    private Integer status;
    /**
     * 流水号
     */
    private String taskNo;
    /**
     *  结算失败编码(status=98时不为空)，参见附表4-结算失败返回码
     */
    private String errorCode;
    /**
     * 备注
     */
    private String resultMsg;

    public String getBizId() {
      return bizId;
    }

    public QueryListVo setBizId(String bizId) {
      this.bizId = bizId;
      return this;
    }

    public Integer getStatus() {
      return status;
    }

    public QueryListVo setStatus(Integer status) {
      this.status = status;
      return this;
    }

    public String getTaskNo() {
      return taskNo;
    }

    public QueryListVo setTaskNo(String taskNo) {
      this.taskNo = taskNo;
      return this;
    }
    
    public String getErrorCode() {
      return errorCode;
    }

    public QueryListVo setErrorCode(String errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    public String getResultMsg() {
      return resultMsg;
    }

    public QueryListVo setResultMsg(String resultMsg) {
      this.resultMsg = resultMsg;
      return this;
    }

    @Override
    public String toString() {
      return "QueryListVo [bizId=" + bizId + ", status=" + status + ", taskNo=" + taskNo + ", errorCode=" + errorCode
          + ", resultMsg=" + resultMsg + "]";
    }
    
    

  }
  
}
