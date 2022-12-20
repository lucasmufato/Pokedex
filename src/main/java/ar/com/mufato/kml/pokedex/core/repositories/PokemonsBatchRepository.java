package ar.com.mufato.kml.pokedex.core.repositories;

import ar.com.mufato.kml.pokedex.core.domain.Pokemon;
import java.util.List;

public interface PokemonsBatchRepository {

  List<Pokemon> getAllBatchPokemons();

}
