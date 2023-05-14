package com.devispora.ovo.edward.models;

public class SheetResult {
  private String sheetId;
  private String sheetName;
  private String sheetParent;

  public SheetResult(String sheetId, String sheetName, String sheetParent) {
    this.sheetId = sheetId;
    this.sheetName = sheetName;
    this.sheetParent = sheetParent;
  }

  public String getSheetId() {
    return sheetId;
  }

  public void setSheetId(String sheetId) {
    this.sheetId = sheetId;
  }

  public String getSheetName() {
    return sheetName;
  }

  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }

  public String getSheetParent() {
    return sheetParent;
  }

  public void setSheetParent(String sheetParent) {
    this.sheetParent = sheetParent;
  }
}
