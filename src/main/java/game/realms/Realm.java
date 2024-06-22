package game.realms;

import game.collectibles.*;
import game.creatures.Creature;
import game.dice.Dice;
import game.engine.Move;
import game.utilities.GameColor;

public abstract class Realm {
    /**
     * Returns the name of the realm as a String.
     *
     * @return The name of the realm.
     */
    // -----------------------Abstract methods-----------------------//
    /* Method return the name of the realm as a String */
    public abstract String getName();

    /**
     * Method to return the color of the realm.
     *
     * @return The color of the realm.
     */
    /* Method to return the color of the realm */
    public abstract GameColor getColor();

    /**
     * Method to return the status of the realm.
     *
     * @return the status of the realm as an integer
     */
    /* Method to return the status of the realm */
    public abstract int getStatus();

    /**
     * Checks if the realm is available.
     *
     * @return true if the realm is available, false otherwise.
     */
    public abstract boolean isRealmAvailable();

    /**
     * Method to return the rewards of the realm.
     *
     * @return an array of Collectibles representing the rewards of the realm
     */
    /* Method to return the rewards of the realm */
    public abstract Collectibles[] getReward();

    /**
     * Method to check whether there is a reward or not after attacking.
     *
     * @return true if there is a reward, false otherwise.
     */
    /* Method to check whether there is a reward or not after attacking */
    public abstract boolean checkReward();

    /**
     * Attacks the realm with the specified move.
     *
     * @param move the move used for the attack
     * @return true if the attack is successful; false otherwise
     */
    public abstract boolean attack(Move move);

    /**
     * Returns the total score for the realm.
     *
     * @return the total score for the realm as an integer
     */
    public abstract int getTotalScore();

    /**
     * <p>Returns the number of elemental crests for a realm.</p>
     *
     * <p>The method should be implemented in the Realm class or its subclasses.</p>
     *
     * @return The number of elemental crests for the realm.
     */
    public abstract int getNoElementalCrests();

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    public abstract String toString();

    /**
     * Returns an array of possible moves in the realm.
     *
     * @return an array of Move objects representing possible moves in the realm.
     */
    public abstract Move[] getRealmMoves();

    /**
     * Retrieves a Creature based on a Dice.
     *
     * @param dice The Dice object to determine the realm.
     * @return A Creature object corresponding to the realm of the Dice.
     */
    public abstract Creature getCreature(Dice dice);
    /**
     * Calculates the Fake Score for a given move.
     *
     * @param move The move to calculate the Fake Score for.
     * @return The Fake Score for the move.
     */
    public abstract int getFakeScore(Move move);

}
