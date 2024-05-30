package game.dice;

import game.utilities.GameColor;

public abstract class Dice {

    //-----------------------Attributes-----------------------//

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

    // getter for the dice value
    public abstract int getValue();

    //-------------------------Methods------------------------//
    public abstract void setValue(int value);

    public abstract GameColor getRealm();

    public abstract DiceStatus getDiceStatus();

    public abstract void setDiceStatus(DiceStatus status);

}
