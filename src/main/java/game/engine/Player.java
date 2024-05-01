package game.engine;

import game.Color;
import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.CollectiblesStatus;
import game.collectibles.TimeWarp;
import game.dice.Dice;
import game.exceptions.InvalidPlayerNameException;
import game.exceptions.MissingGameFilesException;
import game.realms.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Player {
    //----------------------Attributes--------------------------//
    private Realm[] realms;
    private ScoreSheet scoreSheet;
    private GameScore gameScore;
    private PlayerStatus playerStatus;
    private static int id=1;
    private String name;



    private int timeWarpCount;
    private int arcaneBoostCount;
    private Dice selectedDice;

    private ArcaneBoost[] arcaneBoosts;
    private static int MAX_NUMBER_OF_REALMS;
    private static int MAX_NUMBER_OF_TW;
    private static int MAX_NUMBER_OF_AB;
    static{
        Properties playerProperties = new Properties();
        Properties gameProperties = new Properties();
        //Try with res will automatically close fileInputStream
        try (FileInputStream fileInputStreamPlayer = new FileInputStream("src/main/resources/config/Player.properties")) {
            playerProperties = new Properties();
            playerProperties.load(fileInputStreamPlayer);
            MAX_NUMBER_OF_TW = Integer.parseInt(playerProperties.getProperty("maxNumberOfTWPowers","7"));
            MAX_NUMBER_OF_AB = Integer.parseInt(playerProperties.getProperty("maxNumberOfABPowers","7"));
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading player properties");
            //Load default values
            MAX_NUMBER_OF_TW=7;
            MAX_NUMBER_OF_AB=7;

        }
    }
    private TimeWarp[] timeWarps;

    //----------------------Constructor--------------------------//
    public Player(String name) throws InvalidPlayerNameException, MissingGameFilesException {
        if (name.length() == 0) {
            throw new InvalidPlayerNameException("Name cannot be empty");
        }
        if (checkSpecialCharacters(name)) {
            throw new InvalidPlayerNameException("Name cannot contain special characters");
        }
        this.name = name;
        loadProperties();
        initializePowers();
        initializeRealms();
        scoreSheet = new ScoreSheet(realms);
        gameScore = new GameScore(realms);
    }
    public Player(){
        this.name = String.format("Player %d",id);
        loadProperties();
        initializePowers();
        initializeRealms();
        scoreSheet = new ScoreSheet(realms);
        gameScore = new GameScore(realms);
        id++;
    }
    //----------------------Methods--------------------------//

    /**
     * Load player's attributes from resources
     */
    private void loadProperties() {
        timeWarps=new TimeWarp[MAX_NUMBER_OF_TW];
        arcaneBoosts=new ArcaneBoost[MAX_NUMBER_OF_AB];
        initializeRealms();
    }

    /**
     * Check if player name contains special characters
     *
     * @param name player's name
     */
    private boolean checkSpecialCharacters(String name) {
        String regex = "^[a-zA-Z0-9]+$";
        return !name.matches(regex);
    }




    /**
     * Initialize all powers to DISABLED at the start of initialization of the player
     */
    private void initializePowers() {

        //Constructors initiallizes them automatically to disabled
        for (int i=0;i<timeWarps.length;i++) {
            timeWarps[i]=new TimeWarp();
        }
        for (int i=0;i<arcaneBoosts.length;i++) {
            arcaneBoosts[i]=new ArcaneBoost();
        }
    }
    /**
     * Initialize all realms at the start of initialization of the player
     */
    private void initializeRealms(){
        //RED, GREEN, BLUE, MAGENTA, YELLOW
        realms=new Realm[5];
        realms[0]=new RedRealm();
        realms[1]=new GreenRealm();
        realms[2]=new BlueRealm();
        realms[3]=new MagentaRealm();
        realms[4]=new YellowRealm();
    }
    public void setPlayerStatus(PlayerStatus status){
        this.playerStatus=status;
    }
    /**
     * Receives the power and set its status to ENABLED.
     * @return true if the power was successfully received, false otherwise
     */
    boolean receivePower(Collectibles power){

        //Assumption: The player won't be able receive powers more than POWER_NUMBER
        if(power instanceof ArcaneBoost){
            for (int i = 0; i < arcaneBoosts.length; i++) {
                if (arcaneBoosts[i].getStatus() == CollectiblesStatus.DISABLED) {
                    arcaneBoosts[i].setStatus(CollectiblesStatus.ENABLED);
                    arcaneBoostCount++;
                    System.out.println("Arcane Boost Power Received!");
                    return true;
                }
            }
        }
        if(power instanceof TimeWarp){
            for (int i = 0; i < timeWarps.length; i++) {
                if (timeWarps[i].getStatus() == CollectiblesStatus.DISABLED) {
                    timeWarps[i].setStatus(CollectiblesStatus.ENABLED);
                    timeWarpCount++;
                    System.out.println("Time Warp Power Received!");
                    return true;
                }
            }
        }
        System.out.printf("%s can't receive more than %d Arcane Boost power & %d Time Warp power%n",name,MAX_NUMBER_OF_AB,MAX_NUMBER_OF_TW);
        return false;
    }
    ScoreSheet getScoreSheet(){
        return scoreSheet;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    // Check the player's Time Warp powers array
    // Return true if available, false otherwise
    public boolean isTimeWarpAvailable(){
        return timeWarpCount!=0;
    }
    // Check the player's Arcane Boost powers array
    // Return true if available, false otherwise
    public boolean isArcaneBoostAvailable(){
        return arcaneBoostCount!=0;
    }

    /**
     * Use the Time Warp power and set its status to USED.
     *
     * @return true if the power was successfully used, false otherwise
     */
    public boolean useTimeWarpPower() {
        //Decrement number of available TW powers
        boolean flag = false;
        for (int i = 0; i < timeWarps.length; i++) {
            if (timeWarps[i].getStatus() == CollectiblesStatus.ENABLED) {
                timeWarps[i].setStatus(CollectiblesStatus.USED);
                timeWarpCount--;
                flag = true;
                break;
            }
        }
        return flag;
    }
    /**
     * Use the Arcane Boost power and set its status to USED.
     *
     * @return true if the power was successfully used, false otherwise
     */
    public boolean useArcaneBoostPower() {
        boolean flag = false;
        for (int i = 0; i < arcaneBoosts.length; i++) {
            if (arcaneBoosts[i].getStatus() == CollectiblesStatus.ENABLED) {
                arcaneBoosts[i].setStatus(CollectiblesStatus.USED);
                arcaneBoostCount--;
                flag = true;
                break;
            }
        }
        return flag;
    }
    public String getName(){
        return name;
    }
    //Returns total Time Warps collected and unused
    public int getTotalTimeWarpPowersCollected(){
        return timeWarpCount;
    }
    //Returns total Arcane Boosts collected and unused
    public int getTotalArcaneBoostPowersCollected(){
        return arcaneBoostCount;
    }

    public ArcaneBoost[] getArcaneBoosts(){
        return arcaneBoosts;
    }
    public TimeWarp[] getTimeWarps(){
        return timeWarps;
    }
    public Realm getRealm(Color color){
        return realms[color.ordinal()];
    }
    public Realm[] getRealms(){
        return realms;
    }
    public Realm getRealm(Dice dice){
        for(Realm i:realms){
            if(dice.getRealm()==i.getColor()){
                return i;
            }
        }
        return null;
    }
    @Override
    public String toString(){
        return null;
    }
    public void setSelectedDice(Dice dice){
        this.selectedDice=dice;
    }
    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }
}
