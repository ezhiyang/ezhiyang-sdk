package com.ezhiyang.sdk.core.exception;

/**
 * Decript exception
 * @author Theo Zhou
 *
 */
public class DecriptException extends RuntimeException{

  private static final long serialVersionUID = -8658881183783340520L;

  public DecriptException() {
    super();
  }

  public DecriptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public DecriptException(String message, Throwable cause) {
    super(message, cause);
  }

  public DecriptException(String message) {
    super(message);
  }

  public DecriptException(Throwable cause) {
    super(cause);
  }

  
  
}
