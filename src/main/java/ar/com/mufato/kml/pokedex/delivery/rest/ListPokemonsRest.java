package ar.com.mufato.kml.pokedex.delivery.rest;

import ar.com.mufato.kml.pokedex.core.actions.ListPokemons;
import ar.com.mufato.kml.pokedex.core.actions.ListPokemonsFilteringByName;
import ar.com.mufato.kml.pokedex.core.domain.Pokemon;
import ar.com.mufato.kml.pokedex.core.domain.exceptions.DomainExceptions;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListPokemonsRest {

  private final ListPokemons listPokemons;
  private final ListPokemonsFilteringByName listPokemonsFilteringByName;

  public ListPokemonsRest(ListPokemons listPokemons,
      ListPokemonsFilteringByName listPokemonsFilteringByName) {
    this.listPokemons = listPokemons;
    this.listPokemonsFilteringByName = listPokemonsFilteringByName;
  }

  @GetMapping(path = "/pokedex")
  public List<PokemonRepresentation> getAllPokemons(
      @RequestParam(defaultValue = "") String expand) {
    return mapToJson(listPokemons.invoke(), expand);
  }

  @GetMapping(path = "/pokemon")
  public ResponseEntity<List<PokemonRepresentation>> getAllPokemonsFilteringBy(
      @RequestParam(defaultValue = "") String expand,
      @RequestParam(defaultValue = "") String query) {
    try {
      return ResponseEntity.ok(
          mapToJson(listPokemonsFilteringByName.invoke(query), expand)
      );
    } catch (DomainExceptions de) {
      return ResponseEntity.notFound().build();
    }
  }

  private List<PokemonRepresentation> mapToJson(List<Pokemon> pokemons, String expandParameter) {
    return pokemons.stream()
        .map(pokemon -> toRepresentation(pokemon, "description".equalsIgnoreCase(expandParameter)))
        .toList();
  }

  private PokemonRepresentation toRepresentation(Pokemon pokemon, boolean containDescription) {
    PokemonRepresentation representation;
    if (containDescription) {
      representation = new PokemonRepresentation(pokemon.id(), pokemon.name(), pokemon.description());
    } else {
      representation = new PokemonRepresentation(pokemon.id(), pokemon.name(), null);
    }
    return representation;
  }


}