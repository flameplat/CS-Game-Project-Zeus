package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;
import game.dice.Dice;
import game.exceptions.InvalidPlayerNameException;
import game.gui.CompositeScoreSheetController;
import game.realms.*;
import game.utilities.GameColor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Player {
    private static final Map<String, Integer> collectibleCounters = new HashMap<>();
    private static int id = 1;
    private final ScoreSheet scoreSheet;
    private final GameScore gameScore;
    private final String name;
    //----------------------Attributes--------------------------//
    private Realm[] realms;
    private PlayerStatus playerStatus;
    private LinkedList<ArcaneBoost> arcaneBoosts;
    private LinkedList<TimeWarp> timeWarps;
    private CompositeScoreSheetController scoreSheetController;

    //----------------------Constructor--------------------------//
    public Player(String name) throws InvalidPlayerNameException {
        if (name.isEmpty()) {
            throw new InvalidPlayerNameException("Name cannot be empty");
        }
        if (checkSpecialCharacters(name)) {
            throw new InvalidPlayerNameException("Name cannot contain special characters");
        }
        this.name = name;
        initializeRealms();
        scoreSheet = new ScoreSheet(realms);
        gameScore = new GameScore(realms, name);
        timeWarps = new LinkedList<>();
        arcaneBoosts = new LinkedList<>();
        isArcaneBoostSkipped=false;
        isArcaneBoostUsed=false;
    }


    public Player() {
        this.name = String.format("Player %d", id);
        initializeRealms();
        scoreSheet = new ScoreSheet(realms);
        gameScore = new GameScore(realms, name);
        id++;
        timeWarps = new LinkedList<>();
        arcaneBoosts = new LinkedList<>();
        isArcaneBoostSkipped=false;
        isArcaneBoostUsed=false;
    }

    public Map<String, Integer> getCollectiblesCounters() {
        return collectibleCounters;
    }
    //----------------------Methods--------------------------//

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
     * Initialize all realms at the start of initialization of the player
     */
    private void initializeRealms() {
        //RED, GREEN, BLUE, MAGENTA, YELLOW
        realms = new Realm[5];
        realms[0] = new RedRealm();
        realms[1] = new GreenRealm();
        realms[2] = new BlueRealm();
        realms[3] = new MagentaRealm();
        realms[4] = new YellowRealm();
    }

    /**
     * Receives the power and set its status to ENABLE.
     */
    public void receiveCollectible(Collectibles collectible) {
        if (collectible instanceof ElementalCrest) {
            this.gameScore.receiveElementalCrest();
        }
        //The player only receives 2 powers AB and TW
        if (collectible instanceof ArcaneBoost) {
            arcaneBoosts.addLast((ArcaneBoost) collectible);
            collectibleCounters.put("ArcaneBoost", collectibleCounters.getOrDefault("ArcaneBoost", 0) + 1);
            return;
        }
        if (collectible instanceof TimeWarp) {
            timeWarps.addLast((TimeWarp) collectible);
            collectibleCounters.put("TimeWarp", collectibleCounters.getOrDefault("TimeWarp", 0) + 1);
        }
    }

    public void resetRewards() {
        this.arcaneBoosts = new LinkedList<>();
        this.timeWarps = new LinkedList<>();
    }

    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    // Check the player's Time Warp powers array
    // Return true if available, false otherwise
    public boolean isTimeWarpAvailable() {
        return !timeWarps.isEmpty();
    }

    // Check the player's Arcane Boost powers array
    // Return true if available, false otherwise
    public boolean isArcaneBoostAvailable() {
        return !arcaneBoosts.isEmpty();
    }

    /**
     * Use the Time Warp power and set its status to USED.
     */
    public void useTimeWarpPower() {
        if (!timeWarps.isEmpty()) {
            timeWarps.remove();
        }
    }

    /**
     * Use the Arcane Boost power and set its status to USED.
     */
    public void useArcaneBoostPower() {
        if (!arcaneBoosts.isEmpty()) {
            arcaneBoosts.remove();
            isArcaneBoostUsed=true;
        }
    }

    public String getName() {
        return name;
    }

    //Returns total Time Warps collected and unused
    public int getTotalTimeWarpPowersCollected() {
        return timeWarps.size();
    }

    //Returns total Arcane Boosts collected and unused
    public int getTotalArcaneBoostPowersCollected() {
        return arcaneBoosts.size();
    }

    public ArcaneBoost[] getArcaneBoosts() {
        return arcaneBoosts.toArray(ArcaneBoost[]::new);
    }

    public TimeWarp[] getTimeWarps() {
        return timeWarps.toArray(TimeWarp[]::new);
    }

    public Realm getRealm(GameColor gameColor) {
        return realms[gameColor.ordinal()];
    }

    public Realm[] getRealms() {
        return realms;
    }

    public Realm getRealm(Dice dice) {
        if (dice.getRealm() == GameColor.WHITE) {
            System.err.println("There is no white realm");
            return null;
        }
        for (Realm i : realms) {
            if (dice.getRealm() == i.getColor()) {
                return i;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus status) {
        this.playerStatus = status;
    }
    public void setGUIScoreSheet(CompositeScoreSheetController scoreSheet){
        this.scoreSheetController=scoreSheet;
        this.scoreSheetController.setPlayer(this);
    }
    public CompositeScoreSheetController getScoreSheetController(){
        return scoreSheetController;
    }
    private boolean isArcaneBoostSkipped;
    private boolean isArcaneBoostUsed;
    public boolean isArcaneBoostSkipped(){
        return isArcaneBoostSkipped;
    }
    public void setArcaneBoostSkipped(boolean state){
        isArcaneBoostSkipped=state;
    }
    public boolean isArcaneBoostUsed(){
        return isArcaneBoostUsed;
    }
    public void resetArcaneBoostUsage(){
        isArcaneBoostUsed=false;
    }

}
