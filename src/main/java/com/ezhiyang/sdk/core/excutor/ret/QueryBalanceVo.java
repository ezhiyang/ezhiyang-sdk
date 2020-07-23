package com.ezhiyang.sdk.core.excutor.ret;

import java.math.BigDecimal;

import com.ezhiyang.sdk.core.model.BaseReturnVo;

/**
 * 
 * @author Theo Zhou
 *
 */
public class QueryBalanceVo extends BaseReturnVo{

  private static final long serialVersionUID = 5641726282828699170L;

  private BigDecimal acctTotalBalance;
  
  private BigDecimal acctBalance;
  
  private BigDecimal acctFreeze;
  
  private BigDecimal availableBalance;

  public BigDecimal getAcctTotalBalance() {
    return acctTotalBalance;
  }

  public QueryBalanceVo setAcctTotalBalance(BigDecimal acctTotalBalance) {
    this.acctTotalBalance = acctTotalBalance;
    return this;
  }

  public BigDecimal getAcctBalance() {
    return acctBalance;
  }

  public QueryBalanceVo setAcctBalance(BigDecimal acctBalance) {
    this.acctBalance = acctBalance;
    return this;
  }

  public BigDecimal getAcctFreeze() {
    return acctFreeze;
  }

  public QueryBalanceVo setAcctFreeze(BigDecimal acctFreeze) {
    this.acctFreeze = acctFreeze;
    return this;
  }

  public BigDecimal getAvailableBalance() {
    return availableBalance;
  }

  public QueryBalanceVo setAvailableBalance(BigDecimal availableBalance) {
    this.availableBalance = availableBalance;
    return this;
  }

  @Override
  public String toString() {
    return "QueryBalanceVo [acctTotalBalance=" + acctTotalBalance + ", acctBalance=" + acctBalance + ", acctFreeze="
        + acctFreeze + ", availableBalance=" + availableBalance + ", getCode()=" + getCode() + ", getMsg()=" + getMsg()
        + "]";
  }
  
}
