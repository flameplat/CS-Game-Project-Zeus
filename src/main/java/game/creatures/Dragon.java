package game.creatures;

import game.Config;
import game.Realms.Realm;

public class Dragon extends Creature{

    private int[] health;//[HEAD,WING,TAIL,HEART]
    /*  Dragon1[HEAD,WING,TAIL,HEART]
        Dragon2[HEAD,WING,TAIL,HEART]
        Dragon3[HEAD,WING,TAIL,HEART]
        Dragon4[HEAD,WING,TAIL,HEART]
     */
    public Dragon(int head,int wing,int tail,int heart){
        health=new int[Config.MAX_NUM_DRAGON_REGIONS];
        health[Regions.HEAD.ordinal()]=head;
        health[Regions.WING.ordinal()]=wing;
        health[Regions.TAIL.ordinal()]=tail;
        health[Regions.HEART.ordinal()]=heart;
    }
    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public boolean attack(int value) {
        //Already creature to attack is chosen

    }
    public enum Regions{
        HEAD,WING,TAIL,HEART
    }


}
public class Region extends Dragon{

}
