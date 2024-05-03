package game.collectibles;

public class EssenceBonus extends Collectibles{
    private static final String instruction="Essence bonus gives you the ability to play any realm you want with any dice value";
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
    public CollectiblesType getType(){
        return type;
    }

    @Override
    public void setStatus(CollectiblesStatus status) {
        this.status=status;
    }
    public static String getInstruction(){
        return instruction;
    }



}
