package ar.com.mufato.kml.pokedex.core.actions;

import static ar.com.mufato.kml.pokedex.PokemonsFactory.pikachu;
import static ar.com.mufato.kml.pokedex.PokemonsFactory.raychu;

import ar.com.mufato.kml.pokedex.core.domain.Pokemon;
import ar.com.mufato.kml.pokedex.core.repositories.PokemonsBatchRepository;
import ar.com.mufato.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LoadDataTest {

  private final PokemonsBatchRepository batchRepository = Mockito.mock(PokemonsBatchRepository.class);
  private final PokemonsRepository pokemonsRepository = Mockito.mock(PokemonsRepository.class);
  private final LoadData loadData = new LoadData(batchRepository, pokemonsRepository);


  @Test
  void migratesFromBatchToFinalRepository() {
    givenABatchRepositoryReturning(List.of(pikachu, raychu));

    whenLoadData();

    thenPokemonRepositoryIsCalledWith(List.of(pikachu, raychu));
  }

  private void givenABatchRepositoryReturning(List<Pokemon> pokemons) {
    Mockito.when(batchRepository.getAllBatchPokemons()).thenReturn(pokemons);
  }

  private void whenLoadData() {
    loadData.invoke();
  }

  private void thenPokemonRepositoryIsCalledWith(List<Pokemon> pokemons) {
    pokemons.forEach(pokemon ->
        Mockito.verify(pokemonsRepository).save(pokemon)
    );
  }
}