package com.kml.pokedex.infra.repository.http;

import com.kml.pokedex.core.domain.Details;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record PokeapiDetails(List<PokeapiAbilities> abilities, Integer height, Integer weight, Integer base_experience) {
  public Optional<Details> toDetails() {
    Details details = new Details(base_experience,height,weight,
        abilities.stream().map(a -> a.ability().name()).collect(Collectors.toList())
    );
    return Optional.of(details);
  }
}

record PokeapiAbilities(AbilityDescription ability){ }

record AbilityDescription(String name){ }