package game.engine;

import game.Color;
import game.dice.GreenDice;
import game.realms.Realm;
import game.creatures.*;
import game.dice.Dice;


public class Move {
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
        return null;
    }
    public Dice getDice(){
        return dice;
    }


}
