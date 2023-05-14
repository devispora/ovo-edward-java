package com.devispora.ovo.edward.models.types;

public enum GoogleMimeType {
  Sheet("application/vnd.google-apps.spreadsheet"),
  Folder("application/vnd.google-apps.folder")
  ;

  private final String longName;

  GoogleMimeType(String name) {
    this.longName = name;
  }

  public String getLongName() {
    return longName;
  }
}
