package com.ezhiyang.sdk.core.excutor.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.ezhiyang.sdk.core.excutor.AbstractExcuteHandler;
import com.ezhiyang.sdk.core.excutor.ret.QueryBalanceVo;

/**
 * 查询余额接口
 * @author Theo Zhou
 *
 */
public class QueryBalanceExcutor extends AbstractExcuteHandler<QueryBalanceVo,Map<String,Object>>{

  private static final long serialVersionUID = 4510592217295189968L;
  
  /**
   * 企业标识
   */
  private String companyCode;
  /**
   * 落地区域ID
   */
  private Long taxAreaId;
  
  /**
   * 企业标识
   * @return String
   */
  public String getCompanyCode() {
    return companyCode;
  }

  /**
   * 企业标识
   * @param companyCode companyCode
   * @return QueryBalanceExcutor
   */
  public QueryBalanceExcutor setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
    return this;
  }

  /**
   * 落地区域ID
   * @return Long
   */
  public Long getTaxAreaId() {
    return taxAreaId;
  }

  /**
   * 落地区域ID
   * @param taxAreaId
   * @return QueryBalanceExcutor
   */
  public QueryBalanceExcutor setTaxAreaId(Long taxAreaId) {
    this.taxAreaId = taxAreaId;
    return this;
  }
  

  @Override
  protected String getTypeCode() {
    return "ralph.queryBalance";
  }

  @Override
  protected QueryBalanceVo wrapResponse(Map<String,Object> data) {
    QueryBalanceVo vo = new QueryBalanceVo();
    if(data != null) {
      vo.setAcctTotalBalance(BigDecimal.valueOf(MapUtils.getDouble(
          data, "acctTotalBalance", 0d)))
        .setAcctBalance(BigDecimal.valueOf(MapUtils.getDouble(
          data, "acctBalance", 0d)))
        .setAcctFreeze(BigDecimal.valueOf(MapUtils.getDouble(
            data, "acctFreeze", 0d)))
        .setAvailableBalance(BigDecimal.valueOf(MapUtils.getDouble(
            data, "availableBalance", 0d)));
    }
    return vo;
  }

  @Override
  protected boolean needSign() {
    return true;
  }

  @Override
  protected String signPropsIn() {
    return "companyCode,taxAreaId";
  }

}
