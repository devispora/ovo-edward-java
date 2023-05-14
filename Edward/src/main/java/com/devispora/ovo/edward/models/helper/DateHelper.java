package com.devispora.ovo.edward.models.helper;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class DateHelper {

  public static boolean sheetIsFromThisYear(String sheetName, DateTime currentDate) {
    var strippedYear = sheetName.substring(0, 4);
    try {
      return currentDate.getYear() == Integer.parseInt(strippedYear);
    } catch (NumberFormatException ne) {
      return false;
    }
  }

  public static DateTime convertGoogleToJodaTime(com.google.api.client.util.DateTime googleTime){
    long googleLong = googleTime.getValue();
    return new DateTime(googleLong);
  }

  public static boolean fiveMinuteCoolDownPassed(DateTime sheetCreationDate, DateTime currentDate){
    var difference = Minutes.minutesBetween(sheetCreationDate, currentDate).getMinutes();
    return  difference > 5;
  }

}
