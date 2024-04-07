package game.creatures;

import game.collectibles.Collectibles;

public abstract class Creature {
    // ------------------Abstract Methods------------------//
    public abstract boolean isAlive(); // check wheather the creature is dead or alive

    public abstract int getScore(); // get score of creature
    // ------------------Concrete Methods------------------//
    // attack

    public boolean attack(int value) {
        return false;
    }

    // attack for the dragon
    public boolean attack(int value, HitRegionsOfDragons region) {
        return false;
    }

    // get rewards after attcking
    public Collectibles getReward(int value) {
        return null;
    }
}
