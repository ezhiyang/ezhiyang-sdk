package com.ezhiyang.sdk.core.excutor.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.ezhiyang.sdk.core.excutor.AbstractExcuteHandler;
import com.ezhiyang.sdk.core.excutor.ret.QueryAccountDetailVo;
import com.ezhiyang.sdk.core.excutor.ret.QueryAccountDetailVo.EntityListVo;
/**
 * 查询账户明细接口
 * @author Theo Zhou
 *
 */
public final class QueryAccountDetailExcutor extends AbstractExcuteHandler<QueryAccountDetailVo,Map<String,Object>>{

  private static final long serialVersionUID = -4984012293112519874L;
  private static final String ENTITY_LIST = "entityList";
  /**
   * 企业标识
   */
  private String companyCode;
  /**
   * 落地区域ID
   */
  private Long taxAreaId;
  /**
   * 交易时间下限(yyyy-MM-dd HH:mm:ss)，最大区间三个月
   */
  private Date queryStartDate;
  /**
   * 交易时间上限(yyyy-MM-dd HH:mm:ss)，最大区间三个月
   */
  private Date queryEndDate;
  /**
   * 每页条数
   */
  private Integer pageSize;
  /**
   * 当前页
   */
  private Integer pageNo;
  

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
   * @return QueryAccountDetailExcutor
   */
  public QueryAccountDetailExcutor setCompanyCode(String companyCode) {
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
   * @param taxAreaId taxAreaId
   * @return QueryAccountDetailExcutor
   */
  public QueryAccountDetailExcutor setTaxAreaId(Long taxAreaId) {
    this.taxAreaId = taxAreaId;
    return this;
  }

  /**
   * 交易时间下限(yyyy-MM-dd HH:mm:ss)，最大区间三个月
   * @return Date
   */
  public Date getQueryStartDate() {
    return queryStartDate;
  }

  /**
   * 交易时间下限(yyyy-MM-dd HH:mm:ss)，最大区间三个月
   * @param queryStartDate queryStartDate
   * @return QueryAccountDetailExcutor
   */
  public QueryAccountDetailExcutor setQueryStartDate(Date queryStartDate) {
    this.queryStartDate = queryStartDate;
    return this;
  }

  /**
   * 交易时间上限(yyyy-MM-dd HH:mm:ss)，最大区间三个月
   * @return Date
   */
  public Date getQueryEndDate() {
    return queryEndDate;
  }

  /**
   * 交易时间上限(yyyy-MM-dd HH:mm:ss)，最大区间三个月
   * @param queryEndDate queryEndDate
   * @return QueryAccountDetailExcutor
   */
  public QueryAccountDetailExcutor setQueryEndDate(Date queryEndDate) {
    this.queryEndDate = queryEndDate;
    return this;
  }

  /**
   * 每页条数
   * @return Integer
   */
  public Integer getPageSize() {
    return pageSize;
  }

  /**
   * 每页条数
   * @param pageSize pageSize
   * @return QueryAccountDetailExcutor
   */
  public QueryAccountDetailExcutor setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 当前页
   * @return Integer
   */
  public Integer getPageNo() {
    return pageNo;
  }

  /**
   * 当前页
   * @param pageNo pageNo
   * @return QueryAccountDetailExcutor
   */
  public QueryAccountDetailExcutor setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
    return this;
  }

  @Override
  public String toString() {
    return "QueryAccountDetailExcutor [companyCode=" + companyCode + ", taxAreaId=" + taxAreaId + ", queryStartDate="
        + queryStartDate + ", queryEndDate=" + queryEndDate + ", pageSize=" + pageSize + ", pageNo=" + pageNo + "]";
  }

  @Override
  protected String getTypeCode() {
    return "ralph.queryAccountDetail";
  }
  
  @Override
  protected QueryAccountDetailVo wrapResponse(Map<String,Object> data) {
    QueryAccountDetailVo vo = new QueryAccountDetailVo();
    if(data != null) {
      vo.setPageSize(MapUtils.getInteger(data, "pageSize"))
      .setPageNo(MapUtils.getInteger(data, "pageNo"))
      .setPageCount(MapUtils.getInteger(data, "pageCount"))
      .setTotalCount(MapUtils.getLong(data, "pageCount"));
      if(data.get(ENTITY_LIST) != null) {
        List<Map<String,Object>> entityList = (List<Map<String,Object>>)data.get("entityList");
        for(Map<String,Object> entity: entityList) {
          EntityListVo entityVo = new EntityListVo();
          entityVo.setTransTime(entity.get("transTime") == null ? null : parseDate(entity.get("transTime").toString()))
          .setDcFlag(MapUtils.getInteger(entity, "dcFlag"))
          .setTransAmount(BigDecimal.valueOf(MapUtils.getDouble(
              entity, "transAmount", 0d)))
          .setAcctTotalBalance(BigDecimal.valueOf(MapUtils.getDouble(
              entity, "acctTotalBalance", 0d)))
          .setAcctBalance(BigDecimal.valueOf(MapUtils.getDouble(
              entity, "acctBalance", 0d)))
          .setAcctFreeze(BigDecimal.valueOf(MapUtils.getDouble(
              entity, "acctFreeze", 0d)))
          .setRemark(MapUtils.getString(entity, "remark"));
          vo.addEntityVo(entityVo);
        }
      }
    }
    return vo;
  }
  
  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static Date parseDate(String str) {
    try {
      return sdf.parse(str);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected boolean needSign() {
    return true;
  }

  @Override
  protected String signPropsIn() {
    return "companyCode,taxAreaId,queryStartDate,queryEndDate";
  }
  
}
