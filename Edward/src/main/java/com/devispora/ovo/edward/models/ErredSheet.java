package com.devispora.ovo.edward.models;

import com.devispora.ovo.edward.exception.AccountSheetExceptionMessage;

public class ErredSheet extends SheetResult{

  private AccountSheetExceptionMessage message;

  public ErredSheet(String sheetId, String sheetName, String sheetParent, AccountSheetExceptionMessage message) {
    super(sheetId, sheetName, sheetParent);
    this.message = message;
  }
}
