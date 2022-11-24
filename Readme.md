# KLM Recruitment case (Senior Software Engineer)

## Build a pokedex for powerful pokemon!

In this recruitment case you are going to build a pokedex, one specifically only for the 15
most powerful pokemon.

## Assignment

### External Components

The case will consist of the following external components:

* The Pokemon API, a free online rest API to retrieve everything you wanted to know about Pokemon!
* The batch file that will contain the initial load
* The database to store the batch file in, use an in memory H2 instance to store data

### Source Data

Load all the data from the batch file below into the database on startup of the application,
make sure every entry get a unique ID.

``` json
[
   {
      "name": "arceus",
      "url": "https://pokeapi.co/api/v2/pokemon/493/",
      "description": "Arceus doesn’t surprise when it comes to topping the list of th"
   },
   {
      "name": "mewtwo",
      "url": "https://pokeapi.co/api/v2/pokemon/150/",
      "description": "Well, Mewtwo holds a 2nd place in our list. There’s definitely"
   },
   {
      "name": "rayquaza",
      "url": "https://pokeapi.co/api/v2/pokemon/384/",
      "description": "Rayquaza is a Legendary Pokémon from the Hoenn region that is a"
   },
   {
      "name": "lugia",
      "url": "https://pokeapi.co/api/v2/pokemon/249/",
      "description": "In Generation II, Aeroblast was added as a damage-dealing Flyin"
   },
   {
      "name": "giratina",
      "url": "https://pokeapi.co/api/v2/pokemon/99999999999/",
      "description": "It is a Legendary Pokémon of the Ghost/Dragon type. Giratina is"
   },
   {
      "name": "zamazenta",
      "url": "https://pokeapi.co/api/v2/pokemon/889/",
      "description": "In the earlier, it collaborated with a people’s monarch to defe"
   },
   {
      "name": "zygarde",
      "url": "https://pokeapi.co/api/v2/pokemon/99999999999/",
      "description": "Zygarde is a Legendary Pokémon of the Dragon/Ground type that d"
   },
   {
      "name": "kyurem",
      "url": "https://pokeapi.co/api/v2/pokemon/646/",
      "description": "Kyurem is a Legendary Pokémon of the Dragon/Ice type that debut"
   },
   {
      "name": "eternatus",
      "url": "https://pokeapi.co/api/v2/pokemon/890/",
      "description": "Eternatus is a Poison/Dragon Pokémon that first appeared in Gen"
   },
   {
      "name": "alakazam",
      "url": "https://pokeapi.co/api/v2/pokemon/65/",
      "description": "Alakazam is a Psychic-type Pokémon that emerges from Kadabra in"
   },
   {
      "name": "dialga",
      "url": "https://pokeapi.co/api/v2/pokemon/483/",
      "description": "Dialga is a Legendary Pokémon of the Steel/Dragon types. It is"
   },
   {
      "name": "ho-oh",
      "url": "https://pokeapi.co/api/v2/pokemon/250/",
      "description": "Ho-Oh is a Legendary Pokémon of the Fire/Flying types that appe"
   },
   {
      "name": "palkia",
      "url": "https://pokeapi.co/api/v2/pokemon/484/",
      "description": "Palkia is a Legendary Pokémon of the Water/Dragon types that em"
   },
   {
      "name": "groudon",
      "url": "https://pokeapi.co/api/v2/pokemon/383/",
      "description": "Groudon is a Pokémon of the Ground type that arrived in Generat"
   },
   {
      "name": "kyogre",
      "url": "https://pokeapi.co/api/v2/pokemon/382/",
      "description": "Kyogre is a Pokémon of the Water type that appeared in Generati"
   }
]
```
_Bonus assignment_ - Schedule the data loading job, so that the data is loaded every minute -
Since the ID's are auto generated find a way performance efficient way to load the data
_without inserting duplicates_

#### REST API Contract

* /pokedex
  * list all pokemon from the database
  * Collection of objects that only contain id & name (and description depends on expand parameter).
  * Parameter: "expand" can contain "description" which will expand the description field. If this parameter is not set the description field also is not visible in the response.
* /pokemon?query=
  * list all pokemon that match the query (*by partial name*) from the database, minimum characters required is 3, otherwise a 404 will be thrown 
  * Collection of objects that only contain id & name (and description depends on expand parameter).
  * Parameter: "expand" can contain "description" which will expand the description field. If this parameter is not set the description field also is not visible in the response.
* /pokemon/{id}
  * retrieve full pokemon details for a single pokemon from the database & the pokemon API
  * Single object containing all fields (id, name, description, baseExperience, height, weight & abilities)

#### JSON Schema 
``` json
{
   "$schema": "http://json-schema.org/draft-04/schema#",
   "type": "array",
   "items": [
      {
         "type": "object",
         "properties": {
            "id": {
               "type": "integer"
            },
            "name": {
               "type": "string"
            },
            "description": {
               "type": "string"
            },
            "height": {
               "type": "integer"
            },
            "weight": {
               "type": "integer"
            },
            "baseExperience": {
               "type": "integer"
            },
            "abilities": {
               "type": "array",
               "items": [
                  {
                     "type": "string"
                  }
               ]
            }
         },
         "required": [
            "id",
            "name"
         ]
      }
   ]
}
```

* Create a client to connect to the Pokemon API, every database entry contains the link to be used to connect to the Pokemon API.
  * The only information you need from that source is base_experience, height and weight.
* Retrieve the required data from the database
  * Use Spring JDBC's CrudRepository
* Combine both to end up with the resulting object below
  * If the pokemon API does not return any results, then do not include them in the API response (as can be seen in the schema, they are optional)

#### Requirements
* Implement the public contract described above.
* Store the data in the database and use the data to construct the API contract result

#### Business Rules
* If a pokemon has one of the following abilities **[drought, pressure, telepathy]** then its **baseExperience** is increased by 25 points.
* If a pokemon's **height is >50** then its **baseExperience** is increased by 7 points.
* If a pokemon's **weight is >2000** then its **baseExperience** is decreased by 4 points.

### Technical Requirements
* The implementation should follow clean architecture principles
* The core domain of the application should follow DDD principles
* Test cases are expected to be available in BDD style
* System Integration tests should be available where database & service are mocked during the test run
* Application should be implemented using JDK17+
* Application should be implemented using Spring 5+ and Spring Boot2+
* Should use Maven or Gradle
* Code should be pushed to a public github repository and shared with KLM before the interview