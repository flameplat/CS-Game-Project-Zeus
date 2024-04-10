# Project Skeleton

## Folder Structure

```
dice-realms-game-zeus
├── src
│   └── main
│       └── java
│           └── game
│               ├── Realms
│               │   ├── BlueRealm.java
│               │   ├── GreenRealm.java
│               │   ├── MagentaRealm.java
│               │   ├── Realm.java
│               │   ├── RedRealm.java
│               │   └── YellowRealm.java
│               ├── collectibles
│               │   ├── ArcaneBoost.java
│               │   ├── Collectibles.java
│               │   ├── CollectiblesStatus.java
│               │   ├── ColorBonus.java
│               │   ├── ElementalCrest.java
│               │   ├── EssenceShiftBonus.java
│               │   ├── TimeWarp.java
│               │   └── dice-realms-game-zeus.code-workspace
│               ├── creatures
│               │   ├── Creature.java
│               │   ├── Dragon.java
│               │   ├── Guardians.java
│               │   ├── HitRegionsOfDragons.java
│               │   ├── Lion.java
│               │   ├── Phoenix.java
│               │   └── Serpent.java
│               ├── dice
│               │   └── Dice.java
│               ├── engine
│               │   ├── CLIGameController.java
│               │   ├── CurrentStatus.java
│               │   ├── ForgottenRealm.java
│               │   ├── GameBoard.java
│               │   ├── GameController.java
│               │   ├── GameScore.java
│               │   ├── GameStatus.java
│               │   ├── Move.java
│               │   ├── Player.java
│               │   └── hScoreSheet.java
│               ├── Color.java
│               ├── Config.java
│               └── Main.java
├── ProjectSkeleton.md
├── README.md
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


#### Methods:

1. `void startGame()`
   - **Description**: ?
   - **Parameters**:
     - `?`: ?
   - **Return Type**: `?`
     - `?` ?

### `ArcaneBoost` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the arcane boost.

#### Methods:

1. `CollectiblesStatus getStatus()`
   - **Description**: it is a getter method for status attribute in the class.
   - **Return Type**: `CollectiblesStatus`
     - the current status of the collectible (disabled - enabled - used).

2. `void setStatus(CollectiblesStatus status)`
  - **Description**: it is a setter method for status attribute in the class.
  - **Parameters**: `status`: the status needed to be changed into.

3. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.




### `Collectibles` class

- **Package**: `game.collectibles`
- **Type**: Abstract Class
- **Description**: This class includes the abstract methods commonly used in all the subclasses.

#### Methods:

1. `CollectiblesStatus getStatus()`
  - **Description**: it is a getter method for status attribute in the class.
   - **Return Type**: `CollectiblesStatus`
     - the current status of the collectible (disabled - enabled - used).

2. `void setStatus(CollectiblesStatus status)`
  - **Description**: it is a setter method for status attribute in the class.
  - **Parameters**: `status`: the status needed to be changed into.

3. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.

4. `boolean isBonus()` 
  - **Description**: method to determine if the collectible is a bonus or not to determine if it will be available for the later rounds or only this round.
  - **Return Type**: `boolean`
    - `true` if the collectible is a bonus and valid for one round only.
    - `false` if the collectible is a bonus and valid for one round only.



### `CollectiblesStatus` class

- **Package**: `game.collectibles`
- **Type**: Enum
- **Description**: This class have options for the different statuses for the collectible.

### `ColorBonus` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the color bonus.

#### Methods:

1. `CollectiblesStatus getStatus()`
  - **Description**: it is a getter method for status attribute in the class.
   - **Return Type**: `CollectiblesStatus`
     - the current status of the collectible (disabled - enabled - used).

2. `void setStatus(CollectiblesStatus status)`
  - **Description**: it is a setter method for status attribute in the class.
  - **Parameters**: `status`: the status needed to be changed into.

3. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.

4. `boolean isBonus()` 
  - **Description**: method to determine if the collectible is a bonus or not to determine if it will be available for the later rounds or only this round.
  - **Return Type**: `boolean`
    - `true` if the collectible is a bonus and valid for one round only.
    - `false` if the collectible is a bonus and valid for one round only.

### `ElementalCrest` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the Elemental crest.

#### Method:

1.`String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.



### `TimeWarp` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the Time warp power.

#### Method:

1. `CollectiblesStatus getStatus()`
  - **Description**: it is a getter method for status attribute in the class.
   - **Return Type**: `CollectiblesStatus`
     - the current status of the collectible (disabled - enabled - used).

