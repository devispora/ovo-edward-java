package com.devispora.ovo.edward.google;

import com.devispora.ovo.edward.models.Constants;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.Sheets;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ServiceBuilder {

  public static final GsonFactory DEFAULT_FACTORY = GsonFactory.getDefaultInstance();

  public static List<String> provideRequiredScopes() {
    return List.of(
        DriveScopes.DRIVE,
        DriveScopes.DRIVE_FILE
    );
  }

  // Currently doing this as google has input stream focussed credential functions that are more easily documented
  public static GoogleCredentials createCredentials() throws IOException {
    InputStream targetStream = new ByteArrayInputStream(Constants.GOOGLE_CREDENTIALS.getBytes());
    return GoogleCredentials.fromStream(targetStream);
  }

  public static HttpRequestInitializer credentialHttpRequestInitializer() throws IOException {
    GoogleCredentials credentials = createCredentials().createScoped(provideRequiredScopes());
    return new HttpCredentialsAdapter(credentials);
  }

  public static Drive getDriveService() throws IOException {
    return new Drive.Builder(new NetHttpTransport(), DEFAULT_FACTORY,
        credentialHttpRequestInitializer())
        .setApplicationName(Constants.APPLICATION_NAME)
        .build();
  }

  public static Sheets getSheetsService() throws IOException {
    return new Sheets.Builder(new NetHttpTransport(), DEFAULT_FACTORY,
        credentialHttpRequestInitializer())
        .setApplicationName(Constants.APPLICATION_NAME)
        .build();

  }
}
