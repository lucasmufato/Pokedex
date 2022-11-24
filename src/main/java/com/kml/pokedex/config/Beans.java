package com.kml.pokedex.config;

import com.kml.pokedex.core.actions.GetPokemonDetails;
import com.kml.pokedex.core.actions.ListPokemons;
import com.kml.pokedex.core.actions.ListPokemonsFilteringByName;
import com.kml.pokedex.core.actions.LoadData;
import com.kml.pokedex.core.repositories.PokemonDetailsRepository;
import com.kml.pokedex.core.repositories.PokemonsBatchRepository;
import com.kml.pokedex.core.repositories.PokemonsRepository;
import com.kml.pokedex.infra.batch.PokemonsBatchRepositoryImpl;
import com.kml.pokedex.infra.repository.http.PokeapiDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public PokemonsBatchRepository pokemonsBatchRepository() {
    return new PokemonsBatchRepositoryImpl();
  }

  @Bean
  public LoadData loadData(PokemonsBatchRepository pokemonsBatchRepository,
      PokemonsRepository pokemonsRepository) {
    return new LoadData(pokemonsBatchRepository, pokemonsRepository);
  }

  @Bean
  public ListPokemons listPokemons(PokemonsRepository pokemonsRepository){
    return new ListPokemons(pokemonsRepository);
  }

  @Bean
  public ListPokemonsFilteringByName listPokemonsFilteringByName(PokemonsRepository pokemonsRepository){
    return new ListPokemonsFilteringByName(pokemonsRepository);
  }

  @Bean
  public GetPokemonDetails getPokemonDetails(PokemonsRepository pokemonsRepository,
      PokemonDetailsRepository pokemonDetailsRepository){
    return new GetPokemonDetails(pokemonsRepository, pokemonDetailsRepository);
  }

  @Bean
  public PokemonDetailsRepository pokemonDetailsRepository(){
    return new PokeapiDetailsRepository();
  }

}

