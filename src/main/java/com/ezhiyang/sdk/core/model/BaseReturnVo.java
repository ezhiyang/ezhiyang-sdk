package com.ezhiyang.sdk.core.model;

import java.io.Serializable;

/**
 * base return vo for the interfaces
 * @author Theo Zhou
 *
 */
public abstract class BaseReturnVo implements Serializable{

  private static final long serialVersionUID = 4921643239467041964L;

  /**
   * 返回码
   */
  private int code;
  
  /**
   * 消息
   */
  private String msg;

  /**
   * 返回码
   * @return int
   */
  public int getCode() {
    return code;
  }

  /**
   * 设置返回码
   * @param code code
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * 响应消息
   * @return String
   */
  public String getMsg() {
    return msg;
  }

  /**
   * 响应消息
   * @param msg msg
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public String toString() {
    return "BaseReturnVo [code=" + code + ", msg=" + msg + "]";
  }
  
  
  
}
