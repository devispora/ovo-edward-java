package com.devispora.ovo.edward.google;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;

import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;

public class SheetInteractions {

  private final Sheets sheetService;

  public SheetInteractions() throws IOException {
    this.sheetService = ServiceBuilder.getSheetsService();
  }

  public String createSheet(String title) throws IOException {
    Spreadsheet spreadsheet = new Spreadsheet().setProperties(
        new SpreadsheetProperties().setTitle(title));
    spreadsheet = sheetService.spreadsheets()
        .create(spreadsheet)
        .setFields("spreadsheetId")
        .execute();
    return spreadsheet.getSpreadsheetId();
  }


  public ValueRange retrieveSheetValuesByRange(String sheetId, String desiredRanges) throws IOException {
    return sheetService.spreadsheets()
        .values().get(sheetId, desiredRanges).execute();
  }
}
