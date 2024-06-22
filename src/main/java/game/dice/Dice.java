package game.dice;

import game.utilities.GameColor;

public abstract class Dice {

    //-----------------------Attributes-----------------------//

    /**
     * Creates a new instance of a Dice object based on the given GameColor and value.
     *
     * @param gameColor the color of the dice (RED, GREEN, BLUE, MAGENTA, YELLOW, WHITE)
     * @param value     the value of the dice
     * @return a new instance of a Dice object based on the given color and value
     */
    public static Dice getNewDice(GameColor gameColor, int value) {
        switch (gameColor) {
            case RED:
                return new RedDice(value);
            case GREEN:
                return new GreenDice(value);
            case BLUE:
                return new BlueDice(value);
            case MAGENTA:
                return new MagentaDice(value);
            case YELLOW:
                return new YellowDice(value);
            case WHITE:
                return new WhiteDice(value);
        }
        return null;
    }

    /**
     * Gets the value of the dice.
     *
     * @return The value of the dice.
     */
    // getter for the dice value
    public abstract int getValue();

    /**
     * Sets the value of the dice.
     *
     * @param value the new value of the dice
     */
    //-------------------------Methods------------------------//
    public abstract void setValue(int value);

    /**
     * Retrieves the realm of the dice.
     *
     * @return The realm of the dice as a GameColor enum value.
     */
    public abstract GameColor getRealm();

    /**
     * Retrieves the status of the dice.
     *
     * @return the status of the dice
     */
    public abstract DiceStatus getDiceStatus();

    /**
     * Sets the status of a dice.
     *
     * @param status the status to set for the dice
     */
    public abstract void setDiceStatus(DiceStatus status);
    /**
     * Returns the name of the dice.
     *
     * @return the name of the dice
     */
    public abstract String getName();

}
