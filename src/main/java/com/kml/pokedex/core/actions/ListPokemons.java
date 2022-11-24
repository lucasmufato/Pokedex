package com.kml.pokedex.core.actions;

import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.List;

public class ListPokemons {

  private final PokemonsRepository pokemonsRepository;
  public ListPokemons(PokemonsRepository pokemonsRepository) {
    this.pokemonsRepository = pokemonsRepository;
  }

  public List<Pokemon> invoke() {
    return pokemonsRepository.findAll();
  }
}
