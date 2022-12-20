package ar.com.mufato.kml.pokedex.core.actions;


import static ar.com.mufato.kml.pokedex.PokemonsFactory.pikachu;
import static ar.com.mufato.kml.pokedex.PokemonsFactory.raychu;

import ar.com.mufato.kml.pokedex.core.domain.Pokemon;
import ar.com.mufato.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListPokemonsTest {

  private final PokemonsRepository pokemonsRepository = Mockito.mock(PokemonsRepository.class);
  private final ListPokemons listPokemons = new ListPokemons(pokemonsRepository);
  private List<Pokemon> listedPokemons;

  @Test
  void getAllPokemonsFromRepository() {
    givenARepositoryContaining(List.of(pikachu, raychu));

    whenListPokemons();

    thenListedPokemonsAre(List.of(pikachu, raychu));
  }

  private void givenARepositoryContaining(List<Pokemon> pokemons) {
    Mockito.when(pokemonsRepository.findAll()).thenReturn(pokemons);
  }

  private void whenListPokemons() {
    listedPokemons = listPokemons.invoke();
  }

  private void thenListedPokemonsAre(List<Pokemon> expectedList) {
    Assertions.assertEquals(expectedList, listedPokemons);
  }

}