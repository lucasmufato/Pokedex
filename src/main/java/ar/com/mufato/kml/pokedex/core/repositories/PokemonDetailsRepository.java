package ar.com.mufato.kml.pokedex.core.repositories;

import ar.com.mufato.kml.pokedex.core.domain.Details;
import ar.com.mufato.kml.pokedex.core.domain.Pokemon;
import java.util.Optional;

public interface PokemonDetailsRepository {

  Optional<Details> fetchDetailsFor(Pokemon pokemon);
}
