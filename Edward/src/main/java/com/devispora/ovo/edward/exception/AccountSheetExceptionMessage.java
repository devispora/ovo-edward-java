package com.devispora.ovo.edward.exception;

public enum AccountSheetExceptionMessage {
  CantAccessDetails("Could not communicate with sheetService"),
  EmailNotFound("No email has been supplied"),
  EmailNeverMatched("A supplied email could not be found in the rep sheet, could not share this sheet at all"),
  EmailNotFoundInRepSheet("One of the emails could not be found in the rep sheet, but shared to the rest anyway"),
  EmailCouldNotBeParsed("Encountered an issue trying to parse the email section"),
  RequestDateNotFound("No date could be found where it was expected"),
  RequestDateCouldNotBeParsed("Encountered an issue trying to parse the String at request date into a date object"),
  RequestDateTimeNotISOFormat("The requested datetime was not in the ISO format of YYYY-MM-DD and 00:00"),
  GoogleMightHaveChangedDateFormat("The created sheet date format changed from what was expected"),
  SharedStatusIssue("Encountered an issue trying to retrieve the shared status"),
  SharedStatusNotRecognised("Encountered a shared status that the system does not recognise"),
  SharedStatusIsNotCleared("The sheet status of this sheet has not been correctly updated since its creation"),
  ReservationTypeIssue("The reservation type could not be retrieved"),
  ReservationTypeNotRecognised("The reservation type did not match the expected obs/account variants")
  ;

  private final String explanation;

  AccountSheetExceptionMessage(String explanation) {
    this.explanation = explanation;
  }

  public String getExplanation() {
    return explanation;
  }
}
