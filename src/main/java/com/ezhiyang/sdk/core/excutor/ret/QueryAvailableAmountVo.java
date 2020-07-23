package com.ezhiyang.sdk.core.excutor.ret;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.ezhiyang.sdk.core.model.BaseReturnVo;
/**
 * 额度查询接口返回结果
 * @author Theo Zhou
 *
 */
public class QueryAvailableAmountVo extends BaseReturnVo{

  private static final long serialVersionUID = -8705043261008322121L;
  
  List<QueryListVo> queryList;
  
  public List<QueryListVo> getQueryList() {
    return queryList;
  }

  public void setQueryList(List<QueryListVo> queryList) {
    this.queryList = queryList;
  }
  
  public void addQueryListVo(QueryListVo vo) {
    if(queryList == null) {
      queryList = new LinkedList<QueryAvailableAmountVo.QueryListVo>();
    }
    queryList.add(vo);
  }

  @Override
  public String toString() {
    return "QueryAvailableAmountVo [queryList=" + queryList + ", getCode()=" + getCode() + ", getMsg()=" + getMsg()
        + "]";
  }

  public static class QueryListVo implements Serializable{

    private static final long serialVersionUID = -2830190768520152275L;

    private String certNo;
    private String name;
    private Integer status;
    private BigDecimal availableAmount;
    
    public String getCertNo() {
      return certNo;
    }
    public QueryListVo setCertNo(String certNo) {
      this.certNo = certNo;
      return this;
    }
    public String getName() {
      return name;
    }
    public QueryListVo setName(String name) {
      this.name = name;
      return this;
    }
    public Integer getStatus() {
      return status;
    }
    public QueryListVo setStatus(Integer status) {
      this.status = status;
      return this;
    }
    public BigDecimal getAvailableAmount() {
      return availableAmount;
    }
    public QueryListVo setAvailableAmount(BigDecimal availableAmount) {
      this.availableAmount = availableAmount;
      return this;
    }
    @Override
    public String toString() {
      return "QueryListVo [certNo=" + certNo + ", name=" + name + ", status=" + status + ", availableAmount="
          + availableAmount + "]";
    }
    

  }
  
}
