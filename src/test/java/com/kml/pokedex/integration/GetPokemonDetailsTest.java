package com.kml.pokedex.integration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kml.pokedex.infra.repository.PokemonCrudRepository;
import com.kml.pokedex.infra.repository.PokemonDao;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPokemonDetailsTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PokemonCrudRepository pokemonCrudRepository;

  private ResultActions perform;
  private final List<PokemonDao> pokemons = List.of(new PokemonDao(1, "pika", "url", "descr"));

  @Test
  void getPokedex() throws Exception {

    givenAPokemonRepositoryWith(pokemons);

    whenMakeAGetTo("/pokedex");

    thenResponseStatusIsOK();
    thenResponseBodyIs("[{\"id\":1,\"name\":\"pika\"}]");
  }


  @Test
  void getExpandedPokedex() throws Exception {
    givenAPokemonRepositoryWith(pokemons);

    whenMakeAGetTo("/pokedex?expand=description");

    thenResponseStatusIsOK();
    thenResponseBodyIs("[{\"id\":1,\"name\":\"pika\",\"description\":\"descr\"}]");
  }

  @Test
  void getPokemonByname() throws Exception {
    givenAPokemonRepositoryWith(pokemons);

    whenMakeAGetTo("/pokemon?query=pika");

    thenResponseStatusIsOK();
    thenResponseBodyIs("[{\"id\":1,\"name\":\"pika\"}]");
  }

  @Test
  void getExpandedPokemonByname() throws Exception {
    givenAPokemonRepositoryWith(pokemons);

    whenMakeAGetTo("/pokemon?query=pika&expand=description");

    thenResponseStatusIsOK();
    thenResponseBodyIs("[{\"id\":1,\"name\":\"pika\",\"description\":\"descr\"}]");
  }



  @Test
  void getPokemonWithoutName() throws Exception {
    givenAPokemonRepositoryWith(pokemons);

    whenMakeAGetTo("/pokemon");

    thenResponseStatusId404NotFound();
  }


  private void givenAPokemonRepositoryWith(List<PokemonDao> pokemons) {
    Mockito.when(pokemonCrudRepository.findAll()).thenReturn(pokemons);
    Mockito.when(pokemonCrudRepository.findAllWithNameLike(Mockito.any())).thenReturn(pokemons);
  }

  private void whenMakeAGetTo(String urlTemplate) throws Exception {
    perform = mockMvc.perform(get(urlTemplate));
  }

  private void thenResponseBodyIs(String expectedBody) throws Exception {
    perform.andExpect(content().string(containsString(expectedBody)));
  }

  private void thenResponseStatusIsOK() throws Exception {
    perform.andExpect(status().isOk());
  }

  private void thenResponseStatusId404NotFound() throws Exception {
    perform.andExpect(status().isNotFound());
  }
}
