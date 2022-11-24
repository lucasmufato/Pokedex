package com.kml.pokedex.core.actions;

import com.kml.pokedex.core.domain.Details;
import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.domain.PokemonDetails;
import com.kml.pokedex.core.repositories.PokemonDetailsRepository;
import com.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.Optional;

public class GetPokemonDetails {

  private final PokemonsRepository localRepository;
  private final PokemonDetailsRepository pokemonDetailsRepository;

  public GetPokemonDetails(PokemonsRepository localRepository,
      PokemonDetailsRepository pokemonDetailsRepository) {
    this.localRepository = localRepository;
    this.pokemonDetailsRepository = pokemonDetailsRepository;
  }

  public Optional<PokemonDetails> findById(Integer id){
    Optional<Pokemon> optionalPokemon = this.localRepository.findById(id);
    if ( optionalPokemon.isEmpty() ) return Optional.empty();
    Pokemon pokemon = optionalPokemon.get();
    Optional<Details> details = pokemonDetailsRepository.fetchDetailsFor(pokemon);
    return Optional.of(new PokemonDetails(pokemon, details));
  }

}
