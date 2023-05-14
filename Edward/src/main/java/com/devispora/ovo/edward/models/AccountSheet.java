package com.devispora.ovo.edward.models;

import com.devispora.ovo.edward.models.types.AccountSheetStatus;
import com.devispora.ovo.edward.models.types.AccountSheetType;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class AccountSheet extends SheetResult {

  private List<String> emails;
  private ZonedDateTime requestDate;
  private AccountSheetStatus sharedStatus;
  private AccountSheetType reservationType;

  public AccountSheet(String sheetId, String sheetName, String sheetParent) {
    super(sheetId, sheetName, sheetParent);
  }


  public AccountSheet(String sheetId, String sheetName, String sheetParent, List<String> emails,
      ZonedDateTime requestDate, AccountSheetStatus sharedStatus,
      AccountSheetType reservationType) {
    super(sheetId, sheetName, sheetParent);
    this.emails = emails;
    this.requestDate = requestDate;
    this.sharedStatus = sharedStatus;
    this.reservationType = reservationType;
  }

  public List<String> getEmails() {
    return emails;
  }

  public void setEmails(List<String> emails) {
    this.emails = emails;
  }

  public ZonedDateTime getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(ZonedDateTime requestDate) {
    this.requestDate = requestDate;
  }

  public AccountSheetStatus getSharedStatus() {
    return sharedStatus;
  }

  public void setSharedStatus(AccountSheetStatus sharedStatus) {
    this.sharedStatus = sharedStatus;
  }

  public AccountSheetType getReservationType() {
    return reservationType;
  }

  public void setReservationType(AccountSheetType reservationType) {
    this.reservationType = reservationType;
  }
}
