package com.devispora.ovo.edward.models.types;

public enum RepType {
  OutfitRep("Outfit Rep"),
  CommunityRep("Community Rep"),
  ScrimTeam("Scrim Team"),
  ObserverUser("Observer User"),
  ;
  private final String longName;

  RepType(String name) {
    this.longName = name;
  }

  public String getLongName() {
    return longName;
  }
}
