package com.devispora.ovo.edward.models.helper;

import com.devispora.ovo.edward.exception.AccountSheetException;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountSheetHelperTest {

  @Test
  public void retrieveEmails_expectingTwoEntries_True() throws AccountSheetException {
    var values = new ValueRange();
    List<List<Object>> nestedList = new ArrayList<>();
    nestedList.add(List.of(List.of("email", "secondEmail", "", " ")));
    values.setValues(nestedList);
    var result = AccountSheetHelper.retrieveEmails(values);
    Assertions.assertEquals(2, result.size());
  }

  @Test
  public void retrieveEmails_expectingNoEntries_True() throws AccountSheetException {
    var values = new ValueRange();
    List<List<Object>> nestedList = new ArrayList<>();
    nestedList.add(List.of(List.of("", " ")));
    values.setValues(nestedList);
    var result = AccountSheetHelper.retrieveEmails(values);
    Assertions.assertEquals(0, result.size());
  }

}
