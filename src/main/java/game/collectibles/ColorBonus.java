package game.collectibles;

import game.Color;

public class ColorBonus extends Collectibles{
    private Color colorBonus;
    private CollectiblesStatus status;
    public ColorBonus(Color color){
        this.colorBonus=color;
        this.status=CollectiblesStatus.DISABLED;
    }

    @Override
    public CollectiblesStatus getStatus() {
        return status;
    }
    public void setStatus(CollectiblesStatus status){
        this.status=status;
    }
}
