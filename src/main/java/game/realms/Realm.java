package game.realms;

import game.collectibles.*;
import game.utilities.Color;
import game.creatures.Creature;
import game.engine.Move;
import game.dice.Dice;

public abstract class Realm {
    // -----------------------Abstract methods-----------------------//
    /* Method return the name of the realm as a String */
    public abstract String getName();

    /* Method to return the color of the realm */
    public abstract Color getColor();

    /* Method to return the status of the realm */
    public abstract int getStatus();
    public abstract boolean isRealmAvailable();

    /* Method to return the rewards of the realm */
    public abstract Collectibles[] getReward();

    /* Method to check whether there is a reward or not after attacking */
    public abstract boolean checkReward();
    public abstract boolean attack(Move move);
    public abstract int getTotalScore();
    public abstract int getNoElementalCrests();
    public abstract String toString();
    public abstract Move[] getRealmMoves();
    public abstract Creature getCreature(Dice dice);
    public static Collectibles getCollectibleFromString(String reward) {
        if(reward==null){
            return null;
        }
        switch (reward) {
            case "TimeWarp":
                return new TimeWarp();
            case "ArcaneBoost":
                return new ArcaneBoost();
            case "EssenceBonus":
                return new EssenceBonus();
            case "RedBonus":
                return new ColorBonus(Color.RED);
            case "BlueBonus":
                return new ColorBonus(Color.BLUE);
            case "GreenBonus":
                return new ColorBonus(Color.GREEN);
            case "MagentaBonus":
                return new ColorBonus(Color.MAGENTA);
            case "YellowBonus":
                return new ColorBonus(Color.YELLOW);
            case "ElementalCrest":
                return new ElementalCrest();
            default:
                return null;
        }
    }

}
