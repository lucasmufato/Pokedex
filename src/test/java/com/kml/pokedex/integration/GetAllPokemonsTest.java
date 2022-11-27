package com.kml.pokedex.integration;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kml.pokedex.infra.repository.PokemonCrudRepository;
import com.kml.pokedex.infra.repository.PokemonDao;
import com.kml.pokedex.infra.repository.http.AbilityDescription;
import com.kml.pokedex.infra.repository.http.PokeapiAbilities;
import com.kml.pokedex.infra.repository.http.PokeapiDetails;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class GetAllPokemonsTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PokemonCrudRepository pokemonCrudRepository;
  @MockBean
  private RestTemplate restTemplate;

  private ResultActions perform;
  private final PokemonDao pikachu = new PokemonDao(1, "pika", "url", "descr");
  private final String pikachuDetails = "";
  private final List<PokemonDao> pokemons = List.of(pikachu);
  private final PokeapiDetails PikachuDetailsBody = new PokeapiDetails( List.of(new PokeapiAbilities(new AbilityDescription("dodge"))),35, 60, 112);
  private final PokeapiDetails pokemonDetailsBody = new PokeapiDetails( List.of(new PokeapiAbilities(new AbilityDescription("drought"))),55, 5000, 100);

  @Test
  void getPokemonDetailsWithCalculatedExperience() throws Exception {
    givenAPokemonRepositoryReturning(1, pikachu);
    givenARestTemplateReturning(ResponseEntity.ok(pokemonDetailsBody));

    whenGetPokemonDetailsById("/pokemon/1");

    thenResponseStatusIsOK();
    thenExperienceIs("\"baseExperience\":128,");
  }


  @Test
  void getFullDetails() throws Exception {
    givenAPokemonRepositoryReturning(1, pikachu);
    givenARestTemplateReturning(ResponseEntity.ok(PikachuDetailsBody));

    whenGetPokemonDetailsById("/pokemon/1");

    thenResponseStatusIsOK();
    thenResponseBodyIs("{\"id\":1,\"name\":\"pika\",\"description\":\"descr\",\"height\":35,\"weight\":60,\"baseExperience\":112,\"abilities\":[\"dodge\"]}");
  }

  @Test
  void cantGetFullDetails() throws Exception {
    givenAPokemonRepositoryReturning(1, pikachu);
    givenARestTemplateReturning(ResponseEntity.notFound().build());

    whenGetPokemonDetailsById("/pokemon/1");

    thenResponseStatusIsOK();
    thenResponseBodyIs("{\"id\":1,\"name\":\"pika\",\"description\":\"descr\"}");
  }

  @Test
  void returns404WhenPokemonDoesntExists() throws Exception {
    givenAPokemonRepositoryReturning(1, null);

    whenGetPokemonDetailsById("/pokemon/1");

    thenResponseStatusIs404NotFound();
  }

  private void givenARestTemplateReturning(ResponseEntity<PokeapiDetails> response) {
    Mockito.when(restTemplate.exchange(pikachu.getUrl(), HttpMethod.GET, null, PokeapiDetails.class))
        .thenReturn(response);
  }

  private void givenAPokemonRepositoryReturning(Integer id, PokemonDao pokemon) {
    Mockito.when(pokemonCrudRepository.findById(id)).thenReturn(Optional.ofNullable(pokemon));
  }

  private void whenGetPokemonDetailsById(String urlTemplate) throws Exception {
    perform = mockMvc.perform(get(urlTemplate));
  }

  private void thenResponseBodyIs(String expectedBody) throws Exception {
    perform.andExpect(content().string(containsString(expectedBody)));
  }

  private void thenResponseStatusIsOK() throws Exception {
    perform.andExpect(status().isOk());
  }

  private void thenExperienceIs(String experienceJson) throws Exception {
    perform.andExpect(content().string(containsString(experienceJson)));
  }

  private void thenResponseStatusIs404NotFound() throws Exception {
    perform.andExpect(status().isNotFound());
  }


}
