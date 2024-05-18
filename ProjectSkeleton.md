# Project Skeleton

## Folder Structure

```
dice-realms-game-zeus
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── game
│   │   │   │   ├── collectibles
│   │   │   │   │   ├── ArcaneBoost.java
│   │   │   │   │   ├── Collectibles.java
│   │   │   │   │   ├── ColorBonus.java
│   │   │   │   │   ├── ElementalCrest.java
│   │   │   │   │   ├── EssenceBonus.java
│   │   │   │   │   └── TimeWarp.java
│   │   │   │   ├── creatures
│   │   │   │   │   ├── Creature.java
│   │   │   │   │   ├── Dragon.java
│   │   │   │   │   ├── Guardian.java
│   │   │   │   │   ├── HitRegionsOfDragons.java
│   │   │   │   │   ├── Lion.java
│   │   │   │   │   ├── Phoenix.java
│   │   │   │   │   └── Serpent.java
│   │   │   │   ├── dice
│   │   │   │   │   ├── BlueDice.java
│   │   │   │   │   ├── Dice.java
│   │   │   │   │   ├── DiceStatus.java
│   │   │   │   │   ├── GreenDice.java
│   │   │   │   │   ├── MagentaDice.java
│   │   │   │   │   ├── RedDice.java
│   │   │   │   │   ├── WhiteDice.java
│   │   │   │   │   └── YellowDice.java
│   │   │   │   ├── engine
│   │   │   │   │   ├── AntiCheatService.java
│   │   │   │   │   ├── CLIGameController.java
│   │   │   │   │   ├── CurrentStatus.java
│   │   │   │   │   ├── GameBoard.java
│   │   │   │   │   ├── GameController.java
│   │   │   │   │   ├── GameGuide.java
│   │   │   │   │   ├── GameScore.java
│   │   │   │   │   ├── GameStatus.java
│   │   │   │   │   ├── Instruction.java
│   │   │   │   │   ├── Move.java
│   │   │   │   │   ├── Player.java
│   │   │   │   │   ├── PlayerStatus.java
│   │   │   │   │   ├── ScoreSheet.java
│   │   │   │   │   └── StandardAntiCheatService.java
│   │   │   │   ├── exceptions
│   │   │   │   │   ├── CheatDetectedException.java
│   │   │   │   │   ├── InvalidPlayerNameException.java
│   │   │   │   │   ├── MissingGameFilesException.java
│   │   │   │   │   └── NoAvailableMovesException.java
│   │   │   │   ├── gui
│   │   │   │   │   └── DiceRealms.java
│   │   │   │   ├── realms
│   │   │   │   │   ├── BlueRealm.java
│   │   │   │   │   ├── GreenRealm.java
│   │   │   │   │   ├── MagentaRealm.java
│   │   │   │   │   ├── Realm.java
│   │   │   │   │   ├── RedRealm.java
│   │   │   │   │   └── YellowRealm.java
│   │   │   │   ├── system
│   │   │   │   │   └── SystemManager.java
│   │   │   │   ├── utilities
│   │   │   │   │   ├── CollectiblesComparator.java
│   │   │   │   │   ├── Color.java
│   │   │   │   │   └── ColorComparator.java
│   │   │   │   └── Main.java
│   │   │   └── module-info.java
│   │   └── resources
│   │       ├── config
│   │       │   ├── EmberfallDominionRewards.properties
│   │       │   ├── Game.properties
│   │       │   ├── MysticalSkyRewards.properties
│   │       │   ├── RadiantSvannaRewards.properties
│   │       │   ├── RoundsRewards.properties
│   │       │   ├── TerrasHeartlandRewards.properties
│   │       │   └── TideAbyssRewards.properties
│   │       ├── images
│   │       │   └── Project-UML-Diagram.png
│   │       └── EmptyScoreSheet.txt
│   └── test
│       └── java
│           └── game
│               ├── collectibles
│               ├── creatures
│               ├── dice
│               ├── engine
│               │   └── CLIGameControllerTest.java
│               ├── exceptions
│               └── gui
├── target
│   ├── classes
│   │   ├── config
│   │   │   ├── EmberfallDominionRewards.properties
│   │   │   ├── Game.properties
│   │   │   ├── MysticalSkyRewards.properties
│   │   │   ├── RadiantSvannaRewards.properties
│   │   │   ├── RoundsRewards.properties
│   │   │   ├── TerrasHeartlandRewards.properties
│   │   │   └── TideAbyssRewards.properties
│   │   ├── game
│   │   │   ├── collectibles
│   │   │   │   ├── ArcaneBoost.class
│   │   │   │   ├── Collectibles.class
│   │   │   │   ├── ColorBonus.class
│   │   │   │   ├── ElementalCrest.class
│   │   │   │   ├── EssenceBonus.class
│   │   │   │   └── TimeWarp.class
│   │   │   ├── creatures
│   │   │   │   ├── Creature.class
│   │   │   │   ├── Dragon$1.class
│   │   │   │   ├── Dragon.class
│   │   │   │   ├── Guardian.class
│   │   │   │   ├── Guardians.class
│   │   │   │   ├── HitRegionsOfDragons.class
│   │   │   │   ├── Lion.class
│   │   │   │   ├── Phoenix.class
│   │   │   │   └── Serpent.class
│   │   │   ├── dice
│   │   │   │   ├── BlueDice.class
│   │   │   │   ├── Dice.class
│   │   │   │   ├── DiceStatus.class
│   │   │   │   ├── GreenDice.class
│   │   │   │   ├── MagentaDice.class
│   │   │   │   ├── RedDice.class
│   │   │   │   ├── WhiteDice.class
│   │   │   │   └── YellowDice.class
│   │   │   ├── engine
│   │   │   │   ├── CLIGameController$1.class
│   │   │   │   ├── CLIGameController.class
│   │   │   │   ├── CurrentStatus.class
│   │   │   │   ├── GameBoard.class
│   │   │   │   ├── GameController.class
│   │   │   │   ├── GameGuide$1.class
│   │   │   │   ├── GameGuide.class
│   │   │   │   ├── GameScore.class
│   │   │   │   ├── GameStatus.class
│   │   │   │   ├── Instruction.class
│   │   │   │   ├── Move.class
│   │   │   │   ├── Player.class
│   │   │   │   ├── PlayerStatus.class
│   │   │   │   └── ScoreSheet.class
│   │   │   ├── exceptions
│   │   │   │   ├── InvalidPlayerNameException.class
│   │   │   │   ├── MissingGameFilesException.class
│   │   │   │   └── NoAvailableMovesException.class
│   │   │   ├── realms
│   │   │   │   ├── BlueRealm.class
│   │   │   │   ├── GreenRealm.class
│   │   │   │   ├── MagentaRealm.class
│   │   │   │   ├── Realm.class
│   │   │   │   ├── RedRealm.class
│   │   │   │   └── YellowRealm.class
│   │   │   ├── system
│   │   │   │   └── SystemManager.class
│   │   │   ├── utilities
│   │   │   │   ├── CollectiblesComparator.class
│   │   │   │   ├── Color.class
│   │   │   │   ├── ColorComparator$1.class
│   │   │   │   └── ColorComparator.class
│   │   │   └── Main.class
│   │   ├── images
│   │   │   └── Project-UML-Diagram.png
│   │   └── EmptyScoreSheet.txt
│   ├── generated-sources
│   │   └── annotations
│   ├── generated-test-sources
│   │   └── test-annotations
│   └── test-classes
│       └── game
│           └── engine
│               └── CLIGameControllerTest.class
├── Grades.md
├── ProjectSkeleton.md
├── README.md
├── TemplateSkeleton.md
└── pom.xml
```

## Packages

### game.collectibles

The `game.collectibles` package contains classes for the various collectible items within the game; such as power-ups, elemental crest, color bonus, or the essence bonus.

### game.creatures

In the `game.creatures` package, you'll find classes representing creatures in their corresponding realms; including all necessary features about how to attack them or their current status to update the score sheet accordingly.

### game.dice

The `game.dice` package encompasses classes related to dice functionality within the game. It includes implementations for rolling dice, managing dice states, and handling dice-related actions and interactions.

### game.engine

This package contains the core engine components of the game, including the abstract classes and interfaces that define the game's structure and functionality. It serves as the foundation for implementing various game controllers and managing game logic. Additional classes related to game mechanics and control can be added to this package as needed.

### game.exceptions

The `game.exceptions` package provides classes for defining custom exceptions specific to the game. These exceptions help handle error conditions and unexpected situations, providing meaningful feedback to the player or developer.

