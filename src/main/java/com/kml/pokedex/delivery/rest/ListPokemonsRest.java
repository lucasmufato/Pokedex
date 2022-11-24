package com.kml.pokedex.delivery.rest;

import com.kml.pokedex.core.actions.ListPokemons;
import com.kml.pokedex.core.actions.ListPokemonsFilteringByName;
import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.domain.exceptions.DomainExceptions;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListPokemonsRest {

  @Autowired
  private ListPokemons listPokemons;

  @Autowired
  private ListPokemonsFilteringByName listPokemonsFilteringByName;

  @GetMapping(path = "/pokedex")
  public List<PokemonRepresentation> getAllPokemons(@RequestParam Optional<String> expand){
    return mapToJson ( listPokemons.invoke(), expand);
  }

  @GetMapping(path = "/pokemon")
  public ResponseEntity<List<PokemonRepresentation>> getAllPokemonsFilteringBy(
      @RequestParam Optional<String> expand,
      @RequestParam Optional<String> query)
  {
    try{
      return ResponseEntity.ok(
          mapToJson(
              listPokemonsFilteringByName.invoke(query.orElse("")),
              expand
          )
      );
    }catch (DomainExceptions de){
      return ResponseEntity.notFound().build();
    }
  }

  private  List<PokemonRepresentation> mapToJson(List<Pokemon> pokemons, Optional<String> expand){
    return pokemons.stream()
        .map(pokemon -> toRepresentation(pokemon, expandQuery(expand)))
        .collect(Collectors.toList());
  }
  private PokemonRepresentation toRepresentation(Pokemon pokemon, boolean containDescription) {
    PokemonRepresentation representation;
    if (containDescription)
      representation = new PokemonRepresentation(pokemon.id(), pokemon.name(), pokemon.description());
    else
      representation = new PokemonRepresentation(pokemon.id(), pokemon.name(), null);
    return representation;
  }

  private static boolean expandQuery(Optional<String> expand) {
    if( expand.isEmpty() ) return false;
    return expand.get().equalsIgnoreCase("description");
  }

}