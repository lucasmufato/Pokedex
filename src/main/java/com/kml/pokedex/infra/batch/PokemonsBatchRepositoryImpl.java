package com.kml.pokedex.infra.batch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.repositories.PokemonsBatchRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class PokemonsBatchRepositoryImpl implements PokemonsBatchRepository {


  ObjectMapper mapper = new ObjectMapper();
  TypeReference<List<BatchPokemon>> typeReference = new TypeReference<>() {};
  InputStream inputStream = TypeReference.class.getResourceAsStream("/static/pokemons_data.json");

  @Override
  public List<Pokemon> getAllBatchPokemons() {
    try {
      List<BatchPokemon> batchPokemons = mapper.readValue(inputStream,typeReference);
      return batchPokemons.stream().map(this::toPokemon).collect(Collectors.toList());
    } catch (IOException e){
      System.out.println("Unable to read batch pokemons: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  private Pokemon toPokemon(BatchPokemon batchPokemon) {
    return new Pokemon(null, batchPokemon.getName(), batchPokemon.getUrl(), batchPokemon.getDescription());
  }

}
