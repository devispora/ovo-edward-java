package com.devispora.ovo.edward.models.helper;

import com.devispora.ovo.edward.exception.AccountSheetException;
import com.devispora.ovo.edward.exception.AccountSheetExceptionMessage;
import com.devispora.ovo.edward.models.AccountSheet;
import com.devispora.ovo.edward.models.types.AccountSheetStatus;
import com.devispora.ovo.edward.models.types.AccountSheetType;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.joda.time.DateTime;

public class AccountSheetHelper {

  public record AccountSheetResult(AccountSheet goodSheet, AccountSheet erredSheet,
                                   boolean success) {

  }

  public static AccountSheetResult processAccountSheet(ValueRange sheetValues, String sheetId,
      String sheetName, String sheetParent) throws AccountSheetException {

    var requestDateTime = retrieveDate(sheetValues);
    var sharedStatus = retrieveSharedStatus(sheetValues);
    var reservationType = retrieveReservationType(sheetValues);
    try {
      var emails = retrieveEmails(sheetValues);
      var accountSheet = new AccountSheet(sheetId, sheetName, sheetParent, emails, requestDateTime,
          sharedStatus, reservationType);
      return new AccountSheetResult(accountSheet, null, true);
    } catch (AccountSheetException ase) {
      if (sharedStatus.equals(AccountSheetStatus.StatusManuallyShared)) {
        var accountSheet = new AccountSheet(sheetId, sheetName, sheetParent, List.of(),
            requestDateTime,
            sharedStatus, reservationType);
        return new AccountSheetResult(accountSheet, null, true);
      } else {
        throw ase;
      }
    }
  }

  private static ZonedDateTime retrieveDate(ValueRange sheetValues) throws AccountSheetException {
    try {
      var requestDate = sheetValues.getValues().get(1).get(0);
      var requestTime = sheetValues.getValues().get(2).get(0);
      try {
        var requestDateTime = LocalDateTime.parse(
            String.format(
                "%sT%s", requestDate, requestTime
            )
        );
        return ZonedDateTime.of(
            requestDateTime.toLocalDate(),
            requestDateTime.toLocalTime(),
            ZoneOffset.UTC
        );
      } catch (DateTimeParseException dse) {
        throw new AccountSheetException(AccountSheetExceptionMessage.RequestDateTimeNotISOFormat);
      }
    } catch (IndexOutOfBoundsException ie) {
      throw new AccountSheetException(AccountSheetExceptionMessage.RequestDateNotFound);
    }
  }

  public static List<String> retrieveEmails(ValueRange sheetValues) throws AccountSheetException {
    try {
      var potentialList = sheetValues.getValues().get(0).get(0);
      if (potentialList instanceof List) {
        return ((List<?>) potentialList).stream()
            .map(String.class::cast)
            .filter(Predicate.not(String::isBlank))
            .toList();
      } else if (potentialList instanceof String singleEmail) {
        if (!singleEmail.isBlank()) {
          return List.of(singleEmail);
        }
      }
    } catch (IndexOutOfBoundsException ie) {
      throw new AccountSheetException(AccountSheetExceptionMessage.EmailNotFound);
    } catch (ClassCastException ce) {
      throw new AccountSheetException(AccountSheetExceptionMessage.EmailCouldNotBeParsed);
    }
    throw new AccountSheetException(AccountSheetExceptionMessage.EmailCouldNotBeParsed);
  }

  public static AccountSheetStatus retrieveSharedStatus(ValueRange sheetValues)
      throws AccountSheetException {
    try {
      var sharedStatus = sheetValues.getValues().get(3).get(0);
      if (sharedStatus instanceof String) {
        return Arrays.stream(AccountSheetStatus.values())
            .filter(e -> e.getLongName().equals(sharedStatus))
            .findFirst().orElseThrow(() -> new AccountSheetException(
                AccountSheetExceptionMessage.SharedStatusNotRecognised));

      } else {
        throw new AccountSheetException(AccountSheetExceptionMessage.SharedStatusIssue);
      }
    } catch (IndexOutOfBoundsException ie) {
      throw new AccountSheetException(AccountSheetExceptionMessage.SharedStatusIssue);
    }
  }


  public static AccountSheetType retrieveReservationType(ValueRange sheetValues)
      throws AccountSheetException {
    try {
      var sharedStatus = sheetValues.getValues().get(4).get(0);
      if (sharedStatus instanceof String) {
        return Arrays.stream(AccountSheetType.values())
            .filter(e -> e.getLongName().equals(sharedStatus))
            .findFirst().orElseThrow(() -> new AccountSheetException(
                AccountSheetExceptionMessage.ReservationTypeNotRecognised));
      } else {
        throw new AccountSheetException(AccountSheetExceptionMessage.ReservationTypeIssue);
      }
    } catch (IndexOutOfBoundsException ie) {
      throw new AccountSheetException(AccountSheetExceptionMessage.ReservationTypeIssue);
    }

  }
}