### game.gui

The `game.gui` package houses classes related to the graphical user interface (GUI) of the game. This includes components for rendering game graphics, handling user input, and managing the visual presentation of game elements.

### game.realms

The `game.realms` package provides classes that includes all the realms of the game; including all the methods about how to get the available moves in the realm and how to preform an attack and each creature of the realm.

### game.system 

The `game.system` package includes the file responsible for locating and running the files containing properties of realms and making sure no errors arise from it.

### game.utilities

The `game.utilities` package has the comparator classes to be able to compare colors and collectibles and check if equal or not.

## Classes

For each package, add the skeleton details for the class and duplicate as much as needed. As an example, the `GameController.java` skeleton is provided as guideline.

### `GameController` class

- **Package**: `game.engine`
- **Type**: Abstract Class
- **Description**: This abstract class represents the controller for the game. It defines the common blueprint for different controllers used in the game.

#### Methods:

1. `void startGame()`

   - **Description**: Initializes necessary components and starts the game loop.

2. `boolean switchPlayer()`

   - **Description**: Switches the role of the current active player to passive and vice versa, ensuring that the turn-taking mechanism functions correctly.
   - **Return Type**: `boolean`
     - `true` if the switch was successful,
     - `false` otherwise.

3. `Dice[] rollDice()`

   - **Description**: Rolls all available dice for the current turn, assigning each a random number from 1 to 6.
   - **Return Type**: Array of `Dice`
     - An array of the currently rolled dice.

4. `Dice[] getAvailableDice()`

   - **Description**: Gets the dice available for rolling or rerolling.
   - **Return Type**: Array of `Dice`
     - An array of dice available for the current turn.

5. `Dice[] getAllDice()`

   - **Description**: Gets all six dice, providing their current state and value within the game regardless of their location or status.
   - **Return Type**: Array of `Dice`
     - An array of all six dice, with each die's state and value.

6. `Dice[] getForgottenRealmDice()`

   - **Description**: Gets the dice currently available in the Forgotten Realm.
   - **Return Type**: Array of `Dice`
     - An array of dice that are currently in the Forgotten Realm.

7. `Move[] getAllPossibleMoves()`

   - **Description**: Gets all possible moves for all currently rolled dice for the active player.
   - **Return Type**: Array of `Move`
     - An array of all possible moves for all rolled dice.

8. `Move[] getPossibleMoves(Dice dice)`

   - **Description**: Gets all possible moves for a given dice for the active player.
   - **Parameters**:
     - `dice`: The dice to determine possible moves for.
   - **Return Type**: Array of `Move`
     - An array of possible moves for the given dice.

9. `GameBoard getGameBoard()`

   - **Description**: Gets the current game board, including all players and all score sheets.
   - **Return Type**: `GameBoard`
     - The current game board object.

10. `Player getPlayer()`

    - **Description**: Gets the current active player's information.
    - **Return Type**: `Player`
      - The active player object.

11. `ScoreSheet getScoreSheet()`

    - **Description**: Gets the score sheet for the current active player.
    - **Return Type**: `ScoreSheet`
      - The score sheet object for the current active player.

12. `GameStatus getGameStatus()`

    - **Description**: Gets the current game status, including round and turn information for the current active player.
    - **Return Type**: `GameStatus`
      - The current game status object.

13. `GameScore getGameScore()`

    - **Description**: Gets the current score of the game, including scores in each realm, number of elemental crests, and the total score for the current active player.
    - **Return Type**: `GameScore`
      - The current game score object.

14. `TimeWarp getTimeWarpPowers()`

    - **Description**: Gets the number of TimeWarp powers the active player has and their status.
    - **Return Type**: `TimeWarp`
      - The TimeWarp object for the current active player.

15. `ArcaneBoost getArcaneBoostPowers()`

    - **Description**: Gets the number of ArcaneBoost powers the active player has and their status.
    - **Return Type**: `ArcaneBoost`
      - The ArcaneBoost object for the current active player.

16. `boolean selectDice(Dice dice)`

    - **Description**: Selects a dice and adds it to the current turn of the active player, moving all other dice with less value to the Forgotten Realm.
    - **Parameters**:
      - `dice`: The dice to be selected.
    - **Return Type**: `boolean`
      - `true` if the selection was successful,
      - `false` otherwise.

17. `boolean makeMove(Dice dice, Creature creature)`
    - **Description**: Executes a move using the selected dice on a specified creature.
    - **Parameters**:
      - `dice`: The dice selected by the active player for the move.
      - `creature`: The target creature that the move is against.
    - **Return Type**: `boolean`
      - `true` if the move is successfully completed,
      - `false` otherwise.


### `ArcaneBoost` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the arcane boost.

#### Methods:

1. `CollectiblesStatus getStatus()`
   - **Description**: it is a getter method for status attribute in the class.
   - **Return Type**: `CollectiblesStatus`
     - the current status of the collectible (disabled - enabled - used).

2. `String getInstruction()`
  - **Description**: Method that is used to retrieve the instruction property.
  - **Return Type**: `String`
    - returns the value of the instruction property.

3. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.




### `Collectibles` class

- **Package**: `game.collectibles`
- **Type**: Abstract Class
- **Description**: This class includes the abstract methods commonly used in all the subclasses.

#### Methods:

1. `Collectibles getCollectibleFromString(String reward)`
   - **Description**: Converts a string representation of a reward into its corresponding `Collectibles` object.
   - **Parameters**: 
     - `reward`: the string representation of the reward.
   - **Return Type**: `Collectibles`
     - returns a new instance of the corresponding collectible based on the string input. Returns `null` if the input is `null` or does not match any known collectible.

2. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the collectible.
   - **Return Type**: `String`
     - a string description of the collectible.

### `ColorBonus` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the color bonus.

#### Methods:

1. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the collectible.
   - **Return Type**: `String`
     - a string representation of the color bonus.

2. `Color getColor()`
   - **Description**: Retrieves the color of the collectible.
   - **Return Type**: `Color`
     - the color of the collectible.

3. `static String getInstruction()`
   - **Description**: Retrieves the instruction for the collectible.
   - **Return Type**: `String`
     - the instruction for the collectible.

4. `int compareTo(ColorBonus o)`
   - **Description**: Compares this color bonus with another color bonus for order.
   - **Parameters**:
     - `o`: the color bonus to be compared.
   - **Return Type**: `int`
     - a negative integer, zero, or a positive integer as this collectible is less than, equal to, or greater than the specified collectible.

### `ElementalCrest` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the Elemental crest.

#### Method:

1. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the collectible.
   - **Return Type**: `String`
     - a string description of the collectible, representing it as "EC"



### `TimeWarp` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the Time warp power.

#### Method:

1. `static String getInstruction()`
   - **Description**: Retrieves the instruction for the collectible.
   - **Return Type**: `String`
     - the instruction for the collectible.

2. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the collectible.
   - **Return Type**: `String`
     - a string description of the collectible, representing it as "TW".


### `Creature` class

- **Package**: `game.creatures`
- **Type**: Abstract Class
- **Description**: This class holds all abstract methods common in the creatures.

#### Methods:

1. `boolean isAlive()`
  - **Description**: method that knows whether a creature is available for attacking or not.
  - **Return Type**: `boolean`
    - `true` if it is still available to attack.
    - `false` if it has already been attacked before.

2. `int getScore()` 
  - **Description**: gets score of dice needed to attack the creature.
  - **Return Type**: `int` return integer value of the score.

3. `boolean attack(int value)`
  - **Description**: if attack is possible or not.
  - **Parameters**: value of dice to attack.
  - **Return Type**: `boolean`
    - `true` if an attack is possible.
    - `false` if not possible.


4. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.


### `Dragon` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the Dragon creature.

#### Methods:

1. `boolean isAlive()`
   - **Description**: Checks whether the dragon is alive or not.
   - **Return Type**: `boolean`
     - true if the dragon is alive, false otherwise.

2. `boolean attack(int value, HitRegionsOfDragons region)`
   - **Description**: Performs an attack on the dragon at a specified region.
   - **Parameters**:
     - `value`: the value of the attack.
     - `region`: the region of the dragon to be attacked.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

3. `int getScore()`
   - **Description**: Retrieves the score of the dragon.
   - **Return Type**: `int`
     - the score of the dragon.

4. `int getDragonNumber()`
   - **Description**: Retrieves the number of the dragon.
   - **Return Type**: `int`
     - the number of the dragon.

5. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the dragon.
   - **Return Type**: `String`
     - a string description of the dragon, representing it as "Dragon".

