package game.collectibles;

import game.Color;

public class ColorBonus extends Collectibles {
    // -----------------------Attributes-----------------------//
    private Color colorBonus;
    private CollectiblesStatus status;





    // -----------------------constructor-----------------------//
    public ColorBonus(Color color){
            this.colorBonus=color;
            this.status=CollectiblesStatus.DISABLED;
    }
    //-----------------------Methods-----------------------//
    @Override
    public CollectiblesStatus getStatus() {
        return status;
    }
    @Override
    public void setStatus(CollectiblesStatus status) {
        this.status = status;
    }
    @Override
    public boolean isBonus(){
        return true;
    }

    @Override
    public String toString() {
        return null;
    }

}
