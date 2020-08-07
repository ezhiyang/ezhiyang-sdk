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
  /**
   * 查询信息列表(列表项最大1000)
   */
  private List<QueryWithdrawParam> queryList;
  
  /**
   * 添加查询参数
   * @param param @see QueryWithdrawParam
   */
  public void addQueryWithdrawParam(QueryWithdrawParam param) {
    if(queryList == null) {
      queryList = new LinkedList<QueryWithdrawExcutor.QueryWithdrawParam>();
    }
    queryList.add(param);
  }
  
  /**
   * 获取查询参数列表
   * @return
   */
  public List<QueryWithdrawParam> getQueryList() {
    return queryList;
  }
  
  /**
   * 设置查询参数
   * @param queryList
   * @return QueryWithdrawExcutor
   */
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
          .setTaskNo(MapUtils.getString(d, "taskNo"))
          .setErrorCode(MapUtils.getString(d, "errorCode"))
          .setResultMsg(MapUtils.getString(d, "resultMsg"));
        l.add(vo);
      }
      ret.setQueryList(l);
    }
    return ret;
  }
  
  @Override
  protected boolean needSign() {
    return true;
  }

  @Override
  protected String signPropsIn() {
    return "[queryList,bizId,taskNo]";
  }

  /**
   * 查询信息参数
   * @author Theo Zhou
   *
   */
  public static class QueryWithdrawParam implements Serializable{
    
    private static final long serialVersionUID = 3467606633311543192L;
    /**
     * 客户提供的唯一提现ID；bizId与taskNo至少填一个
     */
    private String bizId;
    /**
     * 流水号；bizId与taskNo至少填一个
     */
    private String taskNo;
    
    /**
     * 客户提供的唯一提现ID；bizId与taskNo至少填一个
     * @return String
     */
    public String getBizId() {
      return bizId;
    }
    
    /**
     * 客户提供的唯一提现ID；bizId与taskNo至少填一个
     * @param bizId
     * @return QueryWithdrawParam
     */
    public QueryWithdrawParam setBizId(String bizId) {
      this.bizId = bizId;
      return this;
    }
    
    /**
     * 流水号；bizId与taskNo至少填一个
     * @return String
     */
    public String getTaskNo() {
      return taskNo;
    }
    
    /**
     * 流水号；bizId与taskNo至少填一个
     * @param taskNo taskNo
     * @return QueryWithdrawParam
     */
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
