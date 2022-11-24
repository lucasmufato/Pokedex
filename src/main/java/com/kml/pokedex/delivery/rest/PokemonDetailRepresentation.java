package com.kml.pokedex.delivery.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public record PokemonDetailRepresentation(
    Integer id,
    String name,
    String description,
    Integer height,
    Integer weight,
    Integer baseExperience,
    List<String> abilities
) {

}
