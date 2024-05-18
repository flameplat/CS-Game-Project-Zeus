package game.utilities;

import java.util.Comparator;

public class ColorComparator implements Comparator<Color> {
    @Override
    public int compare(Color o1, Color o2) {
        return getColorPriority(o1) - getColorPriority(o2);
    }
    private int getColorPriority(Color color) {
        return color.ordinal();
    }
}
