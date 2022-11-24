package com.kml.pokedex.infra.batch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchPokemon {

  private String name;
  private String url;
  private String description;

  public BatchPokemon() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public String getDescription() {
    return description;
  }

}
