## Dice Realms: Quest for the Elemental Crests

  **Type:** Turn-based Strategy Game | **Stack:** Java 17, JavaFX, OOP
  **Course:** CS401
  **Team Size:** 11 competing teams, 6 members each | **Role:** Green Realm
  Developer & AI Architect

  ### Overview

  A two-player turn-based strategy game where players roll dice and compete
  across five elemental realms to collect creatures, earn rewards, and
  accumulate the highest score. Loosely inspired by [Ganz Schön 
  Clever](https://www.schmidtspiele.de/static/onlinespiele/ganz-schoen-clever/).
   Shipped with both a terminal (CLI) and a graphical (JavaFX GUI) interface,
  supporting Human vs Human and Human vs AI game modes.

  ### My Contributions

  #### Green Realm — *Terra's Heartland: Gaia Guardians*

  Designed and implemented the Green Realm end-to-end — one of five elemental
  realms in the game:

  - Created a **3×4 grid of 12 creatures** (called Guardians) — players attack a
   Guardian by rolling a die that matches its value
  - Added logic to **detect when a full row or column is cleared**, then hand
  out bonus rewards to the player
  - Built the **scoring system** using external config files, so game balance
  can be adjusted without touching the code
  - Added a `getFakeScore()` method that lets the AI calculate how many points a
   move would give before actually making it
  
  #### AI Player — *Best AI in Class (1st out of 11 teams)*

  Designed and built the `AIPlayer` and `MoveEvaluation` engine from scratch:

  - The AI looks at every possible move and gives each one a **score based on 
  how good it is** — considering points gained, chances of completing a row or
  column for a bonus reward, and how the board looks after the move
  - Each realm has its own **custom logic** inside the AI — for example, in the
  Green Realm, the AI considers how close a row or column is to being completed
  before picking a move
  - Designed a **Monte Carlo world-limiting system** (capped at 500 evaluations
  per turn) so the AI makes decisions quickly without freezing the game
  - The AI also decides **when to use special powers**: it uses the re-roll
  power when good moves are available, and uses the swap power when all current
  options are weak
  - Works cleanly with both the terminal and graphical versions of the game
  
  ### Technical Highlights

  - Applied common software design patterns: **Abstract Factory**, **Template
  Method**, and **Strategy**
  - Used **LinkedLists** for move tracking and **2D arrays** for realm grids
  - All game balance values (scores, rewards) are stored in **config files** —
  easy to adjust without editing code
  - Collaborated on an **AntiCheat layer** that validates move legality and
  score integrity at every turn
