package game.collectibles;

public class ElementalCrest extends Collectibles{
    public static final CollectiblesType type=CollectiblesType.ELEMENTAL_CREST;
    @Override
    public CollectiblesType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Elemental Crest";
    }
}
