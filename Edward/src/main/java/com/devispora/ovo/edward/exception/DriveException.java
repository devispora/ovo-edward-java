package com.devispora.ovo.edward.exception;


public class DriveException extends Exception {

  public DriveException(DriveExceptionMessage message) {
    super(message.getExplanation());
  }
}
