package com.ezhiyang.sdk.core.excutor.ret;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ezhiyang.sdk.core.model.BaseReturnVo;
/**
 * 
 * @author Theo Zhou
 *
 */
public class QueryAccountDetailVo extends BaseReturnVo{

  private static final long serialVersionUID = 5641726282828699170L;

  private Integer pageSize;
  
  private Integer pageNo;
  
  private Integer pageCount;
  
  private Long totalCount;
  
  private List<EntityListVo> entityList;
  
  public Integer getPageSize() {
    return pageSize;
  }

  public QueryAccountDetailVo setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }
  
  public Integer getPageNo() {
    return pageNo;
  }

  public QueryAccountDetailVo setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
    return this;
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public QueryAccountDetailVo setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }
  

  public Integer getPageCount() {
    return pageCount;
  }

  public QueryAccountDetailVo setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
    return this;
  }

  public List<EntityListVo> getEntityList() {
    return entityList;
  }

  public QueryAccountDetailVo setEntityList(List<EntityListVo> entityList) {
    this.entityList = entityList;
    return this;
  }
  
  public QueryAccountDetailVo addEntityVo(EntityListVo entityVo) {
    if(entityList == null) {
      entityList = new LinkedList<QueryAccountDetailVo.EntityListVo>();
    }
    entityList.add(entityVo);
    return this;
  }
  

  @Override
  public String toString() {
    return "QueryAccountDetailVo [pageSize=" + pageSize + ", pageNo=" + pageNo + ", pageCount=" + pageCount
        + ", totalCount=" + totalCount + ", entityList=" + entityList + ", getCode()=" + getCode() + ", getMsg()="
        + getMsg() + "]";
  }


  public static class EntityListVo implements Serializable {

    private static final long serialVersionUID = -2341611490466401921L;
    
    private Date transTime;
    
    private Integer dcFlag;
    
    private BigDecimal transAmount;
    
    private BigDecimal acctTotalBalance;
    
    private BigDecimal acctBalance;
    
    private BigDecimal acctFreeze;
    
    private String remark;

    public Date getTransTime() {
      return transTime;
    }

    public EntityListVo setTransTime(Date transTime) {
      this.transTime = transTime;
      return this;
    }

    public Integer getDcFlag() {
      return dcFlag;
    }

    public EntityListVo setDcFlag(Integer dcFlag) {
      this.dcFlag = dcFlag;
      return this;
    }

    public BigDecimal getTransAmount() {
      return transAmount;
    }

    public EntityListVo setTransAmount(BigDecimal transAmount) {
      this.transAmount = transAmount;
      return this;
    }

    public BigDecimal getAcctTotalBalance() {
      return acctTotalBalance;
    }

    public EntityListVo setAcctTotalBalance(BigDecimal acctTotalBalance) {
      this.acctTotalBalance = acctTotalBalance;
      return this;
    }

    public BigDecimal getAcctBalance() {
      return acctBalance;
    }

    public EntityListVo setAcctBalance(BigDecimal acctBalance) {
      this.acctBalance = acctBalance;
      return this;
    }

    public BigDecimal getAcctFreeze() {
      return acctFreeze;
    }

    public EntityListVo setAcctFreeze(BigDecimal acctFreeze) {
      this.acctFreeze = acctFreeze;
      return this;
    }

    public String getRemark() {
      return remark;
    }

    public EntityListVo setRemark(String remark) {
      this.remark = remark;
      return this;
    }

    @Override
    public String toString() {
      return "EntityListVo [transTime=" + transTime + ", dcFlag=" + dcFlag + ", transAmount=" + transAmount
          + ", acctTotalBalance=" + acctTotalBalance + ", acctBalance=" + acctBalance + ", acctFreeze=" + acctFreeze
          + ", remark=" + remark + "]";
    }
    
  }
 
  
}