6. `Object[] getHealth()`
   - **Description**: Retrieves the health status of the dragon.
   - **Return Type**: `Object[]`
     - an array containing the health status of the dragon.

7. `Dragon()`
   - **Description**: Constructor for creating a dragon object with default dragon number 1.

8. `boolean equals(Object o)`
   - **Description**: Compares this dragon to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.



### `Guardian` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the gaia guardians.

#### Methods:

1. `boolean attack()`
   - **Description**: Performs an attack action by the guardian.
   - **Return Type**: `boolean`
     - true if the attack was successful and the guardian is still alive, false otherwise.

2. `boolean isAlive()`
   - **Description**: Checks whether the guardian is alive or not.
   - **Return Type**: `boolean`
     - true if the guardian is alive, false otherwise.

3. `int getScore()`
   - **Description**: Retrieves the score of the guardian.
   - **Return Type**: `int`
     - the score of the guardian.

4. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the guardian.
   - **Return Type**: `String`
     - a string description of the guardian, representing its score if alive, otherwise "X".

5. `boolean equals(Object o)`
   - **Description**: Compares this guardian to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.


### `Lion` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the Lion.

#### Methods:

1. `boolean attack()`
   - **Description**: Performs an attack action by the lion.
   - **Return Type**: `boolean`
     - true if the attack was successful and the lion is still alive, false otherwise.

2. `boolean isAlive()`
   - **Description**: Checks whether the lion is alive or not.
   - **Return Type**: `boolean`
     - true if the lion is alive, false otherwise.

3. `int getScore()`
   - **Description**: Retrieves the score of the lion.
   - **Return Type**: `int`
     - the score of the lion (always returns 0).

4. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the lion.
   - **Return Type**: `String`
     - a string description of the lion, representing it as "Lion".

5. `boolean equals(Object o)`
   - **Description**: Compares this lion to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.

### `Phoenix` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the Phoenix.

#### Methods:

1. `boolean attack()`
   - **Description**: Performs an attack action by the phoenix.
   - **Return Type**: `boolean`
     - true if the attack was successful and the phoenix is still alive, false otherwise.

2. `boolean isAlive()`
   - **Description**: Checks whether the phoenix is alive or not.
   - **Return Type**: `boolean`
     - true if the phoenix is alive, false otherwise.

3. `int getScore()`
   - **Description**: Retrieves the score of the phoenix.
   - **Return Type**: `int`
     - the score of the phoenix (always returns 0).

4. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the phoenix.
   - **Return Type**: `String`
     - a string description of the phoenix, representing it as "Phoenix".

5. `boolean equals(Object o)`
   - **Description**: Compares this phoenix to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.



### `HitRegionsOfDragons` class

- **Package**: `game.creatures`
- **Type**: enum
- **Description**: This class represents the different hit regions of a dragon.

### `Serpent` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the Serpent.

#### Methods:

1. `boolean attack()`
   - **Description**: Performs an attack action by the Hydra Serpent.
   - **Return Type**: `boolean`
     - true if the attack was successful and the Hydra Serpent is still alive, false otherwise.

2. `boolean isAlive()`
   - **Description**: Checks whether the Hydra Serpent is alive or not.
   - **Return Type**: `boolean`
     - true if the Hydra Serpent is alive, false otherwise.

3. `int getHeadNumber()`
   - **Description**: Retrieves the number of heads of the Hydra Serpent.
   - **Return Type**: `int`
     - the number of heads of the Hydra Serpent.

4. `int getScore()`
   - **Description**: Retrieves the score of the Hydra Serpent.
   - **Return Type**: `int`
     - the score of the Hydra Serpent (always returns 0).

5. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the Hydra Serpent.
   - **Return Type**: `String`
     - a string description of the Hydra Serpent, representing it as "Hydra Serpent" followed by its id.

6. `int getSerpentNumber()`
   - **Description**: Retrieves the id of the Hydra Serpent.
   - **Return Type**: `int`
     - the id of the Hydra Serpent.

7. `boolean equals(Object o)`
   - **Description**: Compares this Hydra Serpent to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.

### `Dice` class

- **Package**: `game.dice`
- **Type**: Abstract Class
- **Description**: This class creates and manipulates the dice object.


#### Methods:

1. `void setValue(int value)`
   - **Description**: Sets the value of the dice.
   - **Parameters**:
     - `value`: the value to set for the dice.
   - **Return Type**: `void`

2. `int getValue()`
   - **Description**: Retrieves the value of the dice.
   - **Return Type**: `int`
     - the value of the dice.

3. `Color getRealm()`
   - **Description**: Retrieves the realm color of the dice.
   - **Return Type**: `Color`
     - the realm color of the dice.

4. `DiceStatus getDiceStatus()`
   - **Description**: Retrieves the status of the dice.
   - **Return Type**: `DiceStatus`
     - the status of the dice.

5. `void setDiceStatus(DiceStatus status)`
   - **Description**: Sets the status of the dice.
   - **Parameters**:
     - `status`: the status to set for the dice.
   - **Return Type**: `void`

### `BlueDice` class

- **Package**: `game.dice`
- **Type**: SubClass
- **Description**: This Class inherits functions from the Dice class to implement any moves resulting in the blue realm.


#### Methods:

1. `String toString()`
   - **Description**: Returns a string representation of the BlueDice object.
   - **Return Type**: `String`
     - a string formatted as "color(value)" with the color in blue.

2. `void setValue(int value)`
   - **Description**: Sets the value of the dice.
   - **Parameters**:
     - `value`: the value to set for the dice.
   - **Return Type**: `void`

3. `int getValue()`
   - **Description**: Retrieves the value of the dice.
   - **Return Type**: `int`
     - the value of the dice.

4. `Color getRealm()`
   - **Description**: Retrieves the realm color of the dice.
   - **Return Type**: `Color`
     - the realm color of the dice.

5. `DiceStatus getDiceStatus()`
   - **Description**: Retrieves the status of the dice.
   - **Return Type**: `DiceStatus`
     - the status of the dice.

6. `void setDiceStatus(DiceStatus status)`
   - **Description**: Sets the status of the dice.
   - **Parameters**:
     - `status`: the status to set for the dice.
   - **Return Type**: `void`

7. `boolean equals(Object o)`
   - **Description**: Compares this BlueDice to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.

### `GreenDice` class

- **Package**: `game.dice`
- **Type**: SubClass
- **Description**: This Class inherits functions from the Dice class to implement any moves resulting in the Green realm.


#### Methods:

1. `String toString()`
   - **Description**: Returns a string representation of the GreenDice object.
   - **Return Type**: `String`
     - a string formatted as "color(value)" with the color in green.

2. `void setValue(int value)`
   - **Description**: Sets the value of the dice.
   - **Parameters**:
     - `value`: the value to set for the dice.
   - **Return Type**: `void`

3. `int getValue()`
   - **Description**: Retrieves the value of the dice.
   - **Return Type**: `int`
     - the value of the dice.

4. `Color getRealm()`
   - **Description**: Retrieves the realm color of the dice.
   - **Return Type**: `Color`
     - the realm color of the dice.

5. `DiceStatus getDiceStatus()`
   - **Description**: Retrieves the status of the dice.
   - **Return Type**: `DiceStatus`
     - the status of the dice.

6. `void setDiceStatus(DiceStatus status)`
   - **Description**: Sets the status of the dice.
   - **Parameters**:
     - `status`: the status to set for the dice.
   - **Return Type**: `void`

7. `boolean equals(Object o)`
   - **Description**: Compares this GreenDice to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.

### `MagentaDice` class

- **Package**: `game.dice`
- **Type**: SubClass
- **Description**: This Class inherits functions from the Dice class to implement any moves resulting in the Magenta realm.


#### Methods:

1. `String toString()`
   - **Description**: Returns a string representation of the MagentaDice object.
   - **Return Type**: `String`
     - a string formatted as "color(value)" with the color in magenta.

2. `void setValue(int value)`
   - **Description**: Sets the value of the dice.
   - **Parameters**:
     - `value`: the value to set for the dice.
   - **Return Type**: `void`

3. `int getValue()`
   - **Description**: Retrieves the value of the dice.
   - **Return Type**: `int`
     - the value of the dice.

4. `Color getRealm()`
   - **Description**: Retrieves the realm color of the dice.
   - **Return Type**: `Color`
     - the realm color of the dice.

5. `DiceStatus getDiceStatus()`
   - **Description**: Retrieves the status of the dice.
   - **Return Type**: `DiceStatus`
     - the status of the dice.

