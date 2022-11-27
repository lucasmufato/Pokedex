package com.kml.pokedex.core.domain;

import com.kml.pokedex.core.domain.exceptions.InvalidPokemonException;

public record Pokemon(Integer id, String name, String url, String description) {

  public Pokemon {
    if(name == null || name.isBlank() ) throw new InvalidPokemonException("Name cant be null or empty");
    if(url == null || url.isBlank() ) throw new InvalidPokemonException("Url cant be null or empty");
    if(description == null || description.isBlank() ) throw new InvalidPokemonException("Description cant be null or empty");
  }
}
