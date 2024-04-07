package game.engine;

import game.Config;
import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.CollectiblesStatus;
import game.collectibles.TimeWarp;
import game.Realms.Realm;
import game.Color;

import java.util.LinkedList;


public class Player {
    //----------------------Attributes--------------------------//
    private Realm[] realms;
    private ScoreSheet scoreSheet;
    private GameScore gameScore;
    private TimeWarp[] timeWarps;
    private String name;
    private int timeWarpCount;
    private int arcaneBoostCount;

    private ArcaneBoost[] arcaneBoosts;
    //----------------------Constructor--------------------------//
    public Player(String name){
        this.name=name;
        timeWarps=new TimeWarp[Config.NUM_POWERS];
        arcaneBoosts=new ArcaneBoost[Config.NUM_POWERS];
        realms=new Realm[Config.NUM_REALMS];
        initializePowers();
        initializeRealms();
        scoreSheet=new ScoreSheet(realms);
        gameScore=new GameScore(realms);
    }

    //----------------------Methods--------------------------//

    /**
     * Initialize all powers to DISABLED at the start of initialization of the player
     */
    private void initializePowers(){

    }
    /**
     * Initialize all realms at the start of initialization of the player
     */
    private void initializeRealms(){

    }
    /**
     * Receives the power and set its status to ENABLED.
     * @return true if the power was successfully received, false otherwise
     */
    boolean receivePower(Collectibles power){
        boolean flag=false;
        //Assumption: The player won't be able receive powers more than POWER_NUMBER
        if(power instanceof ArcaneBoost){
            for(int i = 0; i< Config.NUM_POWERS; i++){
                if(arcaneBoosts[i].getStatus()== CollectiblesStatus.DISABLED){
                    arcaneBoosts[i].setStatus(CollectiblesStatus.ENABLED);
                    arcaneBoostCount++;
                    flag=true;
                    break;
                }
            }
        }
        if(power instanceof TimeWarp){
            for(int i = 0; i< Config.NUM_POWERS; i++){
                if(timeWarps[i].getStatus()== CollectiblesStatus.DISABLED){
                    timeWarps[i].setStatus(CollectiblesStatus.ENABLED);
                    timeWarpCount++;
                    flag=true;
                    break;
                }
            }
        }
        return flag;

    }
    ScoreSheet getScoreSheet(){
        return scoreSheet;
    }
    // Check the player's Time Warp powers array
    // Return true if available, false otherwise
    public boolean isTimeWarpAvailable(){
        return arcaneBoostCount!=0;
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
    public boolean useTimeWarpPower(){
        //Decrement number of available TW powers
        return false;
    }
    /**
     * Use the Arcane Boost power and set its status to USED.
     *
     * @return true if the power was successfully used, false otherwise
     */
    public boolean useArcaneBoostPower() {
        //Decrement number of available AB powers
        return false;
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
    @Override
    public String toString(){
        return null;
    }





}
