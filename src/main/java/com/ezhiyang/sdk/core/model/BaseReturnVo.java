package com.ezhiyang.sdk.core.model;

import java.io.Serializable;

/**
 * base return vo for the interfaces
 * @author Theo Zhou
 *
 */
public abstract class BaseReturnVo implements Serializable{

  private static final long serialVersionUID = 4921643239467041964L;

  private int code;
  
  private String msg;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public String toString() {
    return "BaseReturnVo [code=" + code + ", msg=" + msg + "]";
  }
  
  
  
}
