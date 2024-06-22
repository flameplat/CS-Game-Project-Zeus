package game.engine;

import game.creatures.*;
import game.dice.*;
import game.utilities.ColorComparator;


public class Move implements Comparable<Move> {
    // -----------------------Attributes-----------------------//
    private Creature creature;
    private Dice dice;
    /**
     *
     */
    private boolean isExecuted;

    /**
     * Constructs a new Move object with the specified Dice and Creature.
     *
     * @param dice     the Dice object to be used for determining the movement distance
     * @param creature the Creature object to be moved
     */
    // -----------------------Constructor-----------------------//
    public Move(Dice dice, Creature creature) {
        this.dice = dice;
        this.creature = creature;
    }

    /**
     * Creates a new Move object by copying the values from the given Move object.
     * The isExecuted flag is copied from the given Move object.
     *
     * @param move the Move object to copy
     */
    public Move(Move move) {

        if (move.getDice() instanceof RedDice) {
            this.dice = new RedDice((RedDice) move.getDice());
        } else if (move.getDice() instanceof GreenDice) {
            this.dice = new GreenDice((GreenDice) move.getDice());
        } else if (move.getDice() instanceof BlueDice) {
            this.dice = new BlueDice((BlueDice) move.getDice());
        } else if (move.getDice() instanceof MagentaDice) {
            this.dice = new MagentaDice((MagentaDice) move.getDice());
        } else if (move.getDice() instanceof YellowDice) {
            this.dice = new YellowDice((YellowDice) move.getDice());
        } else if (move.getDice() instanceof WhiteDice) {
            this.dice = new WhiteDice((WhiteDice) move.getDice());
        }
        if (move.getCreature() instanceof Dragon) {
            this.creature = new Dragon((Dragon) move.getCreature());
        } else if (move.getCreature() instanceof Guardian) {
            this.creature = new Guardian((Guardian) move.getCreature());
        } else if (move.getCreature() instanceof Lion) {
            this.creature = new Lion((Lion) move.getCreature());
        } else if (move.getCreature() instanceof Phoenix) {
            this.creature = new Phoenix((Phoenix) move.getCreature());
        } else if (move.getCreature() instanceof Serpent) {
            this.creature = new Serpent((Serpent) move.getCreature());
        }
        this.isExecuted = move.isExecuted;

    }

    /**
     * Returns the creature associated with this object.
     *
     * @return the creature object
     */
    // -----------------------Methods-----------------------//
    public Creature getCreature() {
        return creature;
    }
    // return "[" + dice.toString() + ", " + creature.toString() + "]";

    /**
     * Returns a string representation of the Move object.
     *
     * @return a string representation of the Move object
     */
    @Override
    public String toString() {
        return "[" + dice.toString() + ", " + creature.toString() + ", " + isExecuted + "]";
    }

    /**
     * Retrieve the dice object.
     *
     * @return The dice object.
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * Compares this Move with the specified Move for order.
     *
     * @param o the Move to be compared
     * @return a negative integer, zero, or a positive integer as this Move is less than,
     * equal to, or greater than the specified Move
     */
    @Override
    public int compareTo(Move o) {
        int color = (new ColorComparator().compare(dice.getRealm(), o.getDice().getRealm()));
        if (color == 0) {
            return Integer.compare(dice.getValue(), o.getDice().getValue());
        }
        return color;
    }

    /**
     * Compares this Move object to the specified object for equality.
     * Returns true if the specified object is also a Move object, and
     * if the dice and creature properties of both objects are equal.
     *
     * @param o the object to be compared for equality with this Move object
     * @return true if the specified object is equal to this Move object, false otherwise
     */
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

    /**
     * Checks if the method isExecuted has been called.
     *
     * @return true if the method has been executed, false otherwise.
     */
    public boolean isExecuted() {
        return isExecuted;
    }

    /**
     * Executes the Move by setting the flag isExecuted to true.
     */
    public void execute() {
        isExecuted = true;
    }
}
