package game.engine;

import game.collectibles.*;
import game.creatures.Dragon;
import game.creatures.Guardian;
import game.dice.*;
import game.exceptions.*;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;
import game.system.SystemManager;
import game.utilities.CollectiblesComparator;
import game.utilities.GameColor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLIGameController extends GameController {

    // -----------------------Attributes-----------------------//
    protected static final String GAME_PROPERTIES_PATH = "src/main/resources/config/Game.properties";
    protected static final String ROUNDS_REWARDS_PATH = "src/main/resources/config/RoundsRewards.properties";
    protected static int MAX_NUMBER_OF_TURNS;
    protected static Collectibles[] roundRewards;
    protected static int MAX_NUMBER_OF_ROUNDS;
    protected GameMode gameMode;

    static {
        Properties gameProperties = new Properties();
        Properties roundRewardProperties = new Properties();
        try (FileInputStream gameFileInputStream = new FileInputStream(GAME_PROPERTIES_PATH);
             FileInputStream roundRewardFileInputStream = new FileInputStream(ROUNDS_REWARDS_PATH)) {
            gameProperties.load(gameFileInputStream);
            roundRewardProperties.load(roundRewardFileInputStream);
            MAX_NUMBER_OF_ROUNDS = Integer.parseInt(gameProperties.getProperty("numberOfRounds", "6"));
            MAX_NUMBER_OF_TURNS = Integer.parseInt(gameProperties.getProperty("numberOfTurns", "3"));
            roundRewards = new Collectibles[MAX_NUMBER_OF_ROUNDS];
            for (int i = 0; i < MAX_NUMBER_OF_ROUNDS; i++) {
                String reward = roundRewardProperties.getProperty("round" + (i + 1) + "Reward");
                if (reward != null) {
                    roundRewards[i] = Collectibles.getCollectibleFromString(reward);
                }

            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading game properties: " + e.getMessage());
            // Default values
            MAX_NUMBER_OF_ROUNDS = 6;
            MAX_NUMBER_OF_TURNS = 3;
        }
    }


    protected final Dice[] diceArray;
    protected final GameBoard gameBoard;
    protected final SystemManager systemManager;
    protected final Scanner sc; //Will be closed at the end of the game
    protected final GameStatus gameStatus;
    protected final GameGuide gameGuide;
    protected final StandardAntiCheatService standardAntiCheatService = new StandardAntiCheatService();
    protected Player activePlayer;
    protected Player passivePlayer;

    // -----------------------Constructor-----------------------//
    public CLIGameController() {
        systemManager = new SystemManager();
        systemManager.performSystemChecks();
        gameGuide = new GameGuide();
        gameBoard = new GameBoard();
        gameStatus = new GameStatus();
        gameStatus.setGameStatus(CurrentStatus.ACTIVE_TURN);
        diceArray = gameBoard.getDice();
        sc = new Scanner(System.in);
        activePlayer = gameBoard.getPlayer1();
        passivePlayer = gameBoard.getPlayer2();
    }
    // -----------------------Methods-----------------------//
    @Override
    public void startGame() {
        standardAntiCheatService.initMasterPlayer();
        mainMenu();
        Player player1 = getPlayerName("Enter Player 1 name: ");
        gameBoard.setPlayer1(player1);
        Player player2 = getPlayerName("Enter Player 2 name: ");
        gameBoard.setPlayer2(player2);
        player1.setPlayerStatus(PlayerStatus.ACTIVE);
        player2.setPlayerStatus(PlayerStatus.PASSIVE);
        activePlayer = player1;
        passivePlayer = player2;
        performAntiCheatServiceChecks(activePlayer);
        performAntiCheatServiceChecks(passivePlayer);
        for (int i = 0; i < MAX_NUMBER_OF_ROUNDS; i++) {
            gameStatus.resetTurn();
            System.out.println("Round " + (i + 1));
            //Active player receives round reward
            if (roundRewards[i] != null) {
                performReward(activePlayer, roundRewards[i]);
            }
            playRound();
            switchPlayer();
            System.out.println("Round " + (i + 1));
            //Active player receives round reward
            if (roundRewards[i] != null) {
                performReward(activePlayer, roundRewards[i]);
            }
            playRound();
            switchPlayer();
            gameStatus.incrementRound();
        }
        endGame();
    }
    private void mainMenu() {
        gameGuide.displayGameMode();
        while(true){
            int choice = gameGuide.getUserChoice(1, 2);
            if (choice == 2) {
                System.out.println("This option is WIP");
            }
            else{
                gameMode=GameMode.SINGLEPLAYER;
                break;
            }
        }
        gameGuide.displayInstructions(Instruction.GAME);
    }

    protected void checkArcaneBoost(Player player) {
        while (player.isArcaneBoostAvailable()) {
            displayArcaneBoostStatus(player);
            boolean choice = gameGuide.getUserBooleanChoice();
            if (choice) {
                try {
                    if (getPossibleMovesForDice(player, diceArray).length == 0) {
                        throw new NoAvailableMovesException();
                    }
                    player.useArcaneBoostPower();
                    playExtraTurn(player);
                } catch (NoAvailableMovesException e) {
                    System.out.println("No available moves for current dice");
                    System.out.println("You can't use Arcane Boost");
                    break;
                }
            } else {
                break;
            }
        }
    }

    protected void displayArcaneBoostStatus(Player player) {
        System.out.println(player.getName());
        gameGuide.displayInstructions(Instruction.AB_PROMPT);
        int count = player.getTotalArcaneBoostPowersCollected();
        System.out.printf("You have %d Arcane Boost%s%n", count, count > 1 ? "s" : "");
    }

    protected void playExtraTurn(Player player) {
        player.getScoreSheet().displayScoreSheet();
        LinkedList<Dice> notSelectedByPlayer = new LinkedList<>();
        DiceStatus filter = player.getPlayerStatus() == PlayerStatus.ACTIVE ? DiceStatus.ACTIVE_PLAYER_SELECTED : DiceStatus.PASSIVE_PLAYER_SELECTED;
        for (Dice i : diceArray) {
            if (i.getDiceStatus() != filter) {
                notSelectedByPlayer.add(i);
            }
        }
        Dice[] diceNotSelectedByPlayer = notSelectedByPlayer.toArray(Dice[]::new);
        try {
            Dice selectedDie = selectValidDie(player, diceNotSelectedByPlayer, false, filter);
            Move validMove = selectValidMove(player, selectedDie);
            makeMove(player, validMove);
        } catch (NoAvailableMovesException e) {
            System.out.println("Ohh bad luck...there are no possible moves, turn lost!");
        }
    }

    protected boolean checkTimeWarp(Player player) {
        if (player.isTimeWarpAvailable()) {
            displayTimeWarpStatus(player);
            boolean choice = gameGuide.getUserBooleanChoice();
            if (choice) {
                player.useTimeWarpPower();
                rollDice();
                return true;
            }
        }
        return false;
    }

    protected void displayTimeWarpStatus(Player player) {
        int count = player.getTotalTimeWarpPowersCollected();
        gameGuide.displayInstructions(Instruction.TW_PROMPT);
        System.out.printf("You have %d Time Warp%s%n", count, count > 1 ? "s" : "");
    }

    protected void playEssenceBonus(Player player) {
        gameGuide.displayInstructions(Instruction.ESSENCE_BONUS);
        player.getScoreSheet().displayScoreSheet();
        Realm[] realms = player.getRealms();
        LinkedList<Realm> availableRealms = Stream.of(realms)
                .filter(Realm::isRealmAvailable)
                .collect(Collectors.toCollection(LinkedList::new));
        if (availableRealms.size() != realms.length) {
            while (true) {
                displayRealms(player);
                System.out.println("Possible realms to choose from:");
                for (int i = 0; i < availableRealms.size(); i++) {
                    System.out.print(availableRealms.get(i).getName());
                    if (i != availableRealms.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
                int choice = gameGuide.getUserChoice(1, realms.length);
                try {
                    if (!availableRealms.contains(realms[choice - 1])) {
                        throw new InvalidMoveException();
                    }
                    playColorBonus(player, realms[choice - 1].getColor());
                    break;
                } catch (InvalidMoveException e) {
                    System.out.printf("%s is not available%n", realms[choice - 1].getName());
                    System.out.println("Choose another realm");
                }
            }
        } else {
            displayRealms(player);
            playColorBonus(player, realms[gameGuide.getUserChoice(1, realms.length) - 1].getColor());
        }


    }

    protected void playColorBonus(Player player, GameColor gameColor) {
        gameGuide.displayInstructions(Instruction.COLOR_BONUS);
        switch (gameColor) {
            case RED: {
                Dice[] redDice = new Dice[]{
                        new RedDice(1),
                        new RedDice(2),
                        new RedDice(3),
                        new RedDice(4),
                        new RedDice(5),
                        new RedDice(6)};
                try {
                    Dice selectedDie = selectValidDie(player, redDice, false, DiceStatus.TURN_SELECTED);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);

                } catch (NoAvailableMovesException e) {
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            case GREEN: {
                // Define green dice
                GreenRealm greenRealm = (GreenRealm) player.getRealm(GameColor.GREEN);
                LinkedList<Guardian> aliveCreatures = greenRealm.getAliveCreatures();
                Guardian[] allCreatures = ((GreenRealm) player.getRealm(GameColor.GREEN)).getAllCreatures();
                try {
                    if (aliveCreatures.isEmpty() || !greenRealm.isRealmAvailable()) {
                        throw new NoAvailableMovesException();
                    }
                    gameGuide.displayCreatures(allCreatures);
                    System.out.println("Choose a Gaia to attack:");
                    while (true) {
                        int choice = gameGuide.getUserChoice(2, 12);
                        try {
                            if (!aliveCreatures.contains(allCreatures[choice - 2])) {
                                throw new InvalidMoveException();
                            }
                            System.out.println(allCreatures[choice - 2].getName());
                            makeMove(player,new Move(new GreenDice(allCreatures[choice - 2].getScore()), allCreatures[choice - 2]));
                            break;
                        } catch (InvalidMoveException e) {
                            System.out.println(allCreatures[choice - 2].getName() + " is dead, choose another Gaia");
                        }
                    }

                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            case BLUE: {
                // Define blue dice
                Dice[] blueDice = new Dice[]{
                        new BlueDice(6)};
                try {
                    // Select a valid die and move
                    Dice selectedDie = selectValidDie(player, blueDice, false, DiceStatus.TURN_SELECTED);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            case MAGENTA: {
                // Define magenta dice
                Dice[] magentaDice = new Dice[]{
                        new MagentaDice(6)};
                try {
                    // Select a valid die and move
                    Dice selectedDie = selectValidDie(player, magentaDice, false, DiceStatus.TURN_SELECTED);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            case YELLOW: {
                // Define yellow dice
                Dice[] yellowDice = new Dice[]{
                        new YellowDice(6)};
                try {
                    // Select a valid die and move
                    Dice selectedDie = selectValidDie(player, yellowDice, false, DiceStatus.TURN_SELECTED);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            default:
                System.err.println("Invalid color bonus: " + gameColor);
        }
    }

    private void processRewardQueue(Player player, Collectibles[] rewards) {
        LinkedList<Collectibles> list = new LinkedList<>();
        for (Collectibles r : rewards) {
            if (r != null) {
                list.add(r);
            }
        }
        PriorityQueue<Collectibles> priorityQueue = new PriorityQueue<>(new CollectiblesComparator());
        priorityQueue.addAll(list);
        while (!priorityQueue.isEmpty()) {
            performReward(player, priorityQueue.remove());
        }
    }

    protected void performReward(Player player, Collectibles reward) {
        if (reward == null) {
            return;
        }
        System.out.println(player.getName() + ", you received " + reward.getName() + "!");
        if (reward instanceof EssenceBonus) {
            playEssenceBonus(player);
        } else {
            if (reward instanceof ColorBonus) {
                playColorBonus(player, ((ColorBonus) reward).getColor());
            } else {
                player.receiveCollectible(reward);
            }
        }
    }

    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {

        }
    }

    protected void playRound() {
        System.out.println(activePlayer.getName());
        gameGuide.displayInstructions(Instruction.ROUND);
        resetDice();
        for (int i = 0; (i < MAX_NUMBER_OF_TURNS) & (containsAvailableDie()); i++) {
            System.out.println("Turn " + (i + 1));
            playTurn();
        }
        moveDiceToForgottenRealm();
        playPassiveTurn();
        checkArcaneBoost(activePlayer);
        checkArcaneBoost(passivePlayer);


    }

    private boolean containsAvailableDie() {
        for (Dice i : diceArray) {
            if (i.getDiceStatus() == DiceStatus.AVAILABLE) {
                return true;
            }
        }
        return false;
    }

    //Selects a valid die (has an available move in player)
    //Prints given dice if no moves or prints filtered dice then the selected die
    protected Dice selectValidDie(Player player, Dice[] dice, boolean checkTimeWarp, DiceStatus diceStatus) throws NoAvailableMovesException {
        Dice selectedDie;
        LinkedList<Dice> filteredDice;
        if (getPossibleMovesForDice(player, dice).length == 0) {
            gameGuide.displayNumberedChoice(dice);
            throw new NoAvailableMovesException();
        } else {
            gameGuide.displayNumberedChoice(dice);
            filteredDice = filterDiceWithPossibleMoves(player, dice);
            if (filteredDice.size() != dice.length) {
                System.out.println("Possible Dice to choose from:");
                System.out.println(filteredDice);
            }
            while (checkTimeWarp && checkTimeWarp(player)) {
                filteredDice = filterDiceWithPossibleMoves(player, dice);
                if (filteredDice.isEmpty()) {
                    throw new NoAvailableMovesException();
                }
                gameGuide.displayNumberedChoice(dice);
                if (filteredDice.size() != dice.length) {
                    System.out.println("Possible Dice to choose from:");
                    System.out.println(filteredDice);
                }
            }
            while (true) {
                int choice = gameGuide.getUserChoice(1, dice.length);
                selectedDie = dice[choice - 1];
                try {
                    if (!filteredDice.contains(dice[choice - 1])) {
                        throw new InvalidMoveException();
                    }
                    break;
                } catch (InvalidMoveException e) {
                    System.out.println("There are no possible moves for " + selectedDie);
                    System.out.println("Choose another die");
                }
            }

        }
        System.out.println(selectedDie);
        if (selectedDie instanceof WhiteDice) {
            System.out.println("Choose which realm to play with Arcane Prism");
            selectedDie.setDiceStatus(diceStatus);
            //RED, GREEN, BLUE, MAGENTA, YELLOW
            Dice[] possibleDice = {
                    new RedDice(selectedDie.getValue()),
                    //If casting it to green die is not desired then change this to value of green die
                    new GreenDice(diceArray[1].getValue()),
                    new BlueDice(selectedDie.getValue()),
                    new MagentaDice(selectedDie.getValue()),
                    new YellowDice(selectedDie.getValue())};
            return selectValidDie(player, possibleDice, false, diceStatus);
        }
        selectedDie.setDiceStatus(diceStatus);
        return selectedDie;
    }

    protected LinkedList<Dice> filterDiceWithPossibleMoves(Player player, Dice[] dice) {
        LinkedList<Dice> diceWithMoves = new LinkedList<>();
        for (Dice i : dice) {
            if (getPossibleMovesForADie(player, i).length != 0) {
                diceWithMoves.add(i);
            }
        }
        return diceWithMoves;
    }

    protected Move selectValidMove(Player player, Dice selectedDie) {

        if (selectedDie instanceof RedDice) {
            return selectMoveForRedDice(player, (RedDice) selectedDie);
        }
        return getPossibleMovesForADie(player, selectedDie)[0];
    }

    private Move selectMoveForRedDice(Player player, RedDice redDice) {
        player.getScoreSheet().displayRedRealm();
        Move[] moves = getPossibleMovesForADie(player, redDice);
        System.out.println("Choose a Dragon to attack");
        Map<Integer, Move> moveDragonMap = new HashMap<>();
        for (Move move : moves) {
            moveDragonMap.put(((Dragon) move.getCreature()).getDragonNumber(), move);
        }
        while (true) {
            System.out.print("Possible dragons to attack: ");
            moveDragonMap.forEach((dragonNumber, move) -> System.out.print(dragonNumber + "  "));
            System.out.println();
            int dragonNumber = gameGuide.getUserChoice(1, 4);
            try {
                if (!moveDragonMap.containsKey(dragonNumber)) {
                    throw new InvalidMoveException();
                }
                return moveDragonMap.get(dragonNumber);
            } catch (InvalidMoveException e) {
                System.out.println("Can't attack Dragon " + dragonNumber);
                System.out.println("Choose another dragon");
            }
        }
    }

    private void playTurn() {
        gameGuide.displayInstructions(Instruction.TURN);
        System.out.println("Here is your score sheet");
        delay(2000);
        activePlayer.getScoreSheet().displayScoreSheet();
        Dice[] availableDice = getAvailableDice();
        gameGuide.displayInstructions(Instruction.ROLL);
        sc.nextLine();
        rollDice();
        Dice selectedDie;
        while (true) {
            try {
                selectedDie = selectValidDie(activePlayer, availableDice, true, DiceStatus.TURN_SELECTED);
                selectDice(selectedDie, activePlayer);
                break;
            } catch (NoAvailableMovesException e) {
                System.out.println("Ohh bad luck...No possible moves!");
                if (!checkTimeWarp(activePlayer)) {
                    System.out.println("Turn lost!");
                    return;
                }
            }
        }
        Move selectedMove = selectValidMove(activePlayer, selectedDie);
        makeMove(activePlayer, selectedMove);
        //Choosing a die, move (check if move is valid,if not choose another die)
        //execute move
        //All dice of value less than selected die's value goes to forgotten realm
        gameStatus.incrementTurn();
    }

    private void playPassiveTurn() {
        System.out.println(passivePlayer.getName());
        gameGuide.displayInstructions(Instruction.PASSIVE_TURN);
        passivePlayer.getScoreSheet().displayScoreSheet();
        Dice[] temp = getForgottenRealmDice();
        Dice selectedDie;
        try {
            selectedDie = selectValidDie(passivePlayer, temp, false, DiceStatus.TURN_SELECTED);
        } catch (NoAvailableMovesException e) {
            System.out.println("Ohh bad luck...No possible moves!");
            System.out.println("Passive turn lost!");
            return;
        }
        Move selectedMove = selectValidMove(passivePlayer, selectedDie);
        makeMove(passivePlayer, selectedMove);
    }

    protected void displayRealms(Player player) {
        Realm[] realms = player.getRealms();
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < realms.length; i++) {

            result.append(i + 1).append("-");
            result.append(realms[i].getName());
            if (i < realms.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        System.out.println(result);
    }

    private Player getPlayerName(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String playerName = sc.nextLine();
                if (gameBoard.getPlayer1() != null && playerName.equals(gameBoard.getPlayer1().getName())) {
                    throw new InvalidPlayerNameException("Name already in use!");
                }
                return new Player(playerName);
            } catch (InvalidPlayerNameException e) {
                System.out.println(e.getMessage());
                sc.nextLine();  // Clear the buffer
            }
        }
    }

    @Override
    public boolean switchPlayer() {
        boolean flag;
        try {
            if (activePlayer != passivePlayer && activePlayer.getPlayerStatus() == PlayerStatus.ACTIVE &&
                    passivePlayer.getPlayerStatus() == PlayerStatus.PASSIVE) {
                activePlayer.setPlayerStatus(PlayerStatus.PASSIVE);
                passivePlayer.setPlayerStatus(PlayerStatus.ACTIVE);
                Player temp = activePlayer;
                activePlayer = passivePlayer;
                passivePlayer = temp;
                flag = true;
            } else {
                flag = false;
            }

        } catch (NullPointerException e) {
            System.err.println("Invalid Switch: " + e.getMessage());
            flag = false;
        }
        return flag;
    }

    /**
     * Rolls all available dice for the current turn, assigning each a random
     * number from 1 to 6.
     *
     * @return An array of the currently rolled {@code Dice}.
     */
    @Override
    public Dice[] rollDice() {
        //Rolling only rolls available dice
        Random random = new Random();
        int diceValue;
        //Dice values are from 1 to 6
        int diceMaxBound = 6;
        int diceMinBound = 1;
        for (Dice dice : diceArray) {
            if (dice != null && dice.getDiceStatus() == DiceStatus.AVAILABLE) {
                diceValue = random.nextInt(diceMaxBound - diceMinBound + 1) + diceMinBound;
                dice.setValue(diceValue);
            }
        }
        return diceArray;
    }

    /**
     * Resets dice status to be all available
     * Used at the beginning of each round
     */
    private void resetDice() {
        for (Dice i : diceArray) {
            i.setDiceStatus(DiceStatus.AVAILABLE);
        }
    }

    /**
     * Gets the dice available for rolling or rerolling.
     *
     * @return An array of {@code Dice} available for the current turn.
     */
    @Override
    public Dice[] getAvailableDice() {
        LinkedList<Dice> list = new LinkedList<>();
        for (Dice i : diceArray) {
            if (i.getDiceStatus() == DiceStatus.AVAILABLE) {
                list.add(i);
            }
        }
        return list.toArray(Dice[]::new);
    }

    /**
     * Gets all six dice, providing their current state and value within the
     * game regardless of their location or status. The dice could be in various
     * states, such as currently rolled and awaiting selection by the active player,
     * in the Forgotten Realm awaiting selection by the passive player, or already
     * assigned to a specific turn by the active player.
     *
     * @return An array of all six {@code Dice}, with each die's state and value.
     */
    @Override
    public Dice[] getAllDice() {
        return diceArray;
    }

    /**
     * Gets all dice located in the forgotten realm
     *
     * @return An array of all dice in the forgotten realm
     */
    @Override
    public Dice[] getForgottenRealmDice() {
        LinkedList<Dice> list = new LinkedList<>();
        for (Dice i : diceArray) {
            if (i.getDiceStatus() == DiceStatus.FORGOTTEN_REALM) {
                list.add(i);
            }
        }
        Dice[] result = new Dice[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Override
    public Move[] getAllPossibleMoves(Player player) {
        if (player == null) {
            System.err.println("Player cannot be null.");
            return new Move[0];
        }
        LinkedList<Move> list = new LinkedList<>();
        for (Realm i : player.getRealms()) {
            Move[] moves = i.getRealmMoves();
            for (Move m : moves) {
                list.addLast(m);
            }
        }
        Move[] result = new Move[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * Gets possible moves for all currently rolled dice for a given player.
     *
     * @param player The player for whom to determine possible moves.
     * @return An array of all possible moves for all rolled dice.
     */
    @Override
    public Move[] getPossibleMovesForAvailableDice(Player player) {
        return getPossibleMovesForDice(player, getAvailableDice());
    }

    private Move[] getPossibleMovesForDice(Player player, Dice[] dice) {
        try {
            LinkedList<Move> availableMoves = new LinkedList<>();
            for (Dice i : dice) {
                Move[] moves = getPossibleMovesForADie(player, i);
                for (Move m : moves) {
                    //To not include duplicated moves if white die has same value of any other die in the given dice array
                    //And to not duplicate moves for green realm
                    if (!availableMoves.contains(m)) {
                        availableMoves.addLast(m);
                    }
                }
            }
            return availableMoves.toArray(Move[]::new);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return new Move[0];
    }

    @Override
    public Move[] getPossibleMovesForADie(Player player, Dice dice) {
        try {
            LinkedList<Move> possibleMoves = new LinkedList<>();
            int diceValue = dice.getValue();

            // If the dice is white, iterate over all realms
            if (dice.getRealm() == GameColor.WHITE) {
                for (Realm realm : player.getRealms()) {
                    Move[] realmMoves = realm.getRealmMoves();
                    for (Move move : realmMoves) {
                        int targetValue = (realm instanceof GreenRealm) ? diceValue + diceArray[1].getValue() : diceValue;
                        if (move.getDice().getValue() == targetValue) {
                            possibleMoves.add(move);
                        }
                    }
                }
            } else {
                // If the dice is not white, find moves in the respective realm
                Realm realm = player.getRealm(dice);
                Move[] realmMoves = realm.getRealmMoves();
                for (Move move : realmMoves) {
                    int targetValue = (realm instanceof GreenRealm) ? diceValue + diceArray[5].getValue() : diceValue;
                    if ((realm instanceof RedRealm) && ((RedDice) dice).getDragonNumber() != 0) {
                        if (move.getDice().getValue() == diceValue && ((Dragon) move.getCreature()).getDragonNumber() == ((RedDice) dice).getDragonNumber()) {
                            possibleMoves.add(move);
                        }
                    } else if (move.getDice().getValue() == targetValue) {
                        possibleMoves.add(move);
                    }
                }
            }
            return possibleMoves.toArray(new Move[0]);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            return new Move[0];
        }

    }

    @Override
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public Player getActivePlayer() {
        return activePlayer;
    }

    @Override
    public Player getPassivePlayer() {
        return passivePlayer;
    }

    @Override
    public ScoreSheet getScoreSheet(Player player) {
        return player.getScoreSheet();
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public GameScore getGameScore(Player player) {
        if (player != null) {
            return player.getGameScore();
        }
        System.err.println("Cannot get player Game score since player is null");
        return new GameScore();
    }

    @Override
    public TimeWarp[] getTimeWarpPowers(Player player) {
        if (player != null) {
            return player.getTimeWarps();
        }
        return new TimeWarp[0];
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers(Player player) {
        if (player != null) {
            return player.getArcaneBoosts();
        }
        return new ArcaneBoost[0];
    }

    @Override
    public boolean selectDice(Dice dice, Player player) {
        if (dice == null || player == null) {
            System.err.println("Error: Dice or Player cannot be null.");
            return false;
        }
        try {
            dice.setDiceStatus(DiceStatus.TURN_SELECTED);
            for (Dice diceFromArray : diceArray) {
                if (diceFromArray.getDiceStatus() == DiceStatus.AVAILABLE && diceFromArray.getValue() < dice.getValue()) {
                    diceFromArray.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    private void moveDiceToForgottenRealm() {
        //Moves the rest of the dice unselected by active player to forgotten realm
        for (Dice i : diceArray) {
            if (i.getDiceStatus() == DiceStatus.AVAILABLE) {
                i.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
            }
        }
    }

    @Override
    public boolean makeMove(Player player, Move move) {
        try {
            if (move.getDice().getRealm() == GameColor.WHITE) {
                return false;
            }
            Realm realm = player.getRealm(move.getDice());
            realm.attack(move);
            performAntiCheatServiceChecks(player);
            if (realm.checkReward()) {
                processRewardQueue(player, realm.getReward());
            }
            return true;

        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    protected void performAntiCheatServiceChecks(Player player) {
        try {
            standardAntiCheatService.checkPlayerScore(player);
            standardAntiCheatService.checkDice(diceArray);
            standardAntiCheatService.checkPlayerFinalScore(player);
            standardAntiCheatService.checkPlayerReward(player);
            standardAntiCheatService.checkGameStatus(gameStatus);
        } catch (DiceCheatException e) {
            System.err.println(e.getMessage());
            standardAntiCheatService.handleDiceCheat(diceArray);
        } catch (InvalidFinalScoreCheat e) {
            System.err.println("Cheat detected in final score of player: " + player.getName());
            systemManager.exit("Cheat detected!");
        } catch (RewardCheatException e) {
            System.err.println("Cheat detected in rewards of player: " + player.getName());
            standardAntiCheatService.handleRewardCheat(player);
        } catch (NegativeScoreException e) {
            System.err.println("Cheat detected in score of player: " + player.getName());
            System.err.println("Score is below zero!");
            standardAntiCheatService.handlePlayerScore(player);
        } catch (HighScoreException e) {
            System.err.println("Cheat detected in score of player: " + player.getName());
            System.err.println("Score is invalid: " + player.getGameScore().getTotalScore());
            standardAntiCheatService.handlePlayerScore(player);
        } catch (CheatDetectedException e) {
            systemManager.exit("Cheat detected!");
        }
    }

    public Collectibles[] getRoundRewards() {
        return roundRewards;
    }

    protected void endGame() {
        gameGuide.closeScanner();
        sc.close();
        System.out.println(activePlayer.getName());
        activePlayer.getScoreSheet().displayScoreSheet();
        System.out.println("*".repeat(100));
        System.out.println(passivePlayer.getName());
        passivePlayer.getScoreSheet().displayScoreSheet();
        System.out.println("*".repeat(100));
        System.out.println(activePlayer.getGameScore());
        System.out.println(passivePlayer.getGameScore());
        int diff = activePlayer.getGameScore().getTotalScore() - passivePlayer.getGameScore().getTotalScore();
        if (diff == 0) {
            System.out.println("Draw!");
        } else {
            if (diff > 0) {
                //Active player is the winner
                System.out.println(activePlayer + " is the winner!");
                gameStatus.setGameStatus(CurrentStatus.PLAYER_1_WINS);

            } else {
                System.out.println(passivePlayer + " is the winner!");
                gameStatus.setGameStatus(CurrentStatus.PLAYER_2_WINS);

            }
        }

        //Compares GameScore of each player and declares winner
        System.out.println("Game developed by Team: ");
        System.out.println("  ZZZZ  EEEEE  U   U  SSSS");
        System.out.println("     Z  E      U   U  S    ");
        System.out.println("    Z   EEEE   U   U   SSS ");
        System.out.println("   Z    E      U   U      S");
        System.out.println("  ZZZZ  EEEEE   UUU   SSSS");
        systemManager.exit();
    }

}