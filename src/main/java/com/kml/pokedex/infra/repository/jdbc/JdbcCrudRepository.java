package com.kml.pokedex.infra.repository.jdbc;

import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcCrudRepository extends CrudRepository<PokemonDao, Integer> {

  @Override
  Iterable<PokemonDao> findAll();

  @Override
  <S extends PokemonDao> S save(S entity);

  @Override
  Optional<PokemonDao> findById(Integer id);

  @Query("SELECT * FROM POKEMON where name like :name ")
  Iterable<PokemonDao> findAllWithNameLike(@Param("name") String name);


}
