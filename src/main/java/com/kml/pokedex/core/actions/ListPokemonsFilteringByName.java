package com.kml.pokedex.core.actions;

import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.domain.exceptions.InvalidNameFilter;
import com.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.List;

public class ListPokemonsFilteringByName {

  private final PokemonsRepository pokemonsRepository;

  public ListPokemonsFilteringByName(PokemonsRepository pokemonsRepository) {
    this.pokemonsRepository = pokemonsRepository;
  }

  public List<Pokemon> invoke(String name) {
    if(name.trim().length()<3) throw new InvalidNameFilter("Filter must have more that 3 letter");
    return pokemonsRepository.findAllWithNameLike(name);
  }

}
