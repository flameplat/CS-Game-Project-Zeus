package game.engine;

import game.utilities.ColorComparator;
import game.dice.Dice;
import game.creatures.Creature;



public class Move implements Comparable<Move>{
    // -----------------------Attributes-----------------------//
    private Creature creature;
    private Dice dice;


    // -----------------------Constructor-----------------------//
    public Move(Dice dice, Creature creature) {
        this.dice=dice;
        this.creature = creature;
    }

    // -----------------------Methods-----------------------//
    public Creature getCreature() {
        return creature;
    }

    @Override
    public String toString(){
        return "["+dice.toString()+", "+creature.toString()+"]";
    }
    public Dice getDice(){
        return dice;
    }


    @Override
    public int compareTo(Move o) {
        return new ColorComparator().compare(dice.getRealm(), o.getDice().getRealm());
    }
}
