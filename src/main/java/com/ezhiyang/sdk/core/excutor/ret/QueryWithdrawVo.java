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

    private String bizId;
    
    private Integer status;
    
    private String taskNo;

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

    @Override
    public String toString() {
      return "QueryListVo [bizId=" + bizId + ", status=" + status + ", taskNo=" + taskNo + "]";
    }
    
    

  }
  
}