6. `void setDiceStatus(DiceStatus status)`
   - **Description**: Sets the status of the dice.
   - **Parameters**:
     - `status`: the status to set for the dice.
   - **Return Type**: `void`

7. `boolean equals(Object o)`
   - **Description**: Compares this MagentaDice to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.

### `RedDice` class

- **Package**: `game.dice`
- **Type**: SubClass
- **Description**: This Class inherits functions from the Dice class to implement any moves resulting in the Red realm.


#### Methods:

1. `String toString()`
   - **Description**: Returns a string representation of the RedDice object.
   - **Return Type**: `String`
     - a string formatted as "color(value)" with the color in red.

2. `void setValue(int value)`
   - **Description**: Sets the value of the dice.
   - **Parameters**:
     - `value`: the value to set for the dice.
   - **Return Type**: `void`

3. `int getValue()`
   - **Description**: Retrieves the value of the dice.
   - **Return Type**: `int`
     - the value of the dice.

4. `Color getRealm()`
   - **Description**: Retrieves the realm color of the dice.
   - **Return Type**: `Color`
     - the realm color of the dice.

5. `DiceStatus getDiceStatus()`
   - **Description**: Retrieves the status of the dice.
   - **Return Type**: `DiceStatus`
     - the status of the dice.

6. `void setDiceStatus(DiceStatus status)`
   - **Description**: Sets the status of the dice.
   - **Parameters**:
     - `status`: the status to set for the dice.
   - **Return Type**: `void`

7. `void selectsDragon(int dragonNumber)`
   - **Description**: Sets the dragon number selected by the red dice.
   - **Parameters**:
     - `dragonNumber`: the number of the dragon to select.
   - **Return Type**: `void`

8. `int getDragonNumber()`
   - **Description**: Retrieves the dragon number selected by the red dice.
   - **Return Type**: `int`
     - the selected dragon number.

9. `boolean equals(Object o)`
   - **Description**: Compares this RedDice to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.


### `WhiteDice` class

- **Package**: `game.dice`
- **Type**: SubClass
- **Description**: This Class inherits functions from the Dice class to implement any moves resulting in the realm selected by the Player.


#### Methods:

1. `String toString()`
   - **Description**: Returns a string representation of the WhiteDice object.
   - **Return Type**: `String`
     - a string formatted as "color(value)".

2. `void setValue(int value)`
   - **Description**: Sets the value of the dice.
   - **Parameters**:
     - `value`: the value to set for the dice.
   - **Return Type**: `void`

3. `int getValue()`
   - **Description**: Retrieves the value of the dice.
   - **Return Type**: `int`
     - the value of the dice.

4. `Color getRealm()`
   - **Description**: Retrieves the realm color of the dice.
   - **Return Type**: `Color`
     - the realm color of the dice.

5. `DiceStatus getDiceStatus()`
   - **Description**: Retrieves the status of the dice.
   - **Return Type**: `DiceStatus`
     - the status of the dice.

6. `void setDiceStatus(DiceStatus status)`
   - **Description**: Sets the status of the dice.
   - **Parameters**:
     - `status`: the status to set for the dice.
   - **Return Type**: `void`

7. `boolean equals(Object o)`
   - **Description**: Compares this WhiteDice to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.

### `YellowDice` class

- **Package**: `game.dice`
- **Type**: SubClass
- **Description**: This Class inherits functions from the Dice class to implement any moves resulting in the blue realm.


#### Methods:

1. `String toString()`
   - **Description**: Returns a string representation of the YellowDice object.
   - **Return Type**: `String`
     - a string formatted as "color(value)" with the color in yellow.

2. `void setValue(int value)`
   - **Description**: Sets the value of the dice.
   - **Parameters**:
     - `value`: the value to set for the dice.
   - **Return Type**: `void`

3. `int getValue()`
   - **Description**: Retrieves the value of the dice.
   - **Return Type**: `int`
     - the value of the dice.

4. `Color getRealm()`
   - **Description**: Retrieves the realm color of the dice.
   - **Return Type**: `Color`
     - the realm color of the dice.

5. `DiceStatus getDiceStatus()`
   - **Description**: Retrieves the status of the dice.
   - **Return Type**: `DiceStatus`
     - the status of the dice.

6. `void setDiceStatus(DiceStatus status)`
   - **Description**: Sets the status of the dice.
   - **Parameters**:
     - `status`: the status to set for the dice.
   - **Return Type**: `void`

7. `boolean equals(Object o)`
   - **Description**: Compares this YellowDice to the specified object.
   - **Parameters**:
     - `o`: the object to compare with.
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.

### `DiceStatus` class

- **Package**: `game.engine`
- **Type**: enum
- **Description**: This class represents
the different options for the status of the Die.

### `AntiCheatService` class

- **Package**: `game.engine`
- **Type**: Interface
- **Description**: An interface that is used as a defensive mechanism by throwing any exceptions that may occur due to attempts of hacking and successfully block said attempted attack.

#### Methods:

1. `void checkPlayerScore(Player player) throws CheatDetectedException`
   - **Description**: Checks the score of the specified player for any discrepancies or cheating.
   - **Parameters**:
     - `player`: the player whose score is to be checked.
   - **Return Type**: `void`
   - **Throws**: `CheatDetectedException` if cheating is detected.

2. `void checkPlayerReward(Player player) throws CheatDetectedException`
   - **Description**: Checks the rewards of the specified player for any discrepancies or cheating.
   - **Parameters**:
     - `player`: the player whose rewards are to be checked.
   - **Return Type**: `void`
   - **Throws**: `CheatDetectedException` if cheating is detected.

3. `void checkGameBoard(GameBoard gameBoard) throws CheatDetectedException`
   - **Description**: Checks the game board for any discrepancies or signs of tampering.
   - **Parameters**:
     - `gameBoard`: the game board to be checked.
   - **Return Type**: `void`
   - **Throws**: `CheatDetectedException` if cheating or tampering is detected.

4. `void checkGameStatus(GameStatus gameStatus) throws CheatDetectedException`
   - **Description**: Checks the current status of the game for any discrepancies or cheating.
   - **Parameters**:
     - `gameStatus`: the status of the game to be checked.
   - **Return Type**: `void`
   - **Throws**: `CheatDetectedException` if cheating is detected.

5. `void checkDice(Dice[] dice) throws CheatDetectedException`
   - **Description**: Checks the dice for any discrepancies or signs of tampering.
   - **Parameters**:
     - `dice`: an array of dice to be checked.
   - **Return Type**: `void`
   - **Throws**: `CheatDetectedException` if cheating or tampering is detected.

### `CLIGameController` class

- **Package**: `game.engine`
- **Type**: SubClass
- **Description**: This class represents the command line game controller and inherits most of its methods from the GameController abstract class.

#### Methods:

1. `void startGame()`
   - **Description**: Initiates the game and executes the game logic for each round.
   - **Parameters**: None
   - **Return Type**: `void`

2. `void mainMenu()`
   - **Description**: Displays the main menu of the game and handles user choices.
   - **Parameters**: None
   - **Return Type**: `void`

3. `void checkArcaneBoost(Player player)`
   - **Description**: Checks if the player can activate the arcane boost power and prompts the user for activation.
   - **Parameters**:
     - `player`: The player whose arcane boost availability needs to be checked.
   - **Return Type**: `void`

4. `void playExtraTurn(Player player)`
   - **Description**: Executes an extra turn for the player if the arcane boost power is activated.
   - **Parameters**:
     - `player`: The player who is taking the extra turn.
   - **Return Type**: `void`

5. `boolean checkTimeWarp()`
   - **Description**: Checks if the active player can activate the time warp power and prompts the user for activation.
   - **Parameters**: None
   - **Return Type**: `boolean`
     - true if the time warp power is activated, false otherwise.

6. `void playEssenceBonus(Player player)`
   - **Description**: Executes the essence bonus action for the player.
   - **Parameters**:
     - `player`: The player who receives the essence bonus.
   - **Return Type**: `void`

7. `void playColorBonus(Player player, Color color)`
   - **Description**: Executes the color bonus action for the player based on the specified color.
   - **Parameters**:
     - `player`: The player who receives the color bonus.
     - `color`: The color for which the bonus action is executed.
   - **Return Type**: `void`

8. `void processRewardQueue(Player player, Collectibles[] rewards)`
   - **Description**: Processes the reward queue for the player by executing each reward.
   - **Parameters**:
     - `player`: The player who receives the rewards.
     - `rewards`: An array of collectibles representing the rewards to be processed.
   - **Return Type**: `void`

