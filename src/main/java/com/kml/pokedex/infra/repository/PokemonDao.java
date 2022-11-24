package com.kml.pokedex.infra.repository;

import com.kml.pokedex.core.domain.Pokemon;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PokemonDao {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;
  private String name;
  private String url;
  private String description;

  public PokemonDao() {
  }

  public PokemonDao(Integer id, String name, String url, String description) {
    this.id = id;
    this.name = name;
    this.url = url;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Pokemon toPokemon() {
    return new Pokemon(id, name, url, description);
  }
}
