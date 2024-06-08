package game.engine;

import game.creatures.*;
import game.dice.*;
import game.utilities.ColorComparator;


public class Move implements Comparable<Move> {
    // -----------------------Attributes-----------------------//
    private Creature creature;
    private Dice dice;


    // -----------------------Constructor-----------------------//
    public Move(Dice dice, Creature creature) {
        this.dice = dice;
        this.creature = creature;
    }
    public Move(Move move){

        if (move.getDice() instanceof RedDice) {
            this.dice = new RedDice((RedDice) move.getDice());
        } else if (move.getDice() instanceof GreenDice) {
            this.dice = new GreenDice((GreenDice) move.getDice());
        } else if (move.getDice() instanceof BlueDice) {
            this.dice = new BlueDice((BlueDice)move.getDice());
        } else if (move.getDice() instanceof MagentaDice) {
            this.dice = new MagentaDice((MagentaDice) move.getDice());
        } else if (move.getDice() instanceof YellowDice) {
            this.dice = new YellowDice((YellowDice) move.getDice());
        } else if (move.getDice() instanceof WhiteDice) {
            this.dice = new WhiteDice((WhiteDice) move.getDice());
        }
        if(move.getCreature() instanceof Dragon) {
            this.creature = new Dragon((Dragon) move.getCreature());
        } else if(move.getCreature() instanceof Guardian) {
            this.creature = new Guardian((Guardian) move.getCreature());
        } else if(move.getCreature() instanceof Lion) {
            this.creature = new Lion((Lion) move.getCreature());
        } else if(move.getCreature() instanceof Phoenix) {
            this.creature = new Phoenix((Phoenix) move.getCreature());
        } else if(move.getCreature() instanceof Serpent) {
            this.creature = new Serpent((Serpent) move.getCreature());
        }
        this.isExecuted=false;

    }

    // -----------------------Methods-----------------------//
    public Creature getCreature() {
        return creature;
    }
    @Override
    public String toString() {
        return "[" + dice.toString() + ", " + creature.toString() + ", "+isExecuted+"]";
    }
    // return "[" + dice.toString() + ", " + creature.toString() + "]";

    public Dice getDice() {
        return dice;
    }


    @Override
    public int compareTo(Move o) {
        int color = (new ColorComparator().compare(dice.getRealm(), o.getDice().getRealm()));
        if (color == 0) {
            return Integer.compare(dice.getValue(), o.getDice().getValue());
        }
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Move other = (Move) o;
        return dice.equals(other.getDice()) && creature.equals(other.getCreature());
    }
    private boolean isExecuted;
    public boolean isExecuted(){
        return isExecuted;
    }
    public void execute(){
        isExecuted=true;
    }
}
