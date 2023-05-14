package com.devispora.ovo.edward.models;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class ResponseModels {

  public static APIGatewayProxyResponseEvent defaultHeaderResponseEvent() {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("X-Custom-Header", "application/json");

    return new APIGatewayProxyResponseEvent().withHeaders(headers);
  }

}
