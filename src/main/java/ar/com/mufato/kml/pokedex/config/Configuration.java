package ar.com.mufato.kml.pokedex.config;

import ar.com.mufato.kml.pokedex.core.actions.GetPokemonDetails;
import ar.com.mufato.kml.pokedex.core.actions.LoadData;
import ar.com.mufato.kml.pokedex.core.repositories.PokemonDetailsRepository;
import ar.com.mufato.kml.pokedex.delivery.rest.GetPokemonWithDetailsRest;
import ar.com.mufato.kml.pokedex.delivery.rest.ListPokemonsRest;
import ar.com.mufato.kml.pokedex.infra.batch.PokemonsBatchRepositoryImpl;
import ar.com.mufato.kml.pokedex.infra.repository.http.PokeapiDetailsRepository;
import ar.com.mufato.kml.pokedex.infra.repository.jdbc.JdbcPokemonsRepositoryImpl;
import ar.com.mufato.kml.pokedex.core.actions.ListPokemons;
import ar.com.mufato.kml.pokedex.core.actions.ListPokemonsFilteringByName;
import ar.com.mufato.kml.pokedex.core.repositories.PokemonsBatchRepository;
import ar.com.mufato.kml.pokedex.core.repositories.PokemonsRepository;
import ar.com.mufato.kml.pokedex.infra.repository.jdbc.JdbcCrudRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

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
  public PokemonsRepository pokemonsRepository(JdbcCrudRepository pokemonCrudRepository){
    return new JdbcPokemonsRepositoryImpl(pokemonCrudRepository);
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

