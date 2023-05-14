package com.devispora.ovo.edward;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.devispora.ovo.edward.models.ResponseModels;


public class EdwardApplication implements
    RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input,
      final Context context) {
    APIGatewayProxyResponseEvent response = ResponseModels.defaultHeaderResponseEvent();
    try {
      String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", "");
      return response
          .withStatusCode(200)
          .withBody(output);
    } catch (Exception e) {
      return response
          .withBody("{}")
          .withStatusCode(500);
    }
  }


}