9. `void performReward(Player player, Collectibles reward)`
   - **Description**: Executes the specified reward for the player.
   - **Parameters**:
     - `player`: The player who receives the reward.
     - `reward`: The collectible representing the reward to be executed.
   - **Return Type**: `void`

10. `void playRound()`
    - **Description**: Executes a round of the game, including player turns and bonus actions.
    - **Parameters**: None
    - **Return Type**: `void`

11. `boolean containsAvailableDie()`
    - **Description**: Checks if there are any available dice for the current round.
    - **Parameters**: None
    - **Return Type**: `boolean`
      - true if there are available dice, false otherwise.

12. `Dice selectValidDie(Player player, Dice[] dice, boolean checkTimeWarp)`
    - **Description**: Selects a valid die for the player to use based on available moves.
    - **Parameters**:
      - `player`: The player who is selecting the die.
      - `dice`: An array of dice from which the player can choose.
      - `checkTimeWarp`: A boolean indicating whether to check for time warp activation.
    - **Return Type**: `Dice`
      - The selected die for the player.

13. `Dice[] filterDiceWithPossibleMoves(Player player, Dice[] dice)`
    - **Description**: Filters the provided dice array to include only those with possible moves for the player.
    - **Parameters**:
      - `player`: The player for whom the dice are being filtered.
      - `dice`: An array of dice to be filtered.
    - **Return Type**: `Dice[]`
      - An array of dice with possible moves for the player.

14. `GameGuide getGameGuide()`
    - **Description**: Retrieves the game guide associated with the game.
    - **Parameters**: None
    - **Return Type**: `GameGuide`
      - The game guide object.

15. `Move selectValidMove(Player player, Dice selectedDie)`
    - **Description**: Selects a valid move for the player based on the selected die.
    - **Parameters**:
      - `player`: The player for whom the move is being selected.
      - `selectedDie`: The die selected by the player.
    - **Return Type**: `Move`
      - The selected move for the player.

16. `void playTurn()`
    - **Description**: Executes a turn for the active player, including rolling dice, selecting a die, and making a move.
    - **Parameters**: None
    - **Return Type**: `void`

17. `void playPassiveTurn()`
    - **Description**: Executes a turn for the passive player, including selecting a die and making a move.
    - **Parameters**: None
    - **Return Type**: `void`

18. `void displayAvailableRealms(Player player)`
    - **Description**: Displays the available realms for the specified player.
    - **Parameters**:
      - `player`: The player for whom the available realms are being displayed.
    - **Return Type**: `void`

19. `Player getPlayerName(String prompt)`
    - **Description**: Prompts the user to enter a player name and returns a new Player object with the entered name.
    - **Parameters**:
      - `prompt`: The prompt to be displayed to the user.
    - **Return Type**: `Player`
      - A new Player object with the entered name.

20. `boolean switchPlayer()`
    - **Description**: Switches the active and passive players.
    - **Parameters**: None
    - **Return Type**: `boolean`
      - true if the player switch was successful, false otherwise.

21. `Dice[] rollDice()`
    - **Description**: Rolls all available dice for the current turn.
    - **Parameters**: None
    - **Return Type**: `Dice[]`
      - An array of the currently rolled dice.

22. `void resetDice()`
    - **Description**: Resets the dice status to be all available at the beginning of each round.
    - **Parameters**: None
    - **Return Type**: `void`

23. `Dice[] getAvailableDice()`
    - **Description**: Gets the dice available for rolling or rerolling.
    - **Parameters**: None
    - **Return Type**: `Dice[]`
      - An array of dice available for the current turn.

24. `Dice[] getAllDice()`
    - **Description**: Gets all six dice, providing their current state and value within the game.
    - **Parameters**: None
    - **Return Type**: `Dice[]`
      - An array of all six dice with each die's state and value.

25. `Dice[] getForgottenRealmDice()`
    - **Description**: Gets all dice located in the forgotten realm.
    - **Parameters**: None
    - **Return Type**: `Dice[]`
      - An array of all dice in the forgotten realm.

26. `Move[] getAllPossibleMoves(Player player)`
    - **Description**: Gets all possible moves for all currently rolled dice for a given player.
    - **Parameters**:
      - `player`: The player for whom to determine possible moves.
    - **Return Type**: `Move[]`
      - An array of all possible moves for all rolled dice.

27. `Move[] getPossibleMovesForAvailableDice(Player player)`
    - **Description**: Gets possible moves for all currently rolled dice for a given player.
    - **Parameters**:
      - `player`: The player for whom to determine possible moves.
    - **Return Type**: `Move[]`
      - An array of all possible moves for all rolled dice.

28. `private Move[] getPossibleMovesForDice(Player player, Dice[] dice)`
    - **Description**: Gets possible moves for all currently rolled dice for a given player from a specified array of dice.
    - **Parameters**:
      - `player`: The player for whom to determine possible moves.
      - `dice`: An array of dice for which to determine possible moves.
    - **Return Type**: `Move[]`
      - An array of all possible moves for all rolled dice.

29. `@Override public Move[] getPossibleMovesForADie(Player player, Dice dice)`
    - **Description**: Gets possible moves for a specified die for a given player.
    - **Parameters**:
      - `player`: The player for whom to determine possible moves.
      - `dice`: The die for which to determine possible moves.
    - **Return Type**: `Move[]`
      - An array of possible moves for the specified die and player.

30. `@Override public GameBoard getGameBoard()`
    - **Description**: Gets the game board associated with the game.
    - **Parameters**: None
    - **Return Type**: `GameBoard`
      - The game board object.

31. ` Player getActivePlayer()`
    - **Description**: Gets the active player in the game.
    - **Parameters**: None
    - **Return Type**: `Player`
      - The active player object.

32. `Player getPassivePlayer()`
    - **Description**: Gets the passive player in the game.
    - **Parameters**: None
    - **Return Type**: `Player`
      - The passive player object.

33. `ScoreSheet getScoreSheet(Player player)`
    - **Description**: Gets the score sheet for the specified player.
    - **Parameters**:
      - `player`: The player for whom to get the score sheet.
    - **Return Type**: `ScoreSheet`
      - The score sheet object for the specified player.

34. `GameStatus getGameStatus()`
    - **Description**: Gets the game status.
    - **Parameters**: None
    - **Return Type**: `GameStatus`
      - The game status object.

35. `boolean makeMove(Player player, Move move)`
    - **Description**: Executes a move for the specified player.
    - **Parameters**:
      - `player`: The player making the move.
      - `move`: The move to be executed.
    - **Return Type**: `boolean`
      - true if the move was successfully executed, false otherwise.

36. `void endGame()`
    - **Description**: Ends the game and displays the final scores and winner.
    - **Parameters**: None
    - **Return Type**: `void`

### `CurrentStatus` class

- **Package**: `game.engine`
- **Type**: enum
- **Description**: This class represents
the different options for the status of the game.

### `Instruction` class

- **Package**: `game.engine`
- **Type**: Enum
- **Description**: This provides various choices for the instrcution to be given to game controller.

### `PlayerStatus` class

- **Package**: `game.engine`
- **Type**: Enum
- **Description**: This provides status of player either active or passive.

### `GameBoard` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents
the game board where all the realms appear and the dice playing area

#### Methods:

1. `public void displayAllRealms()`
    - **Description**: Displays all realms on the game board.
    - **Parameters**: None
    - **Return Type**: `void`

2. `public void displayAllPossibleMoves(Move[] moves)`
    - **Description**: Displays all possible moves for the given array of moves.
    - **Parameters**:
      - `moves`: An array of moves to display.
    - **Return Type**: `void`

3. `public void displayMainDiceDeck()`
    - **Description**: Displays the main dice deck.
    - **Parameters**: None
    - **Return Type**: `void`

4. `public Player getPlayer1()`
    - **Description**: Retrieves player 1.
    - **Parameters**: None
    - **Return Type**: `Player`

5. `public Player getPlayer2()`
    - **Description**: Retrieves player 2.
    - **Parameters**: None
    - **Return Type**: `Player`

6. `public void display()`
    - **Description**: Displays the game board.
    - **Parameters**: None
    - **Return Type**: `void`

7. `public Dice[] getDice()`
    - **Description**: Retrieves all dice.
    - **Parameters**: None
    - **Return Type**: `Dice[]`

### `GameScore` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class includes total score of the game including the realm scores

#### Methods:

1. `void updateGameScore()`
   - **Description**: Resets attributes and recalculates the total score and elemental crests for all realms.
   - **Return Type**: `void`

