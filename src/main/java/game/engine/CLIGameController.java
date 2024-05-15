package game.engine;

import game.utilities.CollectiblesComparator;
import game.utilities.Color;
import game.collectibles.*;
import game.creatures.Dragon;
import game.dice.*;
import game.exceptions.InvalidPlayerNameException;
import game.exceptions.MissingGameFilesException;
import game.exceptions.NoAvailableMovesException;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;
import game.system.SystemManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CLIGameController extends GameController {

    // -----------------------Attributes-----------------------//
    private static final String GAME_PROPERTIES_PATH = "src/main/resources/config/Game.properties";
    private static final String ROUNDS_REWARDS_PATH = "src/main/resources/config/RoundsRewards.properties";
    private static int MAX_NUMBER_OF_ROUNDS;
    public static int MAX_NUMBER_OF_TURNS;
    public static Collectibles[] roundRewards;
    private final Dice[] diceArray;
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
        } catch (IOException | NumberFormatException e) {
            // Handle the exception gracefully
            System.err.println("Error loading game properties: " + e.getMessage());
            // Default values
            MAX_NUMBER_OF_ROUNDS = 6;
            MAX_NUMBER_OF_TURNS = 3;
        }
    }
    private final GameBoard gameBoard;
    private Player activePlayer;
    private Player passivePlayer;


    private GameGuide gameGuide;
    private final SystemManager systemManager;
    private final Scanner sc; //Will be closed at the end of the game

    // -----------------------Constructor-----------------------//
    public CLIGameController(){
        systemManager = new SystemManager();
        systemManager.performSystemChecks();
        gameGuide = new GameGuide();
        gameBoard=new GameBoard();
        gameStatus=new GameStatus();
        gameStatus.setGameStatus(CurrentStatus.IN_PROGRESS);
        diceArray=gameBoard.getDice();
        sc = new Scanner(System.in);
        activePlayer = gameBoard.getPlayer1();
        passivePlayer = gameBoard.getPlayer2();
    }
    // -----------------------Methods-----------------------//
    @Override
    public void startGame() {
        mainMenu();
        Player player1=getPlayerName("Enter Player 1 name: ");
        gameBoard.setPlayer1(player1);
        Player player2=getPlayerName("Enter Player 2 name: ");
        gameBoard.setPlayer2(player2);
        player1.setPlayerStatus(PlayerStatus.ACTIVE);
        player2.setPlayerStatus(PlayerStatus.PASSIVE);
        activePlayer = player1;
        passivePlayer = player2;
        for (int i = 0; i < MAX_NUMBER_OF_ROUNDS; i++) {
            gameStatus.resetTurn();
            System.out.println("Round "+(i+1));
            //Active player receives round reward
            if(roundRewards[i]!=null){
                performReward(activePlayer,roundRewards[i]);
            }
            playRound();
            switchPlayer();
            System.out.println("Round "+(i+1));
            //Active player receives round reward
            if(roundRewards[i]!=null){
                performReward(activePlayer,roundRewards[i]);
            }
            playRound();
            switchPlayer();
            gameStatus.incrementRound();
        }
        endGame();
    }


    private void mainMenu() {
        gameGuide.displayMenu();
        int choice = gameGuide.getUserChoice(1, 2);
        if (choice == 2) {
            gameGuide.closeScanner();
            systemManager.exit();
        }
        gameGuide.displayInstructions(Instruction.GAME);

    }
    private void checkArcaneBoost(Player player){
        while (player.isArcaneBoostAvailable()) {
            System.out.println(player.getName());
            gameGuide.displayInstructions(Instruction.AB_PROMPT);
            int count =player.getTotalArcaneBoostPowersCollected();
            System.out.printf("You have %d Arcane Boost%s%n",count,count>1?"s":"");
            boolean choice = gameGuide.getUserBooleanChoice();
            if (choice) {
                player.useArcaneBoostPower();
                playExtraTurn(player);
            }
            else{
                break;
            }
        }
    }
    private void playExtraTurn(Player player){
        System.out.println(player.getScoreSheet());
        LinkedList<Dice> notSelectedByPlayer=new LinkedList<>();
        DiceStatus filter=player.getPlayerStatus()==PlayerStatus.ACTIVE? DiceStatus.ACTIVE_PLAYER_SELECTED:DiceStatus.PASSIVE_PLAYER_SELECTED;
        for(Dice i:diceArray){
            if(i.getDiceStatus()!=filter){
                notSelectedByPlayer.add(i);
            }
        }
        Dice[] diceNotSelectedByPlayer=notSelectedByPlayer.toArray(Dice[]::new);
        try {
            Dice selectedDie=selectValidDie(player,diceNotSelectedByPlayer,false);
            selectedDie.setDiceStatus(filter);
            Move validMove=selectValidMove(player,selectedDie);
            makeMove(player,validMove);
            if(player.getRealm(validMove.getDice()).checkReward()){
                Collectibles[] rewards=player.getRealm(validMove.getDice()).getReward();
                processRewardQueue(player,rewards);
            }
        }
        catch (NoAvailableMovesException e) {
            System.out.println("Ohh bad luck...there are no possible moves, turn lost!");
        }



    }
    private boolean checkTimeWarp(){
        if(activePlayer.isTimeWarpAvailable()) {
            boolean choice=false;
            int count = activePlayer.getTotalTimeWarpPowersCollected();
            gameGuide.displayInstructions(Instruction.TW_PROMPT);
            System.out.printf("You have %d Time Warp%s%n",count,count>1?"s":"");
            choice = gameGuide.getUserBooleanChoice();
            if (choice) {
                activePlayer.useTimeWarpPower();
                rollDice();
                return true;
            }
        }
        return false;
    }
    private void playEssenceBonus(Player player){
        gameGuide.displayInstructions(Instruction.ESSENCE_BONUS);
        System.out.println(player.getScoreSheet());
        Realm[] realms=player.getRealms();
        LinkedList<Color> availableRealms=new LinkedList<>();
        for(Realm r:realms){
            if(r.isRealmAvailable()){
                availableRealms.addLast(r.getColor());
            }
        }
        Color[] colors= availableRealms.toArray(Color[]::new);
        gameGuide.displayNumberedChoice(colors);
        int choice =gameGuide.getUserChoice(1,colors.length);
        playColorBonus(player,colors[choice-1]);

    }
    public void playColorBonus(Player player,Color color){
        gameGuide.displayInstructions(Instruction.COLOR_BONUS);
        switch (color){
            case RED: {
                Dice[] redDice = new Dice[]{
                        new RedDice(1),
                        new RedDice(2),
                        new RedDice(3),
                        new RedDice(4),
                        new RedDice(5),
                        new RedDice(6)};
                try{
                    Dice selectedDie=selectValidDie(player,redDice,false);
                    Move selectedMove=selectValidMove(player,selectedDie);
                    makeMove(player,selectedMove);
                    if(player.getRealm(Color.RED).checkReward()){
                        Collectibles[] rewards=player.getRealm(Color.RED).getReward();
                        processRewardQueue(player,rewards);
                    }

                }
                catch (NoAvailableMovesException e){
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;

            }
            case GREEN: {
                // Define green dice
                Dice[] greenDice = new Dice[]{
                        new GreenDice(1),
                        new GreenDice(2),
                        new GreenDice(3),
                        new GreenDice(4),
                        new GreenDice(5),
                        new GreenDice(6)};
                try {
                    // Select a valid die and move
                    Dice selectedDie = selectValidDie(player, greenDice,false);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                    if(player.getRealm(Color.GREEN).checkReward()){
                        Collectibles[] rewards=player.getRealm(Color.GREEN).getReward();
                        processRewardQueue(player,rewards);
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
                        new BlueDice(1),
                        new BlueDice(2),
                        new BlueDice(3),
                        new BlueDice(4),
                        new BlueDice(5),
                        new BlueDice(6)};
                try {
                    // Select a valid die and move
                    Dice selectedDie = selectValidDie(player, blueDice,false);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                    if(player.getRealm(Color.BLUE).checkReward()){
                        Collectibles[] rewards=player.getRealm(Color.BLUE).getReward();
                        processRewardQueue(player,rewards);
                    }
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
                    Dice selectedDie = selectValidDie(player, magentaDice,false);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                    if(player.getRealm(Color.MAGENTA).checkReward()){
                        Collectibles[] rewards=player.getRealm(Color.MAGENTA).getReward();
                        processRewardQueue(player,rewards);
                    }
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
                    Dice selectedDie = selectValidDie(player, yellowDice,false);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                    if(player.getRealm(Color.YELLOW).checkReward()){
                        Collectibles[] rewards=player.getRealm(Color.YELLOW).getReward();
                        processRewardQueue(player,rewards);
                    }
                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
        }

    }
    private void processRewardQueue(Player player,Collectibles[] rewards){
        PriorityQueue<Collectibles> priorityQueue=new PriorityQueue<>(new CollectiblesComparator());
        priorityQueue.addAll(Arrays.asList(rewards));
        while (!priorityQueue.isEmpty()){
            performReward(player,priorityQueue.remove());
        }
    }
    private void performReward(Player player, Collectibles reward){
        System.out.println(player.getName()+", you received "+reward.toString()+"!");
        if(reward instanceof EssenceBonus){
            playEssenceBonus(player);
        }
        else{
            if(reward instanceof ColorBonus){
                playColorBonus(player,((ColorBonus)reward).getColor());
            }
            else{
                player.receivePower(reward);
            }
        }
    }

    private void playRound() {
        System.out.println(activePlayer.getName());
        gameGuide.displayInstructions(Instruction.ROUND);
        resetDice();
        for (int i = 0; (i < MAX_NUMBER_OF_TURNS)&(containsAvailableDie()); i++) {
            System.out.println("Turn "+(i+1));
            playTurn();
        }
        moveDiceToForgottenRealm();
        playPassiveTurn();
        checkArcaneBoost(activePlayer);
        checkArcaneBoost(passivePlayer);

    }
    private boolean containsAvailableDie(){
        for(Dice i:diceArray){
            if(i.getDiceStatus()==DiceStatus.AVAILABLE){
                return true;
            }
        }
        return false;
    }
    //Selects a valid die (has an available move in player)
    //Prints given dice if no moves or prints filtered dice then the selected die
    private Dice selectValidDie(Player player,Dice[] dice,boolean checkTimeWarp) throws NoAvailableMovesException {
        Dice selectedDie;
        Dice[] filteredDice;
        if(getPossibleMovesForDice(player,dice).length==0){
            gameGuide.displayNumberedChoice(dice);
            throw new NoAvailableMovesException();
        }
        else {
            gameGuide.displayNumberedChoice(dice);
            filteredDice=filterDiceWithPossibleMoves(player,dice);
            System.out.printf("Possible Dice to choose from:%100s%n",player.getName());
            gameGuide.displayNumberedChoice(filteredDice);
            while(checkTimeWarp && checkTimeWarp()){
                filteredDice=filterDiceWithPossibleMoves(player,dice);
                gameGuide.displayNumberedChoice(dice);
                System.out.printf("Possible Dice to choose from:%100s%n",player.getName());
                gameGuide.displayNumberedChoice(filteredDice);
            }
            System.out.printf("Select a die from %d to %d%n", 1, filteredDice.length);
            int choice = gameGuide.getUserChoice(1, filteredDice.length);
            selectedDie = filteredDice[choice - 1];
        }
        System.out.println(selectedDie);
        return selectedDie;
    }
    private Dice[] filterDiceWithPossibleMoves(Player player,Dice[] dice){
        LinkedList<Dice> diceWithMoves=new LinkedList<>();
        for(Dice i:dice){
            if(getPossibleMovesForADie(player,i).length!=0){
                diceWithMoves.add(i);
            }
        }
        return diceWithMoves.toArray(Dice[]::new);
    }

    public GameGuide getGameGuide() {
        return gameGuide;
    }
    private Move selectValidMove(Player player, Dice selectedDie){
        //This method is called after selecting a valid die( a die with possible moves found with it)
        Move selectedMove=null;
        if(selectedDie instanceof WhiteDice) {
            System.out.println("Choose which realm to play with Arcane Prism");
            LinkedList<Dice> versatileDice=new LinkedList<>();
            //RED, GREEN, BLUE, MAGENTA, YELLOW
            Dice[] possibleDice={
                    new RedDice(selectedDie.getValue()),
                    new GreenDice(selectedDie.getValue()),
                    new BlueDice(selectedDie.getValue()),
                    new MagentaDice(selectedDie.getValue()),
                    new YellowDice(selectedDie.getValue())};
            for(Dice i:possibleDice){
                if(getPossibleMovesForADie(player,i).length!=0){
                    versatileDice.addLast(i);
                }
            }
            possibleDice=versatileDice.toArray(Dice[]::new);
            gameGuide.displayNumberedChoice(possibleDice);
            int choice=gameGuide.getUserChoice(1,possibleDice.length);
            selectedDie=possibleDice[choice-1];
            System.out.println(selectedDie);
            selectedMove=selectValidMove(player,selectedDie);
        }
        else{
            if(selectedDie instanceof RedDice){
                player.getScoreSheet().displayRedRealm();
                Move[] moves=getPossibleMovesForADie(player,selectedDie);
                while(selectedMove==null){
                    System.out.println("Choose a Dragon to attack");
                    System.out.print("Possible dragons to attack: ");
                    for(int i=0;i<moves.length;i++){
                        System.out.print(((Dragon)moves[i].getCreature()).getDragonNumber());
                        if(moves.length-i==2){
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                    int dragonNumber=gameGuide.getUserChoice(1,4);
                    for(Move i:moves){
                        if(((Dragon)i.getCreature()).getDragonNumber()==dragonNumber){
                            selectedMove=i;
                            break;
                        }
                    }
                    if(selectedMove==null){
                        System.out.println("Can't attack Dragon " + dragonNumber);
                    }
                }
                //Reset dragon number
                //This will allow the possible moves for red dice to be based on dice value only regardless of creature
                //and the test file will be able to select dragon number
                return selectedMove;
            }
            else{
                return getPossibleMovesForADie(player,selectedDie)[0];
            }
        }
        return selectedMove;

    }

    private void playTurn() {
        gameGuide.displayInstructions(Instruction.TURN);
        System.out.println("Here is your score sheet");
        activePlayer.getScoreSheet().displayScoreSheet();
        Dice[] availableDice=getAvailableDice();
        gameGuide.displayNumberedChoice(availableDice);
        gameGuide.displayInstructions(Instruction.ROLL);
        //Press enter to roll
        System.out.println("Press Enter to roll");
        sc.nextLine();
        rollDice();
        Dice selectedDie;
        while (true){
            try {
                selectedDie=selectValidDie(activePlayer,availableDice,true);
                selectDice(selectedDie,activePlayer);
                break;
            }
            catch (NoAvailableMovesException e){
                System.out.println("Ohh bad luck...No possible moves!");
                if(!checkTimeWarp()){
                    System.out.println("Turn lost!");
                    return;
                }
            }
        }


        Move selectedMove=selectValidMove(activePlayer,selectedDie);
        selectedDie=selectedMove.getDice();
        makeMove(activePlayer,selectedMove);
        if(selectedDie!=null && activePlayer.getRealm(selectedDie).checkReward()){
            Collectibles[] rewards=activePlayer.getRealm(selectedDie).getReward();
            processRewardQueue(activePlayer,rewards);
        }
        //Choosing a die, move (check if move is valid,if not choose another die)
        //execute move
        //All dice of value less than selected die's value goes to forgotten realm
        gameStatus.incrementTurn();
    }
    private void playPassiveTurn(){
        System.out.println(passivePlayer.getName());
        gameGuide.displayInstructions(Instruction.PASSIVE_TURN);
        System.out.println(passivePlayer.getScoreSheet());
        Dice[] temp=getForgottenRealmDice();
        Dice selectedDie;
        try {
            selectedDie=selectValidDie(passivePlayer,temp,false);
        }
        catch (NoAvailableMovesException e){
            System.out.println("Ohh bad luck...No possible moves!");
            System.out.println("Passive turn lost!");
            return;
        }
        Move selectedMove=selectValidMove(passivePlayer,selectedDie);
        selectedDie=selectedMove.getDice();
        makeMove(passivePlayer,selectedMove);
        if(selectedDie!=null && passivePlayer.getRealm(selectedDie).checkReward()){
            Collectibles[] rewards=passivePlayer.getRealm(selectedDie).getReward();
            processRewardQueue(passivePlayer,rewards);
        }
    }

    private void displayAvailableRealms(Player player){
        Realm[] realms=player.getRealms();
        StringBuilder result=new StringBuilder();
        result.append("[");
        for(int i=0;i<realms.length;i++){

            result.append(i+1).append("-");
            result.append(realms[i].getColor().toString());
            if(i<realms.length-1){
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
            } catch (MissingGameFilesException e) {
                systemManager.exit(e.getMessage());
            }
        }
    }

    @Override
    public boolean switchPlayer() {
        boolean flag;
        try{
            if (activePlayer!=passivePlayer && activePlayer.getPlayerStatus()==PlayerStatus.ACTIVE &&
                    passivePlayer.getPlayerStatus()==PlayerStatus.PASSIVE) {
                activePlayer.setPlayerStatus(PlayerStatus.PASSIVE);
                passivePlayer.setPlayerStatus(PlayerStatus.ACTIVE);
                Player temp=activePlayer;
                activePlayer=passivePlayer;
                passivePlayer=temp;
                flag= true;
            }
            else{
                flag= false;
            }

        }
        catch (NullPointerException e){
            System.err.println("Invalid Switch: "+e.getMessage());
            flag=false;
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
        Random random=new Random();
        int diceValue;
        //Dice values are from 1 to 6
        int diceMaxBound=6;
        int diceMinBound=1;
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
    private void resetDice(){
        for(Dice i:diceArray){
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
        LinkedList<Dice> list=new LinkedList<>();
        for(Dice i:diceArray){
            if(i.getDiceStatus()==DiceStatus.AVAILABLE){
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
     * @return An array of all dice in the forgotten realm
     */
    @Override
    public Dice[] getForgottenRealmDice() {
        LinkedList<Dice> list=new LinkedList<>();
        for(Dice i:diceArray){
            if(i.getDiceStatus()==DiceStatus.FORGOTTEN_REALM){
                list.add(i);
            }
        }
        Dice[] result=new Dice[list.size()];
        for(int i=0;i<result.length;i++){
            result[i]= list.get(i);
        }
        return result;
    }

    @Override
    public Move[] getAllPossibleMoves(Player player) {
        LinkedList<Move> list=new LinkedList<>();
        for(Realm i:player.getRealms()){
            Move[] moves=i.getRealmMoves();
            for(Move m:moves){
                list.addLast(m);
            }
        }
        Move[] result= new Move[list.size()];
        for(int i=0;i<result.length;i++){
            result[i]=list.get(i);
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
        return getPossibleMovesForDice(player,getAvailableDice());
    }
    private Move[] getPossibleMovesForDice(Player player,Dice[] dice){
        try{
            LinkedList<Move> availableMoves=new LinkedList<>();
            for(Dice i:dice){
                Move[] moves=getPossibleMovesForADie(player,i);
                for(Move m:moves){
                    //To not include duplicated moves if white die has same value of any other die in the given dice array
                    //And to not duplicate moves for green realm
                    if(!availableMoves.contains(m)){
                        availableMoves.addLast(m);
                    }
                }
            }
            return availableMoves.toArray(Move[]::new);
        }
        catch (NullPointerException e){
            System.err.println(e.getMessage());
        }
        return new Move[0];
    }
    @Override
    public Move[] getPossibleMovesForADie(Player player, Dice dice) {
        LinkedList<Move> possibleMoves = new LinkedList<>();
        int diceValue = dice.getValue();

        // If the dice is white, iterate over all realms
        if (dice.getRealm() == Color.WHITE) {
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
        if(player!=null){
            return player.getGameScore();
        }
        System.err.println("Cannot get player Game score since player is null");
        return null;
    }

    @Override
    public TimeWarp[] getTimeWarpPowers(Player player) {
        if(player!=null){
            return player.getTimeWarps();
        }
        return null;
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers(Player player) {
        if(player!=null){
            return player.getArcaneBoosts();
        }
        return null;
    }

    @Override
    public boolean selectDice(Dice dice, Player player) {
        boolean flag;
        try{
            dice.setDiceStatus(DiceStatus.TURN_SELECTED);
            player.setSelectedDie(dice);
            for (Dice diceFromArray : diceArray) {
                if (diceFromArray.getDiceStatus() == DiceStatus.AVAILABLE && diceFromArray.getValue() < dice.getValue()) {
                    diceFromArray.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
                }
            }
            flag=true;
        }
        catch (NullPointerException e){
            flag=false;
        }
        return flag;
    }

    private void moveDiceToForgottenRealm(){
        //Moves the rest of the dice unselected by active player to forgotten realm
        for(Dice i:diceArray){
            if(i.getDiceStatus()==DiceStatus.AVAILABLE){
                i.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
            }
        }
    }

    @Override
    public boolean makeMove(Player player, Move move) {
        Realm[] realms=player.getRealms();
        Color color=move.getDice().getRealm();
        boolean flag;
        switch (color){
            case RED:flag=realms[Color.RED.ordinal()].attack(move);break;
            case GREEN:flag=realms[Color.GREEN.ordinal()].attack(move);break;
            case BLUE:flag=realms[Color.BLUE.ordinal()].attack(move);break;
            case MAGENTA:flag=realms[Color.MAGENTA.ordinal()].attack(move);break;
            case YELLOW:flag=realms[Color.YELLOW.ordinal()].attack(move);break;
            default:return false;
        }
        return flag;

    }

    private void endGame(){
        gameGuide.closeScanner();
        sc.close();
        System.out.println(activePlayer.getName());
        System.out.println(activePlayer.getScoreSheet());
        System.out.println("*".repeat(100));
        System.out.println(passivePlayer.getName());
        System.out.println(passivePlayer.getScoreSheet());
        System.out.println("*".repeat(100));
        System.out.println(activePlayer.getGameScore());
        System.out.println(passivePlayer.getGameScore());

        int diff=activePlayer.getGameScore().getFinalScore()-passivePlayer.getGameScore().getFinalScore();
        if(diff==0){
            System.out.println("Draw!");
        }
        else{
            if(diff>0){
                //Active player is the winner
                System.out.println(activePlayer+" is the winner!");
            }
            else{
                System.out.println(passivePlayer+" is the winner!");
            }
        }

        System.out.println("Difference in score: "+Math.abs(diff));
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