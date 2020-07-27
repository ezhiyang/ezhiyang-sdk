package com.ezhiyang.sdk.core.excutor.ret;

import java.io.Serializable;
import java.util.List;

import com.ezhiyang.sdk.core.model.BaseReturnVo;

/**
 * 提现结果返回
 * @author Theo Zhou
 *
 */
public class SubmitWithdrawVo extends BaseReturnVo{

  private static final long serialVersionUID = -8705043261008322121L;
  List<WithdrawListVo> withdrawList;
  
  public List<WithdrawListVo> getWithdrawList() {
    return withdrawList;
  }

  public void setWithdrawList(List<WithdrawListVo> withdrawList) {
    this.withdrawList = withdrawList;
  }


  @Override
  public String toString() {
    return "SubmitWithdrawVo [withdrawList=" + withdrawList + "]";
  }



  public static class WithdrawListVo implements Serializable{

    private static final long serialVersionUID = -2830190768520152275L;
    
    /**
     * 客户提供的唯一提现ID
     */
    private String bizId;
    /**
     * 提现发起返回码,参见附表3
     */
    private Integer result;
    /**
     * 流水号(结果为1-已受理时生成)
     */
    private String taskNo;

    public String getBizId() {
      return bizId;
    }

    public WithdrawListVo setBizId(String bizId) {
      this.bizId = bizId;
      return this;
    }

    public Integer getResult() {
      return result;
    }

    public WithdrawListVo setResult(Integer result) {
      this.result = result;
      return this;
    }

    public String getTaskNo() {
      return taskNo;
    }

    public WithdrawListVo setTaskNo(String taskNo) {
      this.taskNo = taskNo;
      return this;
    }

    @Override
    public String toString() {
      return "WithdrawListVo [bizId=" + bizId + ", result=" + result + ", taskNo=" + taskNo + "]";
    }
    
  }
  
}
