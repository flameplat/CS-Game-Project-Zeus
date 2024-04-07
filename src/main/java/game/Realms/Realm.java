package game.Realms;

import game.Color;
import game.collectibles.Collectibles;

public abstract class Realm {
    /* Method return the name of the realm as a String */
    public abstract String getName();
    /* Method to return the color of the realm*/
    public abstract Color getColor();
    public abstract int getStatus();
    public abstract Collectibles getReward();
    public abstract boolean checkReward();


}
