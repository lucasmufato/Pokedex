package ar.com.mufato.kml.pokedex.core.actions;

import ar.com.mufato.kml.pokedex.core.domain.Pokemon;
import ar.com.mufato.kml.pokedex.core.repositories.PokemonsRepository;
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
