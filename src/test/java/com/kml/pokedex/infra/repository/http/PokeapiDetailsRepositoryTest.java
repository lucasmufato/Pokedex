package com.kml.pokedex.infra.repository.http;

import static com.kml.pokedex.PokemonsFactory.pikachu;

import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.domain.Details;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokeapiDetailsRepositoryTest {

  private final PokeapiDetailsRepository pokeapiRepo = new PokeapiDetailsRepository();

  @Test
  void pokeapiContractTest() {
    Optional<Details> details = pokeapiRepo.fetchDetailsFor(pikachu);
    Assertions.assertTrue(details.isPresent());
  }

  @Test
  void pokeapiCorrectlyMapsDetails() {
    Optional<Details> details = pokeapiRepo.fetchDetailsFor(pikachu);
    Details expectedDetails = new Details(112,4, 60, List.of("static", "lightning-rod"));
    Assertions.assertEquals(expectedDetails, details.get());
  }

  @Test
  void withFakePokemonReturnsEmpty() {
    Pokemon fakePokemon = new Pokemon(1234,"fakechu","https://pokeapi.co/api/", "fake");
    Optional<Details> details = pokeapiRepo.fetchDetailsFor(fakePokemon);
    Assertions.assertTrue(details.isEmpty());
  }
}