2. `void setStatus(CollectiblesStatus status)`
  - **Description**: it is a setter method for status attribute in the class.
  - **Parameters**: `status`: the status needed to be changed into.

3. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.

### `EssenceShiftBonus` class

- **Package**: `game.collectibles`
- **Type**: SubClass
- **Description**: This class represents the Essence shift Bonus.

#### Methods:

1. `CollectiblesStatus getStatus()`
  - **Description**: it is a getter method for status attribute in the class.
   - **Return Type**: `CollectiblesStatus`
     - the current status of the collectible (disabled - enabled - used).

2. `void setStatus(CollectiblesStatus status)`
  - **Description**: it is a setter method for status attribute in the class.
  - **Parameters**: `status`: the status needed to be changed into.

3. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.

4. `boolean isBonus()` 
  - **Description**: method to determine if the collectible is a bonus or not to determine if it will be available for the later rounds or only this round.
  - **Return Type**: `boolean`
    - `true` if the collectible is a bonus and valid for one round only.


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

4. `Collectibles getReward(int value)`
  - **Description**: gets reward after attacking.
  - **Parameters**: value of dice to attack.
  - **Return Type**: `Collectibles`
    - bonus or power depending on the attacked creature.

5. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.


### `Dragon` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the Dragon creature.

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

4. `Collectibles getReward(int value)`
  - **Description**: gets reward after attacking.
  - **Parameters**: value of dice to attack.
  - **Return Type**: `Collectibles`
    - bonus or power depending on the attacked creature.

5. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.

6. `int getId()` 
  - **Description**: method that is used as a getter for the id of the dragon.
  - **Return Type**: `int`
    - return an integer representing the dragon id.


### `Gaurdians` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the gaia guardians.

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


### `Lion` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the Lion.

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

### `Phoenix` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the Phoenix.

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



### `HitRegionsOfDragons` class

- **Package**: `game.creatures`
- **Type**: enum
- **Description**: This class represents the different hit regions of a dragon.

### `Serpent` class

- **Package**: `game.creatures`
- **Type**: SubClass
- **Description**: This class represents the Serpent.

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

### `Dice` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: This class creates and manipulates the dice object.


#### Methods:

1. `int getDiceValue()`
  - **Description**: method that is a getter of the value attribute.
  - **Return Type**: `int`
    integer of the dice value.

2. `Color getDiceColor()` 
  - **Description**: gets color of the dice to determine which realm to be attacked.
  - **Return Type**: `Color` return color value of the dice.

3. `String toString()`
  - **Description**: method that is used for printing a string of the properties of the collectible.
  - **Return Type**: `String`
    - return a string description of the collectible.

### `CLIGameController` class

- **Package**: `game.engine`
- **Type**: SubClass
- **Description**: This class represents the command line game controller and inherits most of its methods from the GameController abstract class.

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

### `CurrentStatus` class

- **Package**: `game.engine`
- **Type**: enum
- **Description**: This class represents
the different options for the status of the game.

### `ForgottenRealm` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class initiallizes and controls the forgotten Realm.

#### Methods:

  1. `void addDice(Dice dice)`
    - **Description**: This method add all lower value die to forgotten realm for the passive wizard to choose from.
    - **Parameters**: the dice to be thrown into the forgotten realm.

  2. `void removeDice(Dice dice)`
    - **Description**: this method is used when passive wizard chooses a dice to play from the pool of die at forgotten realm.
    - **Parameters**: the dice to be selected.
  
  3. `Dice[] getForgottenDices`
    - **Description**: this method for an array of currently available die in te forgotten realm.
    - **Return Type**: `Dice[]` returns an array of dices.

### `GameBoard` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents
the game board where all the realms appear and the dice playing area

#### Methods:

1. `void displayAllRealms()`
 - **Description:** This method displays all available realms.

2. `void displayAllPossibleMoves(Move[] moves)` 
 - **Description:** This method displays all possible moves based on the provided array of moves.
 - **Parameters:**
- `moves`: An array of `Move` objects representing possible moves.

3.  `void displayMainDiceDeck()`
 - **Description:** This method displays the main dice deck.

4. `void setActivePlayer(Player player)`
 - **Description:** This method sets the active player for the game.
 - **Parameters:**
  - `player`: The active `Player` to be set.

5. `void setPassivePlayer(Player player)` 
 - **Description:** This method sets the passive player for the game.
 - **Parameters:**
    - `player`: The passive `Player` to be set.

### `GameScore` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class includes total score of the game including the realm scores

#### Methods:

1. `private void updateTotalScore()`
    - **Description:** Updates the total score

