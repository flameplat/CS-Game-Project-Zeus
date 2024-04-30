package game.engine;

import game.Color;
import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.EssenceBonus;
import game.collectibles.TimeWarp;
import game.dice.*;
import game.exceptions.InvalidPlayerNameException;
import game.exceptions.MissingGameFilesException;
import game.system.SystemManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class CLIGameController extends GameController {

    // -----------------------Attributes-----------------------//
    private static final String GAME_PROPERTIES_PATH = "src/main/resources/config/Game.properties";
    private static final String ROUNDS_REWARDS_PATH = "src/main/resources/config/RoundsRewards.properties";
    private static int MAX_NUMBER_OF_ROUNDS;
    private static int MAX_NUMBER_OF_ROLLS;
    public static int MAX_NUMBER_OF_TURNS;
    public static Collectibles[] roundRewards;
    private Dice[] diceArray;
    private GameStatus gameStatus;
    static {
        Properties gameProperties = new Properties();
        Properties roundRewardProperties=new Properties();
        try (FileInputStream gameFileInputStream = new FileInputStream(GAME_PROPERTIES_PATH);
             FileInputStream roundRewardFileInputStream = new FileInputStream(ROUNDS_REWARDS_PATH)) {
            gameProperties.load(gameFileInputStream);
            roundRewardProperties.load(roundRewardFileInputStream);
            MAX_NUMBER_OF_ROUNDS = Integer.parseInt(gameProperties.getProperty("numberOfRounds","6"));
            MAX_NUMBER_OF_TURNS = Integer.parseInt(gameProperties.getProperty("numberOfTurns","3"));
            roundRewards = new Collectibles[MAX_NUMBER_OF_ROUNDS];
            for (int i = 0; i < MAX_NUMBER_OF_ROUNDS; i++) {
                String reward = roundRewardProperties.getProperty("round"+(i+1)+"Reward");
                if(reward!=null){
                    switch (reward){
                        case "TimeWarp":roundRewards[i]=new TimeWarp();break;
                        case "ArcaneBoost":roundRewards[i]=new ArcaneBoost();break;
                        case "EssenceBonus":roundRewards[i]=new EssenceBonus();break;
                        default:roundRewards[i]=null;
                    }
                }

            }
            MAX_NUMBER_OF_ROLLS = Integer.parseInt(gameProperties.getProperty("numberOfDiceRolls","3"));
        } catch (IOException | NumberFormatException e) {
            // Handle the exception gracefully
            System.err.println("Error loading game properties: " + e.getMessage());
            // Default values
            MAX_NUMBER_OF_ROUNDS = 6;
            MAX_NUMBER_OF_TURNS = 3;
            MAX_NUMBER_OF_ROLLS = 3;
        }
    }
    private GameBoard gameBoard;
    private Player activePlayer;
    private Player passivePlayer;
    private Dice selectedDice;
    private int roundsCount;

    private GameGuide gameGuide;
    private int turnsCount;
    private SystemManager systemManager;
    private Scanner sc; //Will be closed at the end of the game

    // -----------------------Constructor-----------------------//
    // -----------------------Methods-----------------------//
    @Override
    public void startGame() {
        systemManager = new SystemManager();
        systemManager.performSystemChecks();
        gameGuide = new GameGuide();
        gameBoard=new GameBoard();
        diceArray=gameBoard.getDice();
        mainMenu();
        sc = new Scanner(System.in);
        Player player1=getPlayerName("Enter Player 1 name: ");
        Player player2=getPlayerName("Enter Player 1 name: ");
        gameBoard.setPlayers(player1,player2);
        activePlayer = player1;
        passivePlayer = player2;
        for (int i = 0; i < MAX_NUMBER_OF_ROUNDS; i++) {
            playRound();
            playPassiveTurn();
            checkArcaneBoost(activePlayer);
            checkArcaneBoost(passivePlayer);
            switchPlayer();
        }
        endGame();
    }

    public void mainMenu() {
        gameGuide.displayMenu();
        int choice = gameGuide.getUserChoice(1, 2);
        if (choice == 2) {
            gameGuide.closeScanner();
            systemManager.exit();
        }
    }
    private void checkArcaneBoost(Player player){
        while (player.isArcaneBoostAvailable()) {
            gameGuide.displayInstructions(Instruction.AB_PROMPT);
            boolean choice = gameGuide.getUserBooleanChoice();
            if (choice) {
                player.useArcaneBoostPower();
                playTurn();
            }
            else{
                break;
            }
        }
    }
    private void checkTimeWarp(){
        while (activePlayer.isArcaneBoostAvailable()) {
            gameGuide.displayInstructions(Instruction.TW_PROMPT);
            boolean choice = gameGuide.getUserBooleanChoice();
            if (choice) {
                activePlayer.useArcaneBoostPower();
                rollDice();
            }
            else{
                break;
            }
        }
    }

    private void playRound() {
        System.out.println(activePlayer.getName());
        gameGuide.displayInstructions(Instruction.ROUND);
        getAvailableDice();
        for (int i = 0; (i < MAX_NUMBER_OF_TURNS)&(containsAvailableDie()); i++) {
            playTurn();
        }

    }
    public boolean containsAvailableDie(){
        for(Dice i:diceArray){
            if(i.getDiceStatus()==DiceStatus.AVAILABLE){
                return true;
            }
        }
        return false;
    }


    private void playTurn() {
        gameGuide.displayInstructions(Instruction.TURN);
        System.out.println(Arrays.toString(rollDice()));
        checkTimeWarp();
        //Choosing a die, move (check if move is valid,if not choose another die)
        //execute move
        //All dice of value less than selected die's value goes to forgotten realm

    }
    private void playPassiveTurn(){
        System.out.println(passivePlayer.getName());
        gameGuide.displayInstructions(Instruction.PASSIVE_TURN);

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
            } catch (MissingGameFilesException e) {
                systemManager.exit(e.getMessage());
            }
        }
    }

    @Override
    public boolean switchPlayer() {
        if (activePlayer!=passivePlayer && activePlayer.getPlayerStatus()==PlayerStatus.ACTIVE &&
        passivePlayer.getPlayerStatus()==PlayerStatus.PASSIVE) {
            activePlayer.setPlayerStatus(PlayerStatus.PASSIVE);
            passivePlayer.setPlayerStatus(PlayerStatus.ACTIVE);
            Player temp=activePlayer;
            activePlayer=passivePlayer;
            passivePlayer=temp;
            return true;
        }
        //System.err.println("Invalid Switch!");
        return false;
    }
    /**
     * Rolls all available dice for the current turn, assigning each a random
     * number from 1 to 6.
     *
     * @return An array of the currently rolled {@code Dice}.
     */
    @Override
    public Dice[] rollDice() {
        Random random=new Random();
        int diceValue;
        //Dice values are from 1 to 6
        int diceMaxBound=6;
        int diceMinBound=1;
        for (int i = 0; i < diceArray.length; i++) {
            if(diceArray[i]!=null && diceArray[i].getDiceStatus()==DiceStatus.AVAILABLE){
                diceValue=random.nextInt(diceMaxBound-diceMinBound+1)+diceMinBound;
                diceArray[i].setValue(diceValue);
            }
        }
        return diceArray;
    }
    /**
     * Gets the dice available for rolling or rerolling.
     *
     * @return An array of {@code Dice} available for the current turn.
     */
    @Override
    public Dice[] getAvailableDice() {
        Random random=new Random();
        int diceValue;
        //RED, GREEN, BLUE, MAGENTA, YELLOW, WHITE
        //Dice values are from 1 to 6
        int diceMaxBound=6;
        int diceMinBound=1;
        for (int i = 0; i < diceArray.length; i++) {
            diceValue=random.nextInt(diceMaxBound-diceMinBound+1)+diceMinBound;
            diceArray[i].setValue(diceValue);
        }
        return diceArray;
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
        return new Dice[0];
    }

    @Override
    public Dice[] getForgottenRealmDice() {
        return new Dice[0];
    }

    @Override
    public Move[] getAllPossibleMoves(Player player) {
        return new Move[0];
    }

    @Override
    public Move[] getPossibleMovesForAvailableDice(Player player) {
        return new Move[0];
    }

    @Override
    public Move[] getPossibleMovesForADie(Player player, Dice dice) {
        return new Move[0];
    }

    @Override
    public GameBoard getGameBoard() {
        return null;
    }

    @Override
    public Player getActivePlayer() {
        return null;
    }

    @Override
    public Player getPassivePlayer() {
        return null;
    }

    @Override
    public ScoreSheet getScoreSheet(Player player) {
        return player.getScoreSheet();
    }

    @Override
    public GameStatus getGameStatus() {
        return null;
    }

    @Override
    public GameScore getGameScore(Player player) {
        return null;
    }

    @Override
    public TimeWarp[] getTimeWarpPowers(Player player) {
        return new TimeWarp[0];
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers(Player player) {
        return new ArcaneBoost[0];
    }

    @Override
    public boolean selectDice(Dice dice, Player player) {
        return false;
    }

    @Override
    public boolean makeMove(Player player, Move move) {
        return false;
    }

    public void endGame(){
        //Compares GameScore of each player and declares winner
    }

}