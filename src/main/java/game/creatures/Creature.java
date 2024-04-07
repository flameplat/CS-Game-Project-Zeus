package game.creatures;

import game.collectibles.Collectibles;

public abstract class Creature {
    //------------------Abstract Methods------------------//
    public abstract boolean isAlive();

    public abstract int getScore();

    //------------------Concrete Methods------------------//
    public boolean attack(int value){
        return false;
    }
    public boolean attack(int value,HitRegions region){
        return false;
    }
    public Collectibles getReward(int value){
        return null;
    }
}
