package ar.com.mufato.kml.pokedex.core.domain;

import ar.com.mufato.kml.pokedex.core.domain.exceptions.InvalidDetailException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DetailsTest {

  private Details details = null;
  private Exception exception = null;

  @Test
  void validateBaseExperience() {
    whenInstatiateDetailWith(-10, 10, 10, List.of("something"));

    thenInvalidDetailIsThrownWithMessage("Base Experience cant be null or negative value");
    thenDetailsHasNotBeingInstantiated();
  }

  @Test
  void validateWeight() {
    whenInstatiateDetailWith(100, -1, 10, List.of("something"));

    thenInvalidDetailIsThrownWithMessage("Weight cant be null or negative value");
    thenDetailsHasNotBeingInstantiated();
  }

  @Test
  void validateHeight() {
    whenInstatiateDetailWith(100, 10, -1, List.of("something"));

    thenInvalidDetailIsThrownWithMessage("Height cant be null or negative value");
    thenDetailsHasNotBeingInstantiated();
  }

  @Test
  void validateAbilitiesAgainstNulls() {
    whenInstatiateDetailWith(100, 10, 10, null);

    thenInvalidDetailIsThrownWithMessage("Abilities cant be null or empty or filled with blank values");
    thenDetailsHasNotBeingInstantiated();
  }

  @Test
  void validateAbilitiesAgainsEmptyList() {
    whenInstatiateDetailWith(100, 10, 10, Collections.emptyList());

    thenInvalidDetailIsThrownWithMessage("Abilities cant be null or empty or filled with blank values");
    thenDetailsHasNotBeingInstantiated();
  }

  @Test
  void validateAbilitiesAgaintBlanks() {
    whenInstatiateDetailWith(100, 10, 10, List.of("  "));

    thenInvalidDetailIsThrownWithMessage("Abilities cant be null or empty or filled with blank values");
    thenDetailsHasNotBeingInstantiated();
  }

  @Test
  void experienceIsNotAffectedByLowWeithHeighOrAbilities() {
    whenInstatiateDetailWith(100, 10, 10, List.of("fist"));

    thenDetailsIsInstantiated();
    thenExperienceIs(100);
  }

  @Test
  void experiencesIsDecreasedByHighWeight() {
    whenInstatiateDetailWith(100, 2001, 10, List.of("fist"));

    thenDetailsIsInstantiated();
    thenExperienceIs(96);
  }

  @Test
  void experiencesIsIncreasedByHighHeight() {
    whenInstatiateDetailWith(100, 50, 51, List.of("fist"));

    thenDetailsIsInstantiated();
    thenExperienceIs(107);
  }

  @Test
  void experiencesIsIncreasedByCertainAbilities() {
    whenInstatiateDetailWith(100, 50, 10, List.of("drought"));

    thenDetailsIsInstantiated();
    thenExperienceIs(125);
  }

  @Test
  void experiencesIsUpdatedByAllDetails() {
    whenInstatiateDetailWith(100, 2050, 55, List.of("telepathy", "pressure"));

    thenDetailsIsInstantiated();
    thenExperienceIs(128);
  }

  private void whenInstatiateDetailWith(Integer baseExperience, Integer weight, int height,
      List<String> abilities) {
    try{
      details = new Details(baseExperience, height,weight, abilities);
    }catch (Exception e){
      exception = e;
    }
  }

  private void thenExperienceIs(int expectedExperience) {
    Assertions.assertEquals(expectedExperience, details.baseExperience() );
  }

  private void thenDetailsIsInstantiated() {
    Assertions.assertNotNull(details);
  }
  private void thenDetailsHasNotBeingInstantiated() {
    Assertions.assertNull(details);
  }

  private void thenInvalidDetailIsThrownWithMessage(String expectedMessage) {
    Assertions.assertNotNull(exception);
    Assertions.assertEquals(InvalidDetailException.class, exception.getClass());
    Assertions.assertEquals(expectedMessage, exception.getMessage());
  }
}