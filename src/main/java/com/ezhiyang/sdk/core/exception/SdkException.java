package com.ezhiyang.sdk.core.exception;

/**
 * sdk exception
 * @author Theo Zhou
 *
 */
public class SdkException extends RuntimeException{

  private static final long serialVersionUID = -8658881183783340520L;

  public SdkException() {
    super();
  }

  public SdkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public SdkException(String message, Throwable cause) {
    super(message, cause);
  }

  public SdkException(String message) {
    super(message);
  }

  public SdkException(Throwable cause) {
    super(cause);
  }

  
  
}
