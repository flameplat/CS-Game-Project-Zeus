package game.utilities;

import java.util.Comparator;

public class ColorComparator implements Comparator<GameColor> {
    @Override
    public int compare(GameColor o1, GameColor o2) {
        return getColorPriority(o1) - getColorPriority(o2);
    }

    private int getColorPriority(GameColor gameColor) {
        return gameColor.ordinal();
    }
}
