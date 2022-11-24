package com.kml.pokedex.delivery.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public record PokemonRepresentation(Integer id, String name, String description) {
}
