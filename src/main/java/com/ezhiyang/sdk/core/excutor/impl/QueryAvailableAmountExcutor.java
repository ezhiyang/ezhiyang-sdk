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
  
  /**
   * 额度查询参数列表
   */
  private List<QueryAvailableAmountParam> queryList;
  
  /**
   * 添加额度查询参数
   * @param param param
   * @return QueryAvailableAmountExcutor
   */
  public QueryAvailableAmountExcutor addQueryAvailableAmountParam(QueryAvailableAmountParam param) {
    if(queryList == null) {
      queryList = new LinkedList<QueryAvailableAmountExcutor.QueryAvailableAmountParam>();
    }
    queryList.add(param);
    return this;
  }
  
  /**
   * 获取额度查询参数列表
   * @return List
   */
  public List<QueryAvailableAmountParam> getQueryList() {
    return queryList;
  }
  
  /**
   * 设置额度查询参数列表
   * @param queryList List
   * @return QueryAvailableAmountExcutor
   */
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
          .setAvailableAmount(MapUtils.getDouble(d, "availableAmount") == null ? null : BigDecimal.valueOf(MapUtils.getDouble(
              d, "availableAmount")));
        ret.addQueryListVo(vo);
      }
    }
    return ret;
  }
  
  @Override
  protected boolean needSign() {
    return true;
  }

  @Override
  protected String signPropsIn() {
    return "[queryList,certNo,name]";
  }

  /**
   * 额度查询参数
   * @author Theo Zhou
   *
   */
  public static class QueryAvailableAmountParam implements Serializable{
    
    private static final long serialVersionUID = 3467606633311543192L;
    /**
     * 身份证
     */
    private String certNo;
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 获取身份证号
     * @return String
     */
    public String getCertNo() {
      return certNo;
    }

    /**
     * 设置身份证号
     * @param certNo certNo
     * @return QueryAvailableAmountParam
     */
    public QueryAvailableAmountParam setCertNo(String certNo) {
      this.certNo = certNo;
      return this;
    }

    /**
     * 获取姓名
     * @return String
     */
    public String getName() {
      return name;
    }

    /**
     * 设置姓名
     * @param name name
     * @return QueryAvailableAmountParam
     */
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
