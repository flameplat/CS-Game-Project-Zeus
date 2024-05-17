package game.engine;

import game.utilities.ColorComparator;
import game.dice.Dice;
import game.creatures.Creature;



public class Move implements Comparable<Move>{
    // -----------------------Attributes-----------------------//
    private final Creature creature;
    private final Dice dice;


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
        int color= (new ColorComparator().compare(dice.getRealm(), o.getDice().getRealm()));
        if(color==0){
            return Integer.compare(dice.getValue(), o.getDice().getValue());
        }
        return color;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Move other = (Move) o;
        return dice.equals(other.getDice()) && creature.equals(other.getCreature());
    }
}
