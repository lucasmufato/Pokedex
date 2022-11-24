package com.kml.pokedex.core.actions;


import static com.kml.pokedex.PokemonsFactory.pikachu;

import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.domain.exceptions.InvalidNameFilter;
import com.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListPokemonsFilteringByNameTest {

  private final PokemonsRepository pokemonsRepository = Mockito.mock(PokemonsRepository.class);
  private final ListPokemonsFilteringByName listPokemons = new ListPokemonsFilteringByName(pokemonsRepository);
  private List<Pokemon> listedPokemons;
  private Exception exception;

  @Test
  void throwErrorIfFilterHasLessThan3Letters() {
    // no given needed

    whenListPokemonsWhereNameIsLike("sa");

    thenThrownExceptionIs(InvalidNameFilter.class);
  }

  @Test
  void callsRepositoryPassingFilter() {
    givenARepositoryReturningPokemonsForName("pika", List.of(pikachu));

    whenListPokemonsWhereNameIsLike("pika");

    thenListedPokemonsAre(List.of(pikachu));
  }

  private void givenARepositoryReturningPokemonsForName(String name, List<Pokemon> pokemons) {
    Mockito.when(pokemonsRepository.findAllWithNameLike(name)).thenReturn(pokemons);
  }

  private void whenListPokemonsWhereNameIsLike(String queryFilter) {
    try{
      listedPokemons = listPokemons.invoke(queryFilter);
    }catch (Exception e){
      exception = e;
    }
  }

  private void thenThrownExceptionIs(Class<InvalidNameFilter> expectedException) {
    Assertions.assertEquals(exception.getClass(), expectedException);
  }

  private void thenListedPokemonsAre(List<Pokemon> expectedPokemosn) {
    Assertions.assertEquals(expectedPokemosn, listedPokemons);
  }

}