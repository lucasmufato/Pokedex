package com.kml.pokedex.core.domain;

import java.util.List;
import java.util.Optional;

public class PokemonDetails {

  private final Pokemon pokemon;
  private final Optional<Details> details;

  public PokemonDetails(Pokemon pokemon, Optional<Details> details) {
    this.pokemon = pokemon;
    this.details = details;
  }

  public Integer getId(){
    return pokemon.id();
  }
  public String getName(){return pokemon.name();}
  public String getDescription(){return pokemon.description();}
  public Optional<Integer> getHeight(){return details.map(Details::height);}
  public Optional<Integer> getWeight(){return details.map(Details::weight);}
  public Optional<List<String>> getAbilities(){return details.map(Details::abilities);}
  public Optional<Integer> getBaseExperience(){
    return details.map(Details::baseExperience);
  }


}
