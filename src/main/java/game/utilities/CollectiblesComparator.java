package game.utilities;

import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;

import java.util.Comparator;

public class CollectiblesComparator implements Comparator<Collectibles> {

    /**
     * Compares two Collectibles for ordering.
     * If both objects are instances of ColorBonus, it compares them using their compareTo method.
     * If o1 is not an instance of ColorBonus but o2 is, it returns -1.
     * If o1 is an instance of ColorBonus but o2 is not, it returns 1.
     *
     * @param o1 the first Collectibles object to be compared
     * @param o2 the second Collectibles object to be compared
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Collectibles o1, Collectibles o2) {
        if ((o1 instanceof ColorBonus) && (o2 instanceof ColorBonus)) {
            return ((ColorBonus) o1).compareTo((ColorBonus) o2);
        } else {
            if (!(o1 instanceof ColorBonus) && (o2 instanceof ColorBonus)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
