package com.kml.pokedex.delivery.rest;

import com.kml.pokedex.core.actions.GetPokemonDetails;
import com.kml.pokedex.core.domain.PokemonDetails;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPokemonWithDetailsRest {

  @Autowired
  private GetPokemonDetails getPokemonDetails;

  @GetMapping("/pokemon/{id}")
  public ResponseEntity<PokemonDetailRepresentation> getPokemonWithDetailsById(@PathVariable Integer id){
    Optional<PokemonDetails> pokemonDetails = getPokemonDetails.findById(id);
    if (pokemonDetails.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toRepresentation(pokemonDetails.get()));
  }

  private PokemonDetailRepresentation toRepresentation(PokemonDetails pd) {
    return new PokemonDetailRepresentation(pd.getId(),
        pd.getName(),
        pd.getDescription(),
        pd.getHeight().get(),
        pd.getWeight().get(),
        pd.getBaseExperience().get(),
        pd.getAbilities().get()
    );
  }

}

