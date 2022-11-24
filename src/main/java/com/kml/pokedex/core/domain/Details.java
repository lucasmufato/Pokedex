package com.kml.pokedex.core.domain;

import java.util.List;

public record Details(int baseExperience, int height, int weight, List<String> abilities) {

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
