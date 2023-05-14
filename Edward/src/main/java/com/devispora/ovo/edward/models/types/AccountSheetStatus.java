package com.devispora.ovo.edward.models.types;

public enum AccountSheetStatus {
  StatusCancelled("Cancelled"),
  StatusNotReady("Not Ready"),
  StatusReadyToShare("Ready to Share"),
  StatusManuallyShared("Manually Shared"),
  StatusShared("Shared")
  ;
  private final String longName;

  AccountSheetStatus(String name){
    this.longName = name;
  }

  public String getLongName() {
    return longName;
  }
}
