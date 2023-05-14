package com.devispora.ovo.edward.models;

import com.devispora.ovo.edward.models.types.RepType;
import java.util.List;

public class ContactSheet {

  private List<String> groups;
  private String characterName;
  private String email;
  private String discordHandle;
  private String discordId;
  private RepType repType;
  private String accountLimit;

  public ContactSheet(List<String> groups, String characterName, String email, String discordHandle,
      String discordId, RepType repType, String accountLimit) {
    this.groups = groups;
    this.characterName = characterName;
    this.email = email;
    this.discordHandle = discordHandle;
    this.discordId = discordId;
    this.repType = repType;
    this.accountLimit = accountLimit;
  }

  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(List<String> groups) {
    this.groups = groups;
  }

  public String getCharacterName() {
    return characterName;
  }

  public void setCharacterName(String characterName) {
    this.characterName = characterName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDiscordHandle() {
    return discordHandle;
  }

  public void setDiscordHandle(String discordHandle) {
    this.discordHandle = discordHandle;
  }

  public String getDiscordId() {
    return discordId;
  }

  public void setDiscordId(String discordId) {
    this.discordId = discordId;
  }

  public RepType getRepType() {
    return repType;
  }

  public void setRepType(RepType repType) {
    this.repType = repType;
  }

  public String getAccountLimit() {
    return accountLimit;
  }

  public void setAccountLimit(String accountLimit) {
    this.accountLimit = accountLimit;
  }
}
