package com.kml.pokedex.infra.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PokemonCrudRepository extends CrudRepository<PokemonDao, Integer> {

  @Query("select p from PokemonDao p where p.name like %?1%")
  Iterable<PokemonDao> findAllWithNameLike(String name);
}