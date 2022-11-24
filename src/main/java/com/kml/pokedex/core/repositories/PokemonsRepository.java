package com.kml.pokedex.core.repositories;

import com.kml.pokedex.core.domain.Pokemon;
import java.util.List;
import java.util.Optional;

public interface PokemonsRepository {

  void save(Pokemon pokemon);

  List<Pokemon> findAll();

  List<Pokemon> findAllWithNameLike(String name);

  Optional<Pokemon> findById(Integer id);
}
