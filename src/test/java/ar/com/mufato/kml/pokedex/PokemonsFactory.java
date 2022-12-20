package ar.com.mufato.kml.pokedex;

import ar.com.mufato.kml.pokedex.core.domain.Pokemon;

public class PokemonsFactory {

  public static final Pokemon pikachu = new Pokemon(1,"Pikachu", "https://pokeapi.co/api/v2/pokemon/25", "best pokemon");
  public static final Pokemon raychu = new Pokemon(2,"Raychu", "https://pokeapi.co/api/v2/pokemon/26", "second best pokemon");

}
