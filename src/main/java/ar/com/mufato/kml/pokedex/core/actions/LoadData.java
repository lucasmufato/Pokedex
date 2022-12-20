package ar.com.mufato.kml.pokedex.core.actions;

import ar.com.mufato.kml.pokedex.core.repositories.PokemonsBatchRepository;
import ar.com.mufato.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.logging.Logger;

public class LoadData {

  private final Logger log = Logger.getLogger(LoadData.class.getName());
  private final PokemonsBatchRepository batchRepository;
  private final PokemonsRepository pokemonsRepository;

  public LoadData(PokemonsBatchRepository batchRepository,
      PokemonsRepository pokemonsRepository) {
    this.batchRepository = batchRepository;
    this.pokemonsRepository = pokemonsRepository;
  }

  public void invoke() {
    log.info("Loading data");
    this.batchRepository.getAllBatchPokemons().forEach(
        pokemon -> {
          log.info("Saving pokemon "+pokemon.name());
          pokemonsRepository.save(pokemon);
        });
    log.info("Finished Loading data");
  }

}
