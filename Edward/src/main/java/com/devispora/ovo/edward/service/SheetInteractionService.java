package com.devispora.ovo.edward.service;

import com.devispora.ovo.edward.exception.AccountSheetException;
import com.devispora.ovo.edward.exception.AccountSheetExceptionMessage;
import com.devispora.ovo.edward.google.SheetInteractions;
import com.devispora.ovo.edward.models.AccountSheet;
import com.devispora.ovo.edward.models.ErredSheet;
import com.devispora.ovo.edward.models.helper.AccountSheetHelper;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SheetInteractionService {
  private final SheetInteractions sheetInteractions;
  private static final String DESIRED_RANGE = "B1:D5";

  public SheetInteractionService() throws IOException {
    this.sheetInteractions = new SheetInteractions();
  }

  public record ResultingSheets(List<AccountSheet> convertedSheets, List<ErredSheet> erredSheets){}

  public ResultingSheets retrieveSheetInformation(List<File> driveFiles){
    var convertedSheets = new ArrayList<AccountSheet>();
    var erredSheets = new ArrayList<ErredSheet>();
    for(File driveFile : driveFiles){
      String sheetId = driveFile.getId();
      String sheetName = driveFile.getName();
      String sheetParent = driveFile.getParents().get(0);
      try{
        var valueRange= sheetInteractions.retrieveSheetValuesByRange(sheetId, DESIRED_RANGE);
        var result = AccountSheetHelper.processAccountSheet(valueRange, sheetId, sheetName, sheetParent);
        if(result.success()){
          convertedSheets.add(result.goodSheet());
        }
      }
      catch (AccountSheetException e){
        erredSheets.add(new ErredSheet(sheetId, sheetName, sheetParent, e.getAccountSheetExceptionMessage()));
      }
      catch (Exception e){
        erredSheets.add(new ErredSheet(sheetId, sheetName, sheetParent,
            AccountSheetExceptionMessage.CantAccessDetails));
      }
    }
    return new ResultingSheets(convertedSheets, erredSheets);
  }


}