2. `public void updateGameStatus()`
    - **Description:** Resets attributes to recalculate them, including the total score and the total number of elemental crests.

3. `public void displayGameScore()`
    - **Description:** Displays the game score.

4. `@Override
   public String toString()`
    - **Description:** Returns a string representation of the object.
    - **Return Type:** String

### `GameStatus` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class gets the current game status and checks if there is a change to be made and act accordingly.

#### Methods:

1. `public CurrentStatus getGameStatus()`
    - **Description:** Retrieves the game status.
    - **Parameters:** None
    - **Return Type:** `CurrentStatus`

2. `public int getRound()`
    - **Description:** Retrieves the current round number.
    - **Parameters:** None
    - **Return Type:** `int`

3. `public int getTurn()`
    - **Description:** Retrieves the current turn number.
    - **Parameters:** None
    - **Return Type:** `int`

4. `public void incrementRound()`
    - **Description:** Increments the current round number.
    - **Parameters:** None
    - **Return Type:** void

5. `public void resetTurn()`
    - **Description:** Resets the current turn number to 0.
    - **Parameters:** None
    - **Return Type:** void

6. `public void incrementTurn()`
    - **Description:** Increments the current turn number.
    - **Parameters:** None
    - **Return Type:** void

7. `public Player getCurrentActivePlayer()`
    - **Description:** Retrieves the current active player.
    - **Parameters:** None
    - **Return Type:** `Player`

8. `public void setGameStatus(CurrentStatus status)`
    - **Description:** Sets the game status.
    - **Parameters:** 
        - `status`: The status to set.
    - **Return Type:** void

### `Move` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class controls how a move is processed in the game.

#### Methods:

1. `public Realm getRealm()`
    - **Description:** Retrieves the realm.
    - **Parameters:** None
    - **Return Type:** `Realm`

2. `public Creature getCreature()`
    - **Description:** Retrieves the creature.
    - **Parameters:** None
    - **Return Type:** `Creature`

3. `public HitRegionsOfDragons getHitRegion()`
    - **Description:** Retrieves the hit region of dragons.
    - **Parameters:** None
    - **Return Type:** `HitRegionsOfDragons`

4. `public String toString()`
    - **Description:** Returns a string representation of the object.
    - **Parameters:** None
    - **Return Type:** String

### `Player` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents
all the functions of the player and construct a player object.

#### Methods:

1. `private void initializePowers()`
    - **Description:** Initializes powers.

2. `private void initializeRealms()`
    - **Description:** Initializes all realms at the start of player initialization.

3. `boolean receivePower(Collectibles power)`
    - **Description:** Receives the power and sets its status to ENABLED.
    - **Parameters:** 
        - `power`: The power to be received.
    - **Return Type:** boolean
        - `true` if the power was successfully received, `false` otherwise

4. `ScoreSheet getScoreSheet()`
    - **Description:** Retrieves the score sheet.
    - **Return Type:** `ScoreSheet`

5. `public boolean isTimeWarpAvailable()`
    - **Description:** Checks if the player's Time Warp powers array is available.
    - **Return Type:** boolean
        - `true` if available, `false` otherwise

6. `public boolean isArcaneBoostAvailable()`
    - **Description:** Checks if the player's Arcane Boost powers array is available.
    - **Return Type:** boolean
        - `true` if available, `false` otherwise

7. `public boolean useTimeWarpPower()`
    - **Description:** Uses the Time Warp power and sets its status to USED.
    - **Return Type:** boolean
        - `true` if the power was successfully used, `false` otherwise

8. `public boolean useArcaneBoostPower()`
    - **Description:** Uses the Arcane Boost power and sets its status to USED.
    - **Return Type:** boolean
        - `true` if the power was successfully used, `false` otherwise

9. `public String getName()`
    - **Description:** Retrieves the player's name.
    - **Return Type:** String

10. `public int getTotalTimeWarpPowersCollected()`
    - **Description:** Returns the total Time Warps collected and unused.
    - **Return Type:** int

11. `public int getTotalArcaneBoostPowersCollected()`
    - **Description:** Returns the total Arcane Boosts collected and unused.
    - **Return Type:** int

12. `public ArcaneBoost[] getArcaneBoosts()`
    - **Description:** Retrieves the Arcane Boosts array.
    - **Return Type:** `ArcaneBoost[]`

13. `public TimeWarp[] getTimeWarps()`
    - **Description:** Retrieves the Time Warps array.
    - **Return Type:** `TimeWarp[]`

14. `public Realm getRealm(Color color)`
    - **Description:** Retrieves the realm based on the specified color.
    - **Parameters:** 
        - `color`: The color of the realm.
    - **Return Type:** `Realm`

