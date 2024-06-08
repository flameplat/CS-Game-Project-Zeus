package game.collectibles;

import game.utilities.GameColor;
import game.utilities.ColorComparator;

public class ColorBonus extends Collectibles implements Comparable<ColorBonus> {
    private static final String instruction =
            "Color Bonus Unlocked: " +
                    "Your feat grants an immediate bonus " +
                    "attack in other realms, enabling precise " +
                    "strikes against specific enemies. Use it now!";
    // -----------------------Attributes-----------------------//
    private final GameColor gameColorBonus;

    // -----------------------constructor-----------------------//
    public ColorBonus(GameColor gameColor) {
        this.gameColorBonus = gameColor;
    }
    public ColorBonus(ColorBonus colorBonus){
        this.gameColorBonus=colorBonus.gameColorBonus;
    }
    //-----------------------Methods-----------------------//

    public static String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return gameColorBonus.toString().charAt(0) + "B";
    }

    public GameColor getColor() {
        return gameColorBonus;
    }

    @Override
    public int compareTo(ColorBonus o) {
        return new ColorComparator().compare(gameColorBonus, o.getColor());
    }

    public String getName() {
        return gameColorBonus + " BONUS";
    }
}
