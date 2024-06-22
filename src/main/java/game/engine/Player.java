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
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Player {
    private static final Map<String, Integer> collectibleCounters = new HashMap<>();
    private static int id = 1;
    private final ScoreSheet scoreSheet;
    protected final GameScore gameScore;
    private String name;
    //----------------------Attributes--------------------------//
    private Realm[] realms;
    private PlayerStatus playerStatus;
    private LinkedList<ArcaneBoost> arcaneBoosts;
    private LinkedList<TimeWarp> timeWarps;
    private CompositeScoreSheetController scoreSheetController;
    private int timeWarpsUsed;
    private int arcaneBoostsUsed;
    private Image wizardImage;
    protected boolean isAI;

    /**
     * The Player class represents a player in a game.
     * It manages the player's name, realms, game score, time warps,
     * arcane boosts, and AI status.
     *
     * @param name The name of the player.
     * @throws InvalidPlayerNameException if the name is empty or contains special characters.
     */
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
        isAI=false;
        isArcaneBoostUsed=false;
    }

    /**
     * Retrieves the number of Arcane Boosts used by the player.
     *
     * @return The number of Arcane Boosts used.
     */
    public int getArcaneBoostsUsed() {
        return arcaneBoostsUsed;
    }

    /**
     * Returns the number of time warps used by the player.
     *
     * @return The number of time warps used.
     */
    public int getTimeWarpsUsed() {
        return timeWarpsUsed;
    }

    /**
     * Player class represents a player in the game.
     */
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
        isAI=false;
    }

    /**
     * Retrieves the collectibles counters of the player.
     *
     * @return A map containing the collectibles counters. The keys are the names of the collectibles and the values are the corresponding counters.
     */
    public Map<String, Integer> getCollectiblesCounters() {
        return collectibleCounters;
    }
    //----------------------Methods--------------------------//

    /**
     * Checks if the given name contains any special characters.
     *
     * @param name the name to be checked
     * @return true if the name contains special characters, false otherwise
     */
    private boolean checkSpecialCharacters(String name) {
        String regex = "^[a-zA-Z0-9]+$";
        return !name.matches(regex);
    }
    /**
     * Retrieves the wizard image associated with the player.
     *
     * @return The wizard image of the player.
     */
    public Image getWizardImage(){
        return wizardImage;
    }
    /**
     * Sets the image of the wizard.
     *
     * @param wizardImage the image to set as the wizard image
     */
    public void setWizardImage(Image wizardImage){
        this.wizardImage=wizardImage;
    }

    /**
     * Initializes the realms for the player.
     * The method creates an array of Realm objects and assigns different realm objects to it.
     * The realms are initialized in the following order - RED, GREEN, BLUE, MAGENTA, YELLOW.
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
     * Receives a collectible and updates the player's game state accordingly.
     *
     * @param collectible The collectible to be received.
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

    /**
     * Resets the rewards of the player. It initializes the 'arcaneBoosts' and 'timeWarps'
     * LinkedLists to new empty LinkedLists.
     */
    public void resetRewards() {
        this.arcaneBoosts = new LinkedList<>();
        this.timeWarps = new LinkedList<>();
    }

    /**
     * Returns the scoresheet of the player.
     *
     * @return the score sheet of the player
     */
    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }

    /**
     * Retrieves the game score of the player.
     *
     * @return the GameScore object representing the player's game score.
     */
    public GameScore getGameScore() {
        return gameScore;
    }

    /**
     * Check if the player's Time Warp powers are available.
     * Returns true if available, false otherwise.
     *
     * @return true if the player has available Time Warp powers, false otherwise.
     */
    // Check the player's Time Warp powers array
    // Return true if available, false otherwise
    public boolean isTimeWarpAvailable() {
        return !timeWarps.isEmpty();
    }

    /**
     * Check if the player's Arcane Boost powers are available.
     *
     * @return {@code true} if available, {@code false} otherwise
     */
    // Check the player's Arcane Boost powers array
    // Return true if available, false otherwise
    public boolean isArcaneBoostAvailable() {
        return !arcaneBoosts.isEmpty();
    }

    /**
     * Removes a time warp power from the player's collection if available.
     * Increments the number of time warps used by the player.
     */
    public void useTimeWarpPower() {
        if (!timeWarps.isEmpty()) {
            timeWarps.remove();
            timeWarpsUsed++;
        }
    }

    /**
     * Uses the Arcane Boost power of the player.
     * If the player has any remaining Arcane Boosts, removes one boost from the list, marks the boost as used, and increments the count of used boosts.
     */
    public void useArcaneBoostPower() {
        if (!arcaneBoosts.isEmpty()) {
            arcaneBoosts.remove();
            isArcaneBoostUsed=true;
            arcaneBoostsUsed++;
        }
    }
    /**
     * Sets the name of the player.
     *
     * @param name the name of the player
     */
    public void setName(String name){
        this.name=name;
    }
    /**
     * Checks if the player is an AI.
     *
     * @return true if the player is an AI, false otherwise
     */
    public boolean isAI(){
        return isAI;
    }
    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the total number of Time Warps collected and unused by the player.
     *
     * @return The total number of Time Warps collected and unused.
     */
    //Returns total Time Warps collected and unused
    public int getTotalTimeWarpPowersCollected() {
        return timeWarps.size();
    }

    /**
     * Returns the total number of unused Arcane Boost powers collected by the player.
     *
     * @return the total number of unused Arcane Boost powers collected
     */
    //Returns total Arcane Boosts collected and unused
    public int getTotalArcaneBoostPowersCollected() {
        return arcaneBoosts.size();
    }

    /**
     * Retrieves an array of ArcaneBoost objects representing the available Arcane Boost powers of a player.
     *
     * @return An array of ArcaneBoost objects representing the available Arcane Boost powers of the player.
     */
    public ArcaneBoost[] getArcaneBoosts() {
        return arcaneBoosts.toArray(ArcaneBoost[]::new);
    }

    /**
     * Returns an array of TimeWarp objects representing the available Time Warp powers for the player.
     *
     * @return An array of TimeWarp objects.
     */
    public TimeWarp[] getTimeWarps() {
        return timeWarps.toArray(TimeWarp[]::new);
    }

    /**
     * Retrieves the realm associated with the given game color.
     *
     * @param gameColor the game color for which to retrieve the realm
     * @return the realm associated with the given game color
     */
    public Realm getRealm(GameColor gameColor) {
        return realms[gameColor.ordinal()];
    }

    /**
     * Retrieves the array of available realms.
     *
     * @return The array of available realms.
     */
    public Realm[] getRealms() {
        return realms;
    }

    /**
     *
     */
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

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Retrieves the current status of the player.
     *
     * @return The status of the player as an instance of PlayerStatus enum. Possible values are ACTIVE and PASSIVE.
     */
    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    /**
     * Sets the player status.
     * The player status can be either ACTIVE or PASSIVE.
     *
     * @param status The player status to set.
     */
    public void setPlayerStatus(PlayerStatus status) {
        this.playerStatus = status;
    }
    /**
     * Sets the GUI score sheet for the player.
     *
     * @param scoreSheet the CompositeScoreSheetController object representing the GUI score sheet
     */
    public void setGUIScoreSheet(CompositeScoreSheetController scoreSheet){
        this.scoreSheetController=scoreSheet;
        this.scoreSheetController.setPlayer(this);
    }
    /**
     * Retrieves the score sheet controller associated with this player.
     *
     * @return The score sheet controller for this player.
     */
    public CompositeScoreSheetController getScoreSheetController(){
        return scoreSheetController;
    }
    /**
     * This variable represents whether the Arcane Boost power is skipped or not.
     * If the value is true, it means the Arcane Boost power is skipped. If the value is false, it means the Arcane Boost power is not skipped.
     */
    private boolean isArcaneBoostSkipped;
    /**
     * Represents whether the arcane boost power has been used by the player.
     */
    private boolean isArcaneBoostUsed;
    /**
     * Checks if the Arcane Boost has been skipped by the player.
     *
     * @return true if the Arcane Boost has been skipped, false otherwise
     */
    public boolean isArcaneBoostSkipped(){
        return isArcaneBoostSkipped;
    }
    /**
     *
     */
    public void setArcaneBoostSkipped(boolean state){
        isArcaneBoostSkipped=state;
    }
    /**
     * Checks if the Arcane Boost power has been used.
     *
     * @return true if the Arcane Boost power has been used, false otherwise.
     */
    public boolean isArcaneBoostUsed(){
        return isArcaneBoostUsed;
    }
    /**
     * Resets the usage of Arcane Boost.
     * Sets the isArcaneBoostUsed flag to false.
     */
    public void resetArcaneBoostUsage(){
        isArcaneBoostUsed=false;
    }
}
