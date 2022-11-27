package com.kml.pokedex.config;

import com.kml.pokedex.core.actions.GetPokemonDetails;
import com.kml.pokedex.core.actions.ListPokemons;
import com.kml.pokedex.core.actions.ListPokemonsFilteringByName;
import com.kml.pokedex.core.actions.LoadData;
import com.kml.pokedex.core.repositories.PokemonDetailsRepository;
import com.kml.pokedex.core.repositories.PokemonsBatchRepository;
import com.kml.pokedex.core.repositories.PokemonsRepository;
import com.kml.pokedex.delivery.rest.GetPokemonWithDetailsRest;
import com.kml.pokedex.delivery.rest.ListPokemonsRest;
import com.kml.pokedex.infra.batch.PokemonsBatchRepositoryImpl;
import com.kml.pokedex.infra.repository.PokemonCrudRepository;
import com.kml.pokedex.infra.repository.PokemonsRepositorySpringImpl;
import com.kml.pokedex.infra.repository.http.PokeapiDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {

  // CORE
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
  public GetPokemonDetails getPokemonDetails(
      PokemonsRepository pokemonsRepository,
      PokemonDetailsRepository pokemonDetailsRepository){
    return new GetPokemonDetails(pokemonsRepository, pokemonDetailsRepository);
  }

  // INFRA

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
  @Bean
  public PokemonDetailsRepository pokemonDetailsRepository(RestTemplate restTemplate){
    return new PokeapiDetailsRepository(restTemplate);
  }
  @Bean
  public PokemonsBatchRepository pokemonsBatchRepository() {
    return new PokemonsBatchRepositoryImpl();
  }

  @Bean
  public PokemonsRepository pokemonsRepository(PokemonCrudRepository pokemonCrudRepository){
    return new PokemonsRepositorySpringImpl(pokemonCrudRepository);
  }

  // DELIVERY
  @Bean
  public ListPokemonsRest listPokemonsRest(
      ListPokemonsFilteringByName listPokemonsFilteringByName,
      ListPokemons listPokemons){
    return new ListPokemonsRest(listPokemons, listPokemonsFilteringByName);
  }

  @Bean
  public GetPokemonWithDetailsRest getPokemonWithDetailsRest(GetPokemonDetails getPokemonDetails){
    return new GetPokemonWithDetailsRest(getPokemonDetails);
  }

}

