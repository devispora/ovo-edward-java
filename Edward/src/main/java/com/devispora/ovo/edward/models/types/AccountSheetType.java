package com.devispora.ovo.edward.models.types;

public enum AccountSheetType {
  ObserverAccountType("Observer Accounts"),
  NormalAccountType("Basic Jaeger Accounts")
  ;

  private final String longName;

  AccountSheetType(String name){
    this.longName = name;
  }

  public String getLongName() {
    return longName;
  }
}
