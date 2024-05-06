package game.creatures;

public abstract class Creature {
    // ------------------Abstract Methods------------------//
    public abstract boolean isAlive(); // check wheather the creature is dead or alive

    public abstract int getScore(); // get score of creature
    @Override
    public abstract String toString();
    // ------------------Concrete Methods------------------//

    public boolean attack(int value) {
        return false;
    }
    public boolean attack(){
        return false;
    }

    // attack for the dragon
    public boolean attack(int value, HitRegionsOfDragons region) {
        return false;
    }

}