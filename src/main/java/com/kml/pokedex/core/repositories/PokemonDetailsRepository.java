package com.kml.pokedex.core.repositories;

import com.kml.pokedex.core.domain.Details;
import com.kml.pokedex.core.domain.Pokemon;
import java.util.Optional;

public interface PokemonDetailsRepository {

  Optional<Details> fetchDetailsFor(Pokemon pokemon);
}
