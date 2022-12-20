package ar.com.mufato.kml.pokedex.core.domain;

import ar.com.mufato.kml.pokedex.core.domain.exceptions.InvalidDetailException;
import java.util.List;

public record Details(int baseExperience, int height, int weight, List<String> abilities) {

  public Details {
    if (baseExperience<0) throw new InvalidDetailException("Base Experience cant be null or negative value");
    if (weight<0) throw new InvalidDetailException("Weight cant be null or negative value");
    if (height<0) throw new InvalidDetailException("Height cant be null or negative value");
    if (abilities == null) throw new InvalidDetailException("Abilities cant be null or empty or filled with blank values");
    abilities = abilities.stream().filter( a -> !a.isBlank() ).toList();
    if (abilities.size() == 0) throw new InvalidDetailException("Abilities cant be null or empty or filled with blank values");
  }

  public int baseExperience() {
    int calculatedExperience = baseExperience;
    if (height > 50) {
      calculatedExperience += 7;
    }
    if (weight > 2000) {
      calculatedExperience -= 4;
    }
    if (abilities.stream().anyMatch(
        a -> a.equalsIgnoreCase("drought") || a.equalsIgnoreCase("pressure") || a.equalsIgnoreCase(
            "telepathy"))) {
      calculatedExperience += 25;
    }
    return calculatedExperience;
  }

}
