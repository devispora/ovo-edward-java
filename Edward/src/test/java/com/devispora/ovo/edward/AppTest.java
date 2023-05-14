package com.devispora.ovo.edward;

import static com.devispora.ovo.edward.models.helper.SheetHelper.filterByNameAndCoolDown;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.devispora.ovo.edward.exception.DriveException;
import com.devispora.ovo.edward.google.DriveInteractions;
import com.devispora.ovo.edward.models.AccountSheet;
import com.devispora.ovo.edward.service.SheetInteractionService;
import com.google.gson.Gson;
import java.time.ZonedDateTime;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


public class AppTest {
  @Test
  public void successfulResponse() {
    App app = new App();
    APIGatewayProxyResponseEvent result = app.handleRequest(null, null);
    Assertions.assertEquals(200, result.getStatusCode().intValue());
    Assertions.assertEquals("application/json", result.getHeaders().get("Content-Type"));
    String content = result.getBody();
    Assertions.assertNotNull(content);
    Assertions.assertTrue(content.contains("\"message\""));
    Assertions.assertTrue(content.contains("\"hello world\""));
    Assertions.assertTrue(content.contains("\"location\""));
  }

  @Test
  public void testingSheetFlow() throws IOException, DriveException {
    var inter = new DriveInteractions();
    var result = inter.retrieveOvOFiles();
    var nowTime = new DateTime();
    var resultingFiles = filterByNameAndCoolDown(result,nowTime);
    var sheetService = new SheetInteractionService();
    var result2 = sheetService.retrieveSheetInformation(resultingFiles);
    var currentTime = ZonedDateTime.now();
    for(AccountSheet sheet: result2.convertedSheets()){
      var indeed = sheet.getRequestDate().isBefore(currentTime);
      System.out.printf("It happened after is: %s%n",indeed);
    }
    Assertions.assertNotNull(result);
  }

  @Test
  public void testSheetShare() throws IOException, DriveException {
    var lolSheet = "1wHEqhUEQ3IMf0GFSlay9ktX4Z9MHaElYtkQ_Ps-1tfY";
    var lolEmail = "pronam.v@gmail.com";
    DriveInteractions driveInteractions = new DriveInteractions();
    driveInteractions.shareSheetsToUsers(lolSheet, List.of(lolEmail));
  }



}