15. `public Realm[] getRealms()`
    - **Description:** Retrieves all realms.
    - **Return Type:** `Realm[]`

16. `public String toString()`
    - **Description:** Returns a string representation of the object.
    - **Return Type:** String

### `ScoreSheet` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class manages the ScoreSheet.

#### Methods:

1. `boolean updateScoresheet()`
    - **Description:** Updates the score sheet.
    - **Return Type:** boolean
        - `true` if the score sheet was successfully updated, `false` otherwise

2. `void displayScoreSheet()`
    - **Description:** Displays the score sheet.
    - **Return Type:** void

3. `public String toString()`
    - **Description:** Returns a string representation of the object.
    - **Return Type:** String

### `Realm` class

- **Package**: `game.Realms`
- **Type**: Abstract Class
- **Description**: This class manages all the realms by including every common used in the main realms.

#### Abstract Methods:

1. `String getName()`
   - **Description**: Method to return the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

2. `Color getColor()`
   - **Description**: Method to return the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

3. `int getStatus()`
   - **Description**: Method to return the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

4. `Collectibles getReward()`
   - **Description**: Method to return the rewards of the realm.
   - **Return Type**: `Collectibles`
     - the rewards of the realm.

5. `boolean checkReward()`
   - **Description**: Method to check whether there is a reward or not after attacking.
   - **Return Type**: `boolean`
     - true if there is a reward, false otherwise.

6. `boolean attack(Move move)`
   - **Description**: Method to perform an attack using a move.
   - **Parameters**: `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

7. `int getTotalScore()`
   - **Description**: Method to return the total score of the realm.
   - **Return Type**: `int`
     - the total score of the realm.

8. `int getNoElementalCrests()`
   - **Description**: Method to return the number of elemental crests in the realm.
   - **Return Type**: `int`
     - the number of elemental crests in the realm.

9. `String toString()`
   - **Description**: Method to provide a string representation of the realm.
   - **Return Type**: `String`
     - a string description of the realm.

10. `Creature[] getAliveCreatures()`
    - **Description**: Method to retrieve an array of alive creatures in the realm.
    - **Return Type**: `Creature[]`
      - an array of alive creatures in the realm.


### `GreenRealm` class

- **Package**: `game.Realms`
- **Type**: SubClass
- **Description**: This class manages the green realm.

#### Methods:

1. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

2. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

3. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

4. `Collectibles getReward()`
   - **Description**: Retrieves the reward of the realm.
   - **Return Type**: `Collectibles`
     - the reward of the realm.

5. `boolean checkReward()`
   - **Description**: Checks if the reward is available in the realm.
   - **Return Type**: `boolean`
     - true if the reward is available, false otherwise.

6. `boolean attack(Move move)`
   - **Description**: Performs an attack using a move.
   - **Parameters**: `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

7. `int getTotalScore()`
   - **Description**: Retrieves the total score of the realm.
   - **Return Type**: `int`
     - the total score of the realm.

8. `int getNoElementalCrests()`
   - **Description**: Retrieves the number of elemental crests in the realm.
   - **Return Type**: `int`
     - the number of elemental crests in the realm.

9. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the realm.
   - **Return Type**: `String`
     - a string description of the realm.

10. `Creature[] getAliveCreatures()`
    - **Description**: Retrieves an array of alive creatures in the realm.
    - **Return Type**: `Creature[]`
      - an array of alive creatures in the realm.

### `BlueRealm` class

- **Package**: `game.Realms`
- **Type**:  SubClass
- **Description**: This class manages the blue realm.

#### Methods:

1. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

2. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

3. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

4. `Collectibles getReward()`
   - **Description**: Retrieves the reward of the realm.
   - **Return Type**: `Collectibles`
     - the reward of the realm.

5. `boolean checkReward()`
   - **Description**: Checks if the reward is available in the realm.
   - **Return Type**: `boolean`
     - true if the reward is available, false otherwise.

