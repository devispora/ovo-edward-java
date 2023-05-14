package com.devispora.ovo.edward.google;

import com.devispora.ovo.edward.exception.DriveException;
import com.devispora.ovo.edward.exception.DriveExceptionMessage;
import com.devispora.ovo.edward.models.Constants;
import com.devispora.ovo.edward.models.types.GoogleMimeType;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DriveInteractions {

  private final Drive driveService;

  public DriveInteractions() throws IOException {
    this.driveService = ServiceBuilder.getDriveService();
  }

  public List<File> retrieveOvOFiles() throws IOException, DriveException {
    List<File> files = new ArrayList<>();
    String pageToken = null;
    do {
      FileList result = driveService.files().list()
          .setQ(String.format("'%s' in parents and mimeType = '%s'", Constants.OVO_DRIVE_FOLDER,
              GoogleMimeType.Sheet.getLongName()))
          .setSpaces("drive")
          .setFields("nextPageToken, files(id, name, mimeType, createdTime, parents)")
          .setPageToken(pageToken)
          .setPageSize(30)
          .execute();
      for (File googleFile : result.getFiles()) {
        System.out.printf("Found file: %s (%s)\n",
            googleFile.getName(), googleFile.getId());
      }
      files.addAll(result.getFiles());
      pageToken = result.getNextPageToken();
    } while (pageToken != null);
    if (files.isEmpty()) {
      throw new DriveException(DriveExceptionMessage.NoFilesFound);
    }
    return files;
  }

  public void shareSheetsToUsers(String spreadsheetId, List<String> emails) throws DriveException {
    List<String> errors = new ArrayList<>();
    JsonBatchCallback<Permission> callback = createCallBack(errors);
    BatchRequest batch = driveService.batch();
    try {
      for (String email : emails) {
        driveService.permissions()
            .create(spreadsheetId, createEmailPermission(email))
            .queue(batch, callback);
        //todo consider adding addFields later with emails to get them back on callback, but might be big performance no-no.
      }
      batch.execute();
    } catch (IOException e) {
      throw new DriveException(DriveExceptionMessage.HttpError);
    }
    if (!errors.isEmpty()) {
      for (String error : errors) {
        System.out.println(error);
      }
      throw new DriveException(DriveExceptionMessage.ShareIssue);
    }
  }

  public Permission createEmailPermission(String email) {
    return new Permission()
        .setType("user")
        .setRole("writer")
        .setEmailAddress(email);
  }


  public JsonBatchCallback<Permission> createCallBack(List<String> errors) {
    return new JsonBatchCallback<>() {
      @Override
      public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) throws IOException {
        errors.add(e.getMessage());
      }

      @Override
      public void onSuccess(Permission permission, HttpHeaders responseHeaders) throws IOException {
      }
    };
  }


}


