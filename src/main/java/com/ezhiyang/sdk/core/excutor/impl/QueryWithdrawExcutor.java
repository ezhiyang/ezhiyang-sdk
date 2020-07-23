package com.ezhiyang.sdk.core.excutor.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.ezhiyang.sdk.core.excutor.AbstractExcuteHandler;
import com.ezhiyang.sdk.core.excutor.ret.QueryWithdrawVo;
import com.ezhiyang.sdk.core.excutor.ret.QueryWithdrawVo.QueryListVo;

/**
 * 提现查询接口
 * @author Theo Zhou
 *
 */
public class QueryWithdrawExcutor extends AbstractExcuteHandler<QueryWithdrawVo,List<Map<String,Object>>>{

  private static final long serialVersionUID = 4510592217295189968L;
  
  private List<QueryWithdrawParam> queryList;
  
  public void addQueryWithdrawParam(QueryWithdrawParam param) {
    if(queryList == null) {
      queryList = new LinkedList<QueryWithdrawExcutor.QueryWithdrawParam>();
    }
    queryList.add(param);
  }
  
  public List<QueryWithdrawParam> getQueryList() {
    return queryList;
  }
  
  public QueryWithdrawExcutor setQueryList(List<QueryWithdrawParam> queryList) {
    this.queryList = queryList;
    return this;
  }

  @Override
  protected String getTypeCode() {
    return "ralph.queryWithdraw";
  }

  @Override
  protected QueryWithdrawVo wrapResponse(List<Map<String,Object>> data) {
    QueryWithdrawVo ret = new QueryWithdrawVo();
    if(data != null) {
      List<QueryListVo> l = new ArrayList<QueryListVo>();
      for(Map<String,Object> d : data) {
        QueryListVo vo = new QueryListVo();
        vo.setBizId(MapUtils.getString(d, "bizId"))
          .setStatus(MapUtils.getInteger(d, "status"))
          .setTaskNo(MapUtils.getString(d, "taskNo"));
        l.add(vo);
      }
      ret.setQueryList(l);
    }
    return ret;
  }

  public static class QueryWithdrawParam implements Serializable{
    
    private static final long serialVersionUID = 3467606633311543192L;
    private String bizId;
    private String taskNo;
    public String getBizId() {
      return bizId;
    }
    public QueryWithdrawParam setBizId(String bizId) {
      this.bizId = bizId;
      return this;
    }
    public String getTaskNo() {
      return taskNo;
    }
    public QueryWithdrawParam setTaskNo(String taskNo) {
      this.taskNo = taskNo;
      return this;
    }
    @Override
    public String toString() {
      return "QueryWithdrawParam [bizId=" + bizId + ", taskNo=" + taskNo + "]";
    }
    
    
    
  }

}
