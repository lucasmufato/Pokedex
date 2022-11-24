package com.kml.pokedex.core.repositories;

import com.kml.pokedex.core.domain.Pokemon;
import java.util.List;

public interface PokemonsBatchRepository {

  List<Pokemon> getAllBatchPokemons();

}
