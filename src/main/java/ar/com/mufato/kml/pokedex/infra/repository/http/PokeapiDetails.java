package ar.com.mufato.kml.pokedex.infra.repository.http;

import ar.com.mufato.kml.pokedex.core.domain.Details;
import java.util.List;
import java.util.Optional;

public record PokeapiDetails(List<PokeapiAbilities> abilities, Integer height, Integer weight, Integer base_experience) {
  public Optional<Details> toDetails() {
    Details details = new Details(base_experience,height,weight,
        abilities.stream().map(a -> a.ability().name()).toList()
    );
    return Optional.of(details);
  }
}