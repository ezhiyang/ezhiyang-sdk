package com.ezhiyang.sdk.core.excutor.ret;

import com.ezhiyang.sdk.core.model.BaseReturnVo;

/**
 * 信息认证接口返回结果
 * @author Theo Zhou
 *
 */
public class PersonAuthVo extends BaseReturnVo{

  private static final long serialVersionUID = 5641726282828699170L;

  @Override
  public String toString() {
    return "PersonAuthVo [code=" + getCode() + ", msg=" + getMsg() + "]";
  }

  
}
