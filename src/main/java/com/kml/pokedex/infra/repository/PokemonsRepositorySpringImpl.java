package com.kml.pokedex.infra.repository;

import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.repositories.PokemonsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PokemonsRepositorySpringImpl implements PokemonsRepository {
  private final PokemonCrudRepository pokemonCrudRepository;

  public PokemonsRepositorySpringImpl(PokemonCrudRepository pokemonCrudRepository) {
    this.pokemonCrudRepository = pokemonCrudRepository;
  }

  @Override
  public void save(Pokemon pokemon) {
    this.pokemonCrudRepository.save(toPokemonDao(pokemon));
  }

  @Override
  public List<Pokemon> findAll() {
    Iterable<PokemonDao> pokemonDaos = pokemonCrudRepository.findAll();
    return toPokemonsList(pokemonDaos);
  }

  @Override
  public List<Pokemon> findAllWithNameLike(String name) {
    Iterable<PokemonDao> pokemonDaos = pokemonCrudRepository.findAllWithNameLike(name);
    return toPokemonsList(pokemonDaos);
  }

  @Override
  public Optional<Pokemon> findById(Integer id) {
    return pokemonCrudRepository.findById(id).map(PokemonDao::toPokemon);
  }

  private List<Pokemon> toPokemonsList(Iterable<PokemonDao> pokemonDaos) {
    List<Pokemon> list = new ArrayList<>();
    pokemonDaos.forEach( pokemonDao -> list.add( pokemonDao.toPokemon() ));
    return list;
  }

  private PokemonDao toPokemonDao(Pokemon pokemon) {
    return new PokemonDao(pokemon.id(), pokemon.name(), pokemon.url(), pokemon.description());
  }

}