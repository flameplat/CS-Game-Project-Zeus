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
    public CollectiblesStatus getStatus() {
        return status;
    }
    public void setStatus(CollectiblesStatus status) {
        this.status = status;
    }
}