6. `boolean attack(Move move)`
   - **Description**: Performs an attack using a move.
   - **Parameters**: `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

7. `int getTotalScore()`
   - **Description**: Retrieves the total score of the realm.
   - **Return Type**: `int`
     - the total score of the realm.

8. `int getNoElementalCrests()`
   - **Description**: Retrieves the number of elemental crests in the realm.
   - **Return Type**: `int`
     - the number of elemental crests in the realm.

9. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the realm.
   - **Return Type**: `String`
     - a string description of the realm.


### `RedRealm` class

- **Package**: `game.Realms`
- **Type**: SubClass
- **Description**: This class manages the  realm

#### Methods:

1. `void updateDragonsStatus()`
   - **Description**: Updates the status of dragons.
   
2. `private void initDragons()`
   - **Description**: Initializes dragons with specified attributes.
   
3. `int getScore(int dragonNumber)`
   - **Description**: Retrieves the score of the specified dragon.
   - **Parameters**: `dragonNumber`: the index of the dragon.
   - **Return Type**: `int`
     - the score of the dragon.
   
4. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

5. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

6. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

7. `Collectibles getReward()`
   - **Description**: Retrieves the reward of the realm.
   - **Return Type**: `Collectibles`
     - the reward of the realm.

8. `boolean checkReward()`
   - **Description**: Checks if there is a reward available in the realm.
   - **Return Type**: `boolean`
     - true if there is a reward available, false otherwise.

9. `boolean attack(Move move)`
   - **Description**: Performs an attack using a move.
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
      - a string description of the realm.

13. `Creature[] getAliveCreatures()`
    - **Description**: Retrieves an array of alive creatures in the realm.
    - **Return Type**: `Creature[]`
      - an array of alive creatures in the realm.
.
### `YellowRealm` class

- **Package**: `game.Realms`
- **Type**: SubClass
- **Description**: This class manages the yellow realm.

#### Methods:

1. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

2. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

3. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

4. `Collectibles getReward()`
   - **Description**: Retrieves the reward of the realm.
   - **Return Type**: `Collectibles`
     - the reward of the realm.

5. `boolean checkReward()`
   - **Description**: Checks if there is a reward available in the realm.
   - **Return Type**: `boolean`
     - true if there is a reward available, false otherwise.

6. `boolean attack(Move move)`
   - **Description**: Performs an attack using a move.
   - **Parameters**: `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

7. `int getTotalScore()`
   - **Description**: Retrieves the total score of the realm.
   - **Return Type**: `int`
     - the total score of the realm.

8. `int getNoElementalCrests()`
   - **Description**: Retrieves the number of elemental crests in the realm.
   - **Return Type**: `int`
     - the number of elemental crests in the realm.

9. `String toString()`
   - **Description**: Retrieves a string representation of the realm.
   - **Return Type**: `String`
     - a string description of the realm.

10. `Creature[] getAliveCreatures()`
    - **Description**: Retrieves an array of alive creatures in the realm.
    - **Return Type**: `Creature[]`
      - an array of alive creatures in the realm.


### `MagentaRealm` class

- **Package**: `game.Realms`
- **Type**: SubClass
- **Description**: This class manages the Magenta Realm.

#### Methods:

1. `String getName()`
   - **Description**: Retrieves the name of the realm.
   - **Return Type**: `String`
     - the name of the realm.

2. `Color getColor()`
   - **Description**: Retrieves the color of the realm.
   - **Return Type**: `Color`
     - the color of the realm.

3. `int getStatus()`
   - **Description**: Retrieves the status of the realm.
   - **Return Type**: `int`
     - the status of the realm.

4. `Collectibles getReward()`
   - **Description**: Retrieves the reward of the realm.
   - **Return Type**: `Collectibles`
     - the reward of the realm.

5. `boolean checkReward()`
   - **Description**: Checks if the reward is available in the realm.
   - **Return Type**: `boolean`
     - true if the reward is available, false otherwise.

6. `boolean attack(Move move)`
   - **Description**: Performs an attack using a move.
   - **Parameters**: `move`: the move used for the attack.
   - **Return Type**: `boolean`
     - true if the attack was successful, false otherwise.

7. `int getTotalScore()`
   - **Description**: Retrieves the total score of the realm.
   - **Return Type**: `int`
     - the total score of the realm.

8. `int getNoElementalCrests()`
   - **Description**: Retrieves the number of elemental crests in the realm.
   - **Return Type**: `int`
     - the number of elemental crests in the realm.

9. `String toString()`
   - **Description**: Method that is used for printing a string of the properties of the realm.
   - **Return Type**: `String`
     - a string description of the realm.

10. `Creature[] getAliveCreatures()`
    - **Description**: Retrieves an array of alive creatures in the realm.
    - **Return Type**: `Creature[]`
      - an array of alive creatures in the realm.


### `Color` class

- **Package**: `game`
- **Type**: enum
- **Description**: This class has all different realm colors.

### `Config` class

- **Package**: `game`
- **Type**: class
- **Description**: This class has all settings in the game like max number of rounds an all similar.

### `Main` class

- **Package**: `game`
- **Type**: class
- **Description**: This class includes the main method of that starts the game.