2. `int getYellowRealmScore()`
   - **Description**: Retrieves the total score of the Yellow realm.
   - **Return Type**: `int`

3. `int getGreenRealmScore()`
   - **Description**: Retrieves the total score of the Green realm.
   - **Return Type**: `int`

4. `int getRedRealmScore()`
   - **Description**: Retrieves the total score of the Red realm.
   - **Return Type**: `int`

5. `int getMagentaRealmScore()`
   - **Description**: Retrieves the total score of the Magenta realm.
   - **Return Type**: `int`

6. `int getBlueRealmScore()`
   - **Description**: Retrieves the total score of the Blue realm.
   - **Return Type**: `int`

7. `int getFinalScore()`
   - **Description**: Retrieves the final score, considering the total elemental crests and the minimum score among realms.
   - **Return Type**: `int`

8. `int getTotalElementalCrests()`
   - **Description**: Retrieves the total number of elemental crests across all realms.
   - **Return Type**: `int`

9. `int getTotalScore()`
   - **Description**: Retrieves the total score of all realms combined.
   - **Return Type**: `int`

10. `String toString()`
    - **Description**: Generates a formatted string representation of the game score, including player name, realm scores, elemental crests, and final score.
    - **Return Type**: `String`

### `GameGuide` class

**Package**: `game.engine`
**Type**: Class
**Description**: This class acts as a helper tool for the players to help them understand any tool or property about the game they don't understand.

#### Methods:

1. `void displayMenu()`
   - **Description**: Displays the main menu of the game.
   - **Parameters**: None
   - **Return Type**: `void`

2. `void displayNumberedChoice(Object[] array)`
   - **Description**: Displays a list of choices with corresponding numbers.
   - **Parameters**:
     - `array`: An array of objects representing the choices.
       - Type: `Object[]`
   - **Return Type**: `void`

3. `void displayInstructions(Instruction instruction)`
   - **Description**: Displays the instructions based on the provided instruction type.
   - **Parameters**:
     - `instruction`: The type of instruction to display.
       - Type: `Instruction`
   - **Return Type**: `void`

4. `int getUserChoice(int minBound, int maxBound)`
   - **Description**: Prompts the user to enter a valid integer choice within the specified range.
   - **Parameters**:
     - `minBound`: The minimum valid choice.
       - Type: `int`
     - `maxBound`: The maximum valid choice.
       - Type: `int`
   - **Return Type**: `int`
     - the user's valid integer choice.

5. `boolean getUserBooleanChoice()`
   - **Description**: Prompts the user to choose between yes or no.
   - **Parameters**: None
   - **Return Type**: `boolean`
     - true if the user chooses yes, false if the user chooses no.

6. `int getUserIntChoice()`
   - **Description**: Prompts the user to enter a valid integer.
   - **Parameters**: None
   - **Return Type**: `int`
     - the user's valid integer choice.

7. `void closeScanner()`
   - **Description**: Closes the scanner used for user input.
   - **Parameters**: None
   - **Return Type**: `void`

### `GameStatus` class

**Package**: `game.engine`
**Type**: Class
**Description**: This class gets the current game status and checks if there is a change to be made and act accordingly.

#### Methods:

1. `CurrentStatus getGameStatus()`
   - **Description**: Retrieves the current status of the game.
   - **Return Type**: `CurrentStatus`
     - the current status of the game.

2. `void incrementRound()`
   - **Description**: Increments the round counter of the game.
   - **Parameters**: None
   - **Return Type**: `void`

3. `void resetTurn()`
   - **Description**: Resets the turn counter to 1.
   - **Parameters**: None
   - **Return Type**: `void`

4. `void incrementTurn()`
   - **Description**: Increments the turn counter of the game.
   - **Parameters**: None
   - **Return Type**: `void`

5. `void setGameStatus(CurrentStatus status)`
   - **Description**: Sets the current status of the game.
   - **Parameters**:
     - `status`: The status to be set.
       - Type: `CurrentStatus`
   - **Return Type**: `void`

### `Move` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class controls how a move is processed in the game.

#### Methods:

1. `Creature getCreature()`
   - **Description**: Retrieves the creature associated with the move.
   - **Return Type**: `Creature`
     - the creature associated with the move.

2. `String toString()`
   - **Description**: Returns a string representation of the move.
   - **Return Type**: `String`
     - a string representation of the move.

3. `Dice getDice()`
   - **Description**: Retrieves the dice associated with the move.
   - **Return Type**: `Dice`
     - the dice associated with the move.

4. `int compareTo(Move o)`
   - **Description**: Compares this move with another move based on realm and dice value.
   - **Parameters**:
     - `o`: The move to compare to.
       - Type: `Move`
   - **Return Type**: `int`
     - 0 if the moves are equal, a positive integer if this move is greater, and a negative integer if this move is smaller.

5. `boolean equals(Object o)`
   - **Description**: Indicates whether some other object is "equal to" this one.
   - **Parameters**:
     - `o`: The object to compare to.
       - Type: `Object`
   - **Return Type**: `boolean`
     - true if the objects are equal, false otherwise.

### `Player` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents
all the functions of the player and construct a player object.

#### Methods:

1. `private boolean checkSpecialCharacters(String name)`
   - **Description**: Checks if the given name contains only alphanumeric characters.
   - **Parameters**:
     - `name`: The name to be checked.
       - Type: `String`
   - **Return Type**: `boolean`
     - true if the name contains only alphanumeric characters, false otherwise.

2. `private void initializeRealms()`
   - **Description**: Initializes all realms at the start of player initialization.

3. `public void setPlayerStatus(PlayerStatus status)`
   - **Description**: Sets the player's status.
   - **Parameters**:
     - `status`: The status to be set.
       - Type: `PlayerStatus`

4. `boolean receivePower(Collectibles power)`
   - **Description**: Receives a power and sets its status to ENABLED.
   - **Parameters**:
     - `power`: The power to be received.
       - Type: `Collectibles`
   - **Return Type**: `boolean`
     - true if the power was successfully received, false otherwise.

5. `public ScoreSheet getScoreSheet()`
   - **Description**: Retrieves the player's score sheet.
   - **Return Type**: `ScoreSheet`
     - the player's score sheet.

6. `public GameScore getGameScore()`
   - **Description**: Retrieves the player's game score.
   - **Return Type**: `GameScore`
     - the player's game score.

7. `public boolean isTimeWarpAvailable()`
   - **Description**: Checks if the player has any unused Time Warp powers.
   - **Return Type**: `boolean`
     - true if Time Warp powers are available, false otherwise.

8. `public boolean isArcaneBoostAvailable()`
   - **Description**: Checks if the player has any unused Arcane Boost powers.
   - **Return Type**: `boolean`
     - true if Arcane Boost powers are available, false otherwise.

9. `public void useTimeWarpPower()`
   - **Description**: Uses a Time Warp power and sets its status to USED.

10. `public void useArcaneBoostPower()`
    - **Description**: Uses an Arcane Boost power and sets its status to USED.

11. `public String getName()`
    - **Description**: Retrieves the player's name.
    - **Return Type**: `String`
      - the player's name.

12. `public int getTotalTimeWarpPowersCollected()`
    - **Description**: Retrieves the total number of Time Warp powers collected and unused.
    - **Return Type**: `int`
      - the total number of Time Warp powers collected and unused.

13. `public int getTotalArcaneBoostPowersCollected()`
    - **Description**: Retrieves the total number of Arcane Boost powers collected and unused.
    - **Return Type**: `int`
      - the total number of Arcane Boost powers collected and unused.

14. `public ArcaneBoost[] getArcaneBoosts()`
    - **Description**: Retrieves an array of Arcane Boost powers collected by the player.
    - **Return Type**: `ArcaneBoost[]`
      - an array of Arcane Boost powers collected by the player.

15. `public TimeWarp[] getTimeWarps()`
    - **Description**: Retrieves an array of Time Warp powers collected by the player.
    - **Return Type**: `TimeWarp[]`
      - an array of Time Warp powers collected by the player.

16. `public Realm getRealm(Color color)`
    - **Description**: Retrieves the realm of the specified color.
    - **Parameters**:
      - `color`: The color of the realm to retrieve.
        - Type: `Color`
    - **Return Type**: `Realm`
      - the realm of the specified color.

17. `public Realm[] getRealms()`
    - **Description**: Retrieves an array of all realms.
    - **Return Type**: `Realm[]`
      - an array of all realms.

