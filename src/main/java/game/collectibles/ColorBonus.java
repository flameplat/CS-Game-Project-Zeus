package game.collectibles;

import game.utilities.Color;
import game.utilities.ColorComparator;

public class ColorBonus extends Collectibles implements Comparable<ColorBonus> {
    private static final String instruction =
            "Color Bonus Unlocked: " +
                    "Your feat grants an immediate bonus " +
                    "attack in other realms, enabling precise " +
                    "strikes against specific enemies. Use it now!";
    // -----------------------Attributes-----------------------//
    private final Color colorBonus;

    // -----------------------constructor-----------------------//
    public ColorBonus(Color color) {
        this.colorBonus = color;
    }
    //-----------------------Methods-----------------------//

    public static String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return colorBonus.toString().charAt(0) + "B";
    }

    public Color getColor() {
        return colorBonus;
    }

    @Override
    public int compareTo(ColorBonus o) {
        return new ColorComparator().compare(colorBonus, o.getColor());
    }

    public String getName() {
        return colorBonus + " BONUS";
    }
}
