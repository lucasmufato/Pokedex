package ar.com.mufato.kml.pokedex.delivery.rest;

import ar.com.mufato.kml.pokedex.core.actions.GetPokemonDetails;
import ar.com.mufato.kml.pokedex.core.domain.PokemonDetails;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPokemonWithDetailsRest {

  private final GetPokemonDetails getPokemonDetails;

  public GetPokemonWithDetailsRest(GetPokemonDetails getPokemonDetails) {
    this.getPokemonDetails = getPokemonDetails;
  }

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
        pd.getHeight().orElse(null),
        pd.getWeight().orElse(null),
        pd.getBaseExperience().orElse(null),
        pd.getAbilities().orElse(null)
    );
  }

}

