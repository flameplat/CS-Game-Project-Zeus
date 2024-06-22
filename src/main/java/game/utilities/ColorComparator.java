package game.utilities;

import java.util.Comparator;

public class ColorComparator implements Comparator<GameColor> {
    /**
     * Compares two GameColor objects based on their priority.
     *
     * @param o1 the first GameColor object
     * @param o2 the second GameColor object
     * @return a negative integer if o1 has lower priority, zero if they have equal priority, or a positive integer if o1 has higher priority
     */
    @Override
    public int compare(GameColor o1, GameColor o2) {
        return getColorPriority(o1) - getColorPriority(o2);
    }

    /**
     * Returns the priority of a given GameColor.
     *
     * @param gameColor the GameColor whose priority is to be determined
     * @return the priority of the given GameColor
     */
    private int getColorPriority(GameColor gameColor) {
        return gameColor.ordinal();
    }
}
