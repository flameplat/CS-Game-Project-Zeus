package game.utilities;

import java.util.Comparator;

public class ColorComparator implements Comparator<Color> {
    @Override
    public int compare(Color o1, Color o2) {
        return getColorPriority(o1) - getColorPriority(o2);
    }
    private int getColorPriority(Color color) {
        switch (color) {
            case RED:
                return 0;
            case GREEN:
                return 1;
            case BLUE:
                return 2;
            case MAGENTA:
                return 3;
            case YELLOW:
                return 4;
            case WHITE:return 5;
            default:
                throw new IllegalArgumentException("Unsupported color: " + color);
        }
    }
}