18. `public Realm getRealm(Dice dice)`
    - **Description**: Retrieves the realm associated with the given dice.
    - **Parameters**:
      - `dice`: The dice to retrieve the realm for.
        - Type: `Dice`
    - **Return Type**: `Realm`
      - the realm associated with the given dice.

19. `@Override public String toString()`
    - **Description**: Returns the player's name.
    - **Return Type**: `String`
      - the player's name.

20. `public PlayerStatus getPlayerStatus()`
    - **Description**: Retrieves the player's status.
    - **Return Type**: `PlayerStatus`
      - the player's status.

21. `public Dice getSelectedDie()`
    - **Description**: Retrieves the selected die.
    - **Return Type**: `Dice`
      - the selected die.

22. `public void setSelectedDie(Dice selectedDie)`
    - **Description**: Sets the selected die.
    - **Parameters**:
      - `selectedDie`: The die to be set as selected.
        - Type: `Dice`

### `ScoreSheet` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class manages the ScoreSheet.

#### Methods:

1. `void updateScoresheet()`
    - **Description**: Updates the score sheet with the current scores of all realms.

2. `void displayScoreSheet()`
    - **Description**: Displays the score sheet.

3. `void displayRedRealm()`
    - **Description**: Displays the details of the Red Realm.

4. `String toString()`
    - **Description**: Returns a string representation of the player's score sheet.

5. `Creature getCreatureByRealm(Dice dice)`
    - **Description**: Retrieves the creature associated with the specified realm.
    - **Parameters**:
      - `dice`: The dice representing the realm to retrieve the creature for.
        - Type: `Dice`
    - **Return Type**: `Creature`
      - the creature associated with the specified realm.

### `StandardAntiCheatService` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: this class implements the interface anti cheat service to deal with the exceptions the interface throws a the machine.

#### Methods:

1. `void checkPlayerScore(Player player)`
   - **Description**: Checks the score of the specified player.
   - **Parameters**:
     - `player`: The player whose score needs to be checked.

2. `void checkGameBoard(GameBoard gameBoard)`
   - **Description**: Checks the state of the game board.
   - **Parameters**:
     - `gameBoard`: The game board to be checked.

3. `void checkGameStatus(GameStatus gameStatus)`
   - **Description**: Checks the status of the game.
   - **Parameters**:
     - `gameStatus`: The status of the game.

4. `void checkDice(Dice[] dice)`
   - **Description**: Checks the state of the dice.
   - **Parameters**:
     - `dice`: The array of dice to be checked.

5. `void checkPlayerReward(Player player) throws CheatDetectedException`
   - **Description**: Checks the reward of the specified player and throws a `CheatDetectedException` if cheating is detected.
   - **Parameters**:
     - `player`: The player whose reward needs to be checked.
   - **Throws**:
     - `CheatDetectedException`: If cheating is detected during the check.

### `Realm` class

- **Package**: `game.realms`
- **Type**: Abstract class
- **Description**: This class initializes all the methods that will be used in the realms.

#### Abstract Methods:

1. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

2. `abstract Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

3. `abstract int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

4. `abstract boolean isRealmAvailable()`
   - **Description**: Checks if the realm is available.
   - **Return Type**: `boolean`
     - true if the realm is available, false otherwise.

5. `abstract Collectibles[] getReward()`
   - **Description**: Retrieves the rewards of the realm.
   - **Return Type**: `Collectibles[]`
     - the rewards of the realm.

6. `abstract boolean checkReward()`
   - **Description**: Checks if there is a reward after attacking.
   - **Return Type**: `boolean`
     - true if there is a reward, false otherwise.

7. `abstract boolean attack(Move move)`
   - **Description**: Performs an attack using a move.
   - **Parameters**: `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

8. `abstract int getTotalScore()`
   - **Description**: Retrieves the total score of the realm.
   - **Return Type**: `int`
     - the total score of the realm.

9. `abstract int getNoElementalCrests()`
   - **Description**: Retrieves the number of elemental crests in the realm.
   - **Return Type**: `int`
     - the number of elemental crests in the realm.

10. `abstract String toString()`
    - **Description**: Method that is used for printing a string of the properties of the realm.
    - **Return Type**: `String`
      - a string description of the realm.

11. `abstract Move[] getRealmMoves()`
    - **Description**: Retrieves the possible moves for the realm.
    - **Return Type**: `Move[]`
      - an array of possible moves for the realm.

12. `abstract Creature getCreature(Dice dice)`
    - **Description**: Retrieves the creature associated with the given dice in the realm.
    - **Parameters**: `dice`: the dice used to find the associated creature.
    - **Return Type**: `Creature`
      - the creature associated with the given dice.




### `GreenRealm` class

- **Package**: `game.realms`
- **Type**: SubClass
- **Description**: This class manages the green realm.

#### Methods:

1. `Move[] getAvialableRealmMoves()`
   - **Description**: Retrieves the available moves in the realm.
   - **Return Type**: `Move[]`
     - an array of available moves in the realm.

2. `Collectibles[] getRewardsProperties()`
   - **Description**: Retrieves the reward properties of the realm.
   - **Return Type**: `Collectibles[]`
     - an array of reward properties of the realm.

3. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

4. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

5. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

6. `boolean isRealmAvailable()`
   - **Description**: Checks if the realm is available.
   - **Return Type**: `boolean`
     - true if the realm is available, false otherwise.

7. `Collectibles[] getReward()`
   - **Description**: Retrieves the rewards earned in the realm.
   - **Return Type**: `Collectibles[]`
     - an array of rewards earned in the realm.

8. `boolean checkReward()`
   - **Description**: Checks if there are rewards available in the realm.
   - **Return Type**: `boolean`
     - true if there are rewards available, false otherwise.

9. `boolean attack(Move move)`
   - **Description**: Performs an attack in the realm using a move.
   - **Parameters**: `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

10. `int getTotalScore()`
    - **Description**: Retrieves the total score of the realm.
    - **Return Type**: `int`
      - the total score of the realm.

11. `int getNoElementalCrests()`
    - **Description**: Retrieves the number of elemental crests in the realm.
    - **Return Type**: `int`
      - the number of elemental crests in the realm.

12. `String toString()`
    - **Description**: Retrieves a string representation of the realm.
    - **Return Type**: `String`
      - a string representation of the realm.

13. `Move[] getRealmMoves()`
    - **Description**: Retrieves the moves available in the realm.
    - **Return Type**: `Move[]`
      - an array of moves available in the realm.

14. `Creature getCreature(Dice dice)`
    - **Description**: Retrieves the creature associated with the given dice in the realm.
    - **Parameters**: `dice`: the dice used to find the associated creature.
    - **Return Type**: `Creature`
      - the creature associated with the given dice.





### `BlueRealm` class

- **Package**: `game.realms`
- **Type**:  SubClass
- **Description**: This class manages the blue realm.

#### Methods:

1. `boolean attack(Move move)`
   - **Description**: Performs an attack using a move.
   - **Parameters**: `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

2. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

3. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

4. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

5. `boolean isRealmAvailable()`
   - **Description**: Checks if the realm is available.
   - **Return Type**: `boolean`
     - true if the realm is available, false otherwise.

6. `int getTotalScore()`
   - **Description**: Retrieves the total score of the realm.
   - **Return Type**: `int`
     - the total score of the realm.

7. `Creature getCreature(Dice dice)`
   - **Description**: Retrieves the creature associated with the given dice in the realm.
   - **Parameters**: `dice`: the dice used to find the associated creature.
   - **Return Type**: `Creature`
     - the creature associated with the given dice.

8. `int getNoElementalCrests()`
   - **Description**: Retrieves the number of elemental crests in the realm.
   - **Return Type**: `int`
     - the number of elemental crests in the realm.

9. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the realm.
   - **Return Type**: `String`
     - a string description of the realm.

10. `Move[] getRealmMoves()`
    - **Description**: Retrieves the possible moves for the realm.
    - **Return Type**: `Move[]`
      - an array of possible moves for the realm.

11. `Collectibles[] getReward()`
    - **Description**: Retrieves the rewards of the realm.
    - **Return Type**: `Collectibles[]`
      - the rewards of the realm.

12. `boolean checkReward()`
    - **Description**: Checks if there is a reward after attacking.
    - **Return Type**: `boolean`
      - true if there is a reward, false otherwise.


### `RedRealm` class

- **Package**: `game.realms`
- **Type**: SubClass
- **Description**: This class manages the  realm

#### Methods:

1. `LinkedList<Move> redMovespopulate()`
   - **Description**: Populates the list of available moves for the Red Realm.
   - **Return Type**: `LinkedList<Move>`
     - the list of available moves for the Red Realm.

2. `Dragon[] initDragons()`
   - **Description**: Initializes the dragons in the realm with their attributes.
   - **Return Type**: `Dragon[]`
     - an array containing the initialized dragons.

3. `Object[] getRewardsProperties()`
   - **Description**: Retrieves the reward properties of the realm from a file.
   - **Return Type**: `Object[]`
     - an array containing the reward properties of the realm.

4. `boolean attack(Move move)`
   - **Description**: Performs an attack in the realm using a move.
   - **Parameters**: 
     - `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

5. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

6. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

7. `boolean isRealmAvailable()`
   - **Description**: Checks if the realm is available.
   - **Return Type**: `boolean`
     - true if the realm is available, false otherwise.

8. `boolean checkReward()`
   - **Description**: Checks if there are rewards available in the realm.
   - **Return Type**: `boolean`
     - true if there are rewards available, false otherwise.

9. `Collectibles[] getReward()`
   - **Description**: Retrieves the rewards earned in the realm.
   - **Return Type**: `Collectibles[]`
     - an array containing the rewards earned in the realm.

10. `int getTotalScore()`
    - **Description**: Retrieves the total score of the realm.
    - **Return Type**: `int`
      - the total score of the realm.

11. `int getNoElementalCrests()`
    - **Description**: Retrieves the number of elemental crests in the realm.
    - **Return Type**: `int`
      - the number of elemental crests in the realm.

12. `String toString()`
    - **Description**: Retrieves a string representation of the realm.
    - **Return Type**: `String`
      - a string representation of the realm.

13. `Move[] getRealmMoves()`
    - **Description**: Retrieves the moves available in the realm.
    - **Return Type**: `Move[]`
      - an array containing the moves available in the realm.

14. `Creature getCreature(Dice dice)`
    - **Description**: Retrieves the creature associated with the given dice in the realm.
    - **Parameters**: 
      - `dice`: the dice used to find the associated creature.
    - **Return Type**: `Creature`
      - the creature associated with the given dice.

15. `int getStatus()`
    - **Description**: Retrieves the status of the realm.
    - **Return Type**: `int`
      - the status of the realm.

### `YellowRealm` class

- **Package**: `game.realms`
- **Type**: SubClass
- **Description**: This class manages the yellow realm.

#### Methods:

1. `Collectibles[] getRewardsProperties()`
   - **Description**: Retrieves the reward properties of the realm from a file.
   - **Return Type**: `Collectibles[]`
     - an array containing the reward properties of the realm.

2. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

3. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

4. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

5. `boolean isRealmAvailable()`
   - **Description**: Checks if the realm is available for attack.
   - **Return Type**: `boolean`
     - true if the realm is available for attack, false otherwise.

6. `Collectibles[] getReward()`
   - **Description**: Retrieves the reward earned in the realm.
   - **Return Type**: `Collectibles[]`
     - an array containing the reward earned in the realm.

7. `boolean checkReward()`
   - **Description**: Checks if there is a reward available in the realm.
   - **Return Type**: `boolean`
     - true if there is a reward available, false otherwise.

8. `boolean attack(Move move)`
   - **Description**: Performs an attack in the realm using a move.
   - **Parameters**: 
     - `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

9. `int getTotalScore()`
   - **Description**: Retrieves the total score of the realm.
   - **Return Type**: `int`
     - the total score of the realm.

10. `int getNoElementalCrests()`
    - **Description**: Retrieves the number of elemental crests in the realm.
    - **Return Type**: `int`
      - the number of elemental crests in the realm.

11. `String toString()`
    - **Description**: Retrieves a string representation of the realm.
    - **Return Type**: `String`
      - a string representation of the realm.

12. `Move[] getRealmMoves()`
    - **Description**: Retrieves the available moves in the realm.
    - **Return Type**: `Move[]`
      - an array containing the available moves in the realm.

13. `Creature getCreature(Dice dice)`
    - **Description**: Retrieves the creature associated with the given dice in the realm.
    - **Parameters**: 
      - `dice`: the dice used to find the associated creature.
    - **Return Type**: `Creature`
      - the creature associated with the given dice


### `MagentaRealm` class

- **Package**: `game.realms`
- **Type**: SubClass
- **Description**: This class manages the Magenta Realm.

#### Methods:

1. `void updatePossibleMoves(Move move)`
   - **Description**: Updates the possible moves in the realm after an attack.
   - **Parameters**: 
     - `move`: the move used for the attack.
   - **Return Type**: `void`

2. `Collectibles[] getRewardsProperties()`
   - **Description**: Retrieves the reward properties of the realm from a file.
   - **Return Type**: `Collectibles[]`
     - an array of reward properties of the realm.

3. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

4. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

5. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

6. `boolean isRealmAvailable()`
   - **Description**: Checks if the realm is available.
   - **Return Type**: `boolean`
     - true if the realm is available, false otherwise.

7. `Collectibles[] getReward()`
   - **Description**: Retrieves the rewards earned in the realm.
   - **Return Type**: `Collectibles[]`
     - an array of rewards earned in the realm.

8. `boolean checkReward()`
   - **Description**: Checks if there are rewards available in the realm.
   - **Return Type**: `boolean`
     - true if there are rewards available, false otherwise.

9. `boolean attack(Move move)`
   - **Description**: Performs an attack in the realm using a move.
   - **Parameters**: 
     - `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

10. `int getTotalScore()`
    - **Description**: Retrieves the total score of the realm.
    - **Return Type**: `int`
      - the total score of the realm.

11. `int getNoElementalCrests()`
    - **Description**: Retrieves the number of elemental crests in the realm.
    - **Return Type**: `int`
      - the number of elemental crests in the realm.

12. `String toString()`
    - **Description**: Retrieves a string representation of the realm.
    - **Return Type**: `String`
      - a string representation of the realm.

13. `Move[] getRealmMoves()`
    - **Description**: Retrieves the moves available in the realm.
    - **Return Type**: `Move[]`
      - an array of moves available in the realm.

14. `Creature getCreature(Dice dice)`
    - **Description**: Retrieves the creature associated with the given dice in the realm.
    - **Parameters**: 
      - `dice`: the dice used to find the associated creature.
    - **Return Type**: `Creature`
      - the creature associated with the given dice.



### `SystemManager` class

- **Package**: `game.system`
- **Type**: class
- **Description**: This class opens the properties files.

#### Methods:

1. `public void checkSystem()`
   - **Description**: Checks the system for necessary game files and handles exceptions if files are missing.
   - **Throws**:
     - `MissingGameFilesException`: If any required game file is missing.
   
2. `public void checkGameFiles() throws MissingGameFilesException`
   - **Description**: Checks the existence of required game files.
   - **Throws**:
     - `MissingGameFilesException`: If any required game file is missing.

3. `public void performSystemChecks()`
   - **Description**: Performs system checks by calling `checkSystem()` method and handles exceptions if any.
   
4. `public void exit()`
   - **Description**: Exits the game gracefully.
   
5. `public void exit(String error)`
   - **Description**: Exits the game with a system error message.
   - **Parameters**:
     - `error`: The error message to be displayed.


### `CollectiblesComparater` class

- **Package**: `game.utilities`
- **Type**: enum
- **Descriptiion**: This class can compare between collectibles

#### Methods:

1. `public int compare(Collectibles o1, Collectibles o2)`
   - **Description**: Compares two collectible objects.
   - **Parameters**:
     - `o1`: The first collectible object to compare.
     - `o2`: The second collectible object to compare.
   - **Return Type**: `int`
     - A negative integer, zero, or a positive integer as the first collectible object is less than, equal to, or greater than the second collectible object.

### `ColorComparator` class 

- **Package**: `game.utilities`
- **Type**: class
- **Description**: This class can compare between color.

#### Methods:

`int compare(Color o1, Color o2)`
   - **Description**: Compares two colors based on their priority.
   - **Parameters**:
     - `o1`: The first color object to compare.
     - `o2`: The second color object to compare.
   - **Return Type**: `int`
     - A negative integer, zero, or a positive integer as the first color has a lower priority, equal priority, or higher priority than the second color.

`int getColorPriority(Color color)`
   - **Description**: Retrieves the priority of a color.
   - **Parameters**:
     - `color`: The color to get the priority for.
   - **Return Type**: `int`
     - The priority value of the color.
  
### `Color` class

- **Package**: `game.utilities`
- **Type**: enum
- **Description**: This class has all different realm colors.

### `Main` class

- **Package**: `game`
- **Type**: class
- **Description**: This class includes the main method of that starts the game.









