package com.devispora.ovo.edward.exception;

public class AccountSheetException extends Exception  {

  private AccountSheetExceptionMessage accountSheetExceptionMessage;
  public AccountSheetException(AccountSheetExceptionMessage message) {
    super(message.getExplanation());
    this.accountSheetExceptionMessage = message;
  }

  public AccountSheetExceptionMessage getAccountSheetExceptionMessage() {
    return accountSheetExceptionMessage;
  }
}
