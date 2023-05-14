package com.devispora.ovo.edward.models.helper;

import static com.devispora.ovo.edward.models.helper.DateHelper.convertGoogleToJodaTime;

import com.google.api.services.drive.model.File;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class SheetHelper {

  public static List<File> filterByNameAndCoolDown(List<File> incomingFiles, DateTime currentDate) {
    var result = new ArrayList<File>();
    for (File file : incomingFiles) {
      DateTime jodaTime = convertGoogleToJodaTime(file.getCreatedTime());
      if (DateHelper.sheetIsFromThisYear(file.getName(), currentDate)) {
        if (DateHelper.fiveMinuteCoolDownPassed(jodaTime, currentDate)) {
          result.add(file);
        }
      }
    }
    return result;
  }

}
