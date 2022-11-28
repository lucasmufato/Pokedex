package com.kml.pokedex.infra.repository.jdbc;

import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPokemonsRepositoryImpl implements PokemonsRepository {

  private final JdbcCrudRepository jdbcCrudRepository;

  public JdbcPokemonsRepositoryImpl(JdbcCrudRepository pokemonCrudRepository) {
    this.jdbcCrudRepository = pokemonCrudRepository;
  }

  @Override
  public void save(Pokemon pokemon) {
    this.jdbcCrudRepository.save(toPokemonDao(pokemon));
  }

  @Override
  public List<Pokemon> findAll() {
    Iterable<PokemonDao> pokemonDaos = jdbcCrudRepository.findAll();
    return toPokemonsList(pokemonDaos);
  }

  @Override
  public List<Pokemon> findAllWithNameLike(String name) {
    Iterable<PokemonDao> pokemonDaos = jdbcCrudRepository.findAllWithNameLike("%"+name+"%");
    return toPokemonsList(pokemonDaos);
  }

  @Override
  public Optional<Pokemon> findById(Integer id) {
    return jdbcCrudRepository.findById(id).map(PokemonDao::toPokemon);
  }

  private List<Pokemon> toPokemonsList(Iterable<PokemonDao> pokemonDaos) {
    List<Pokemon> list = new ArrayList<>();
    pokemonDaos.forEach(pokemonDao -> list.add(pokemonDao.toPokemon()));
    return list;
  }

  private PokemonDao toPokemonDao(Pokemon pokemon) {
    return new PokemonDao(pokemon.id(), pokemon.name(), pokemon.url(), pokemon.description());
  }
}
