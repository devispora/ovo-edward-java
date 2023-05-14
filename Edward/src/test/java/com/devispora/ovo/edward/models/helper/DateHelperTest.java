package com.devispora.ovo.edward.models.helper;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateHelperTest {

  @Test
  public void sheetIsFromThisYear_MatchesYear_correct(){
    String incomingSheet = "2023-04-02 Event";
    var year2023 = DateTime.parse("2023-04-02T00:00+02:00");
    var result = DateHelper.sheetIsFromThisYear(incomingSheet, year2023);
    Assertions.assertTrue(result);
  }

  @Test
  public void sheetIsFromThisYear_doesNotMatchYear_False(){
    String incomingSheet = "2024-04-02 Event";
    var year2023 = DateTime.parse("2023-04-02T00:00+02:00");
    var result = DateHelper.sheetIsFromThisYear(incomingSheet, year2023);
    Assertions.assertFalse(result);
  }

  @Test
  public void sheetIsFromThisYear_incorrectDate_False(){
    String incomingSheet = "202A-04-02 Event";
    var year2023 = DateTime.parse("2023-04-02T00:00+02:00");
    var result = DateHelper.sheetIsFromThisYear(incomingSheet, year2023);
    Assertions.assertFalse(result);
  }

  @Test
  public void fiveMinuteCoolDownPassed_DidPass_True(){
    var before = DateTime.parse("2023-04-02T00:00+02:00");
    var after = DateTime.parse("2023-04-02T00:06+02:00");
    var result = DateHelper.fiveMinuteCoolDownPassed(before, after);
    Assertions.assertTrue(result);
  }

  @Test
  public void fiveMinuteCoolDownPassed_DidPass_False(){
    var before = DateTime.parse("2023-04-02T00:00+02:00");
    var after = DateTime.parse("2023-04-02T00:04+02:00");
    var result = DateHelper.fiveMinuteCoolDownPassed(before, after);
    Assertions.assertFalse(result);
  }

}
