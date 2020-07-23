package com.ezhiyang.sdk.core.excutor.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.ezhiyang.sdk.core.excutor.AbstractExcuteHandler;
import com.ezhiyang.sdk.core.excutor.ret.QueryAvailableAmountVo;
import com.ezhiyang.sdk.core.excutor.ret.QueryAvailableAmountVo.QueryListVo;
/**
 * 额度查询接口
 * @author Theo Zhou
 *
 */
public class QueryAvailableAmountExcutor extends AbstractExcuteHandler<QueryAvailableAmountVo,List<Map<String,Object>>>{

  private static final long serialVersionUID = 4510592217295189968L;
  
  private List<QueryAvailableAmountParam> queryList;
  
  public QueryAvailableAmountExcutor addQueryAvailableAmountParam(QueryAvailableAmountParam param) {
    if(queryList == null) {
      queryList = new LinkedList<QueryAvailableAmountExcutor.QueryAvailableAmountParam>();
    }
    queryList.add(param);
    return this;
  }
  
  public List<QueryAvailableAmountParam> getQueryList() {
    return queryList;
  }
  
  public QueryAvailableAmountExcutor setQueryList(List<QueryAvailableAmountParam> queryList) {
    this.queryList = queryList;
    return this;
  }

  @Override
  protected String getTypeCode() {
    return "ralph.queryAvailableAmount";
  }
  
  @Override
  protected QueryAvailableAmountVo wrapResponse(List<Map<String,Object>> data) {
    QueryAvailableAmountVo ret = new QueryAvailableAmountVo();
    if(data != null) {
      for(Map<String,Object> d : data) {
        QueryListVo vo = new QueryListVo();
        vo.setCertNo(MapUtils.getString(d, "certNo"))
          .setName(MapUtils.getString(d, "name"))
          .setStatus(MapUtils.getInteger(d, "status"))
          .setAvailableAmount(BigDecimal.valueOf(MapUtils.getDouble(
              d, "availableAmount", 0d)));
        ret.addQueryListVo(vo);
      }
    }
    return ret;
  }

  public static class QueryAvailableAmountParam implements Serializable{
    
    private static final long serialVersionUID = 3467606633311543192L;
    private String certNo;
    private String name;
    
    public String getCertNo() {
      return certNo;
    }

    public QueryAvailableAmountParam setCertNo(String certNo) {
      this.certNo = certNo;
      return this;
    }

    public String getName() {
      return name;
    }

    public QueryAvailableAmountParam setName(String name) {
      this.name = name;
      return this;
    }

    @Override
    public String toString() {
      return "QueryAvailableAmountParam [certNo=" + certNo + ", name=" + name + "]";
    }

    

  }

}
