/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland;

public class FairyException extends RuntimeException {

  public FairyException(String message) {
    super(message);
  }

  public FairyException(String message, Throwable cause) {
    super(message, cause);
  }

}
