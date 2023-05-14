package com.devispora.ovo.edward.service;

import static com.devispora.ovo.edward.models.helper.SheetHelper.filterByNameAndCoolDown;

import com.devispora.ovo.edward.exception.DriveException;
import com.devispora.ovo.edward.google.DriveInteractions;
import java.io.IOException;
import org.joda.time.DateTime;

public class ProcessingService {

  public void startProcessing() throws IOException, DriveException {
    var inter = new DriveInteractions();
    var result = inter.retrieveOvOFiles();
    var nowTime = new DateTime();
    var filteredFiles = filterByNameAndCoolDown(result,nowTime);

  }

}
