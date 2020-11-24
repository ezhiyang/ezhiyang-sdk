package com.ezhiyang.sdk.core.exception;

/**
 * verify exception
 * @author Theo Zhou
 *
 */
public class VerifyException extends RuntimeException{

  private static final long serialVersionUID = -8658881183783340520L;

  public VerifyException() {
    super();
  }

  public VerifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public VerifyException(String message, Throwable cause) {
    super(message, cause);
  }

  public VerifyException(String message) {
    super(message);
  }

  public VerifyException(Throwable cause) {
    super(cause);
  }

  
  
}
