package com.devispora.ovo.edward.models;

import org.junit.jupiter.api.Test;

public class SheetHelperTest {

  @Test
  public void hellokitty(){
    long that = 1679187118041L;
    var googleDateTime = createGoogleDateTime(that);
  }


  private com.google.api.client.util.DateTime createGoogleDateTime(long timeStamp){
    return new com.google.api.client.util.DateTime(timeStamp);
  }

}
