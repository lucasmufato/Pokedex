package com.kml.pokedex.core.domain;


import com.kml.pokedex.core.domain.exceptions.InvalidPokemonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonTest {

  private Pokemon pokemon = null;
  private Exception exception = null;

  @Test
  void validateName() {
    whenInstantiatePokemonWith(1, "  ", "some url", "best pokemon");

    thenInvalidPokemonIsThrownWithMessage("Name cant be null or empty");
    thenPokemonHasNotBeingInstatiated();
  }

  @Test
  void validateUrl() {
    whenInstantiatePokemonWith(1, "pikachu", null, "best pokemon");

    thenInvalidPokemonIsThrownWithMessage("Url cant be null or empty");
    thenPokemonHasNotBeingInstatiated();
  }

  @Test
  void validateDescription() {
    whenInstantiatePokemonWith(1, "pikachu", "some url", "");

    thenInvalidPokemonIsThrownWithMessage("Description cant be null or empty");
    thenPokemonHasNotBeingInstatiated();
  }

  private void whenInstantiatePokemonWith(Integer id, String name, String url, String description) {
    try{
      pokemon = new Pokemon(id, name, url, description);
    }catch (Exception e){
      exception = e;
    }
  }

  private void thenPokemonIsInstantiated() {
    Assertions.assertNotNull(pokemon);
  }

  private void thenPokemonHasNotBeingInstatiated() {
    Assertions.assertNull(pokemon);
  }

  private void thenInvalidPokemonIsThrownWithMessage(String expectedMessage) {
    Assertions.assertNotNull(exception);
    Assertions.assertEquals(InvalidPokemonException.class, exception.getClass());
    Assertions.assertEquals(expectedMessage, exception.getMessage());
  }
}