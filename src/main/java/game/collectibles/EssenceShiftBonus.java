package game.collectibles;

import game.Color;

public class EssenceShiftBonus extends Collectibles{
    private CollectiblesStatus status;
    public EssenceShiftBonus(){
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
    @Override
    public boolean isBonus(){
        return true;
    }


}
