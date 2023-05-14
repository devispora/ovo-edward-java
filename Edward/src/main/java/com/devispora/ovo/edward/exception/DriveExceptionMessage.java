package com.devispora.ovo.edward.exception;

public enum DriveExceptionMessage {
  ShareIssue("Encountered an issue trying to tell google to share the file"),
  NoFilesFound("Not a single file could be accessed at the assigned folder"),
  HttpError(
      "'Encountered an error trying to communicate with Google Drive, check the logs for more info"),
  MonthArchiveNotFound("Having trouble finding the archive folder of the a month"),
  YearArchiveNotFound("Having trouble finding the archive folder of the current year");
  private final String explanation;

  DriveExceptionMessage(String explanation) {
    this.explanation = explanation;
  }

  public String getExplanation() {
    return explanation;
  }
}
