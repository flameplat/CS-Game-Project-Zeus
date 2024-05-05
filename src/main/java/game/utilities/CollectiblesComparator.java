package game.utilities;

import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;

import java.util.Comparator;

public class CollectiblesComparator implements Comparator<Collectibles> {

    @Override
    public int compare(Collectibles o1, Collectibles o2) {
        if((o1 instanceof ColorBonus) && (o2 instanceof ColorBonus)){
            return ((ColorBonus) o1).compareTo((ColorBonus) o2);
        }
        else{
            if(!(o1 instanceof ColorBonus) && (o2 instanceof ColorBonus)){
                return -1;
            }
            else {
                return 1;
            }
        }
    }
}
