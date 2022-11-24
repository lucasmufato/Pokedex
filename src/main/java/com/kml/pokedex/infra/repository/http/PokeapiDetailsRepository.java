package com.kml.pokedex.infra.repository.http;

import com.kml.pokedex.core.domain.Details;
import com.kml.pokedex.core.domain.Pokemon;
import com.kml.pokedex.core.repositories.PokemonDetailsRepository;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PokeapiDetailsRepository implements PokemonDetailsRepository {
  private final Logger Log = Logger.getLogger(PokeapiDetailsRepository.class.getName());
  private final RestTemplate restTemplate = new RestTemplate();
  @Override
  public Optional<Details> fetchDetailsFor(Pokemon pokemon) {
    try{
      return makeRequestFor(pokemon);
    }catch (RuntimeException exception){
      Log.severe(
          "Couldnt fetch or parse data for pokemon id %d from url: %s because %s".formatted(
              pokemon.id(), pokemon.url(), exception.getMessage() ));
      return Optional.empty();
    }
  }

  private Optional<Details> makeRequestFor(Pokemon pokemon) {
    ResponseEntity<PokeapiDetails> response = prepareRequestTo(pokemon.url());
    Log.info("Fetch info from pokeapi with "+response.getStatusCodeValue());

    if(response.getStatusCode().is2xxSuccessful()){
      if (response.getBody() == null) return Optional.empty();
      return response.getBody().toDetails();
    }else{
      Log.warning(
          "Couldnt fetch data for pokemon id %d from url: %s because response was: %d - %s".formatted(
              pokemon.id(), pokemon.url(), response.getStatusCodeValue(), response.getBody() ));
      return Optional.empty();
    }
  }

  private ResponseEntity<PokeapiDetails> prepareRequestTo(String url) {
    return restTemplate.exchange(
        url, HttpMethod.GET, null,
        new ParameterizedTypeReference<>() {
        });
  }

}
