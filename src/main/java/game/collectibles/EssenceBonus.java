package game.collectibles;

public class EssenceBonus extends Collectibles{
    private static final CollectiblesType type=CollectiblesType.ESSENCE_BONUS;
    private CollectiblesStatus status;
    public EssenceBonus(){
        this.status=CollectiblesStatus.DISABLED;
    }
    @Override
    public String toString() {
        return null;
    }
    @Override
    public CollectiblesStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(CollectiblesStatus status) {
        this.status=status;
    }



}
