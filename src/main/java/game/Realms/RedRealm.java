package game.Realms;

import game.Config;
import game.creatures.Dragon;
import game.Color;


public class RedRealm {
    private Dragon[] dragons;
    private Color realmColor;
    public RedRealm(){
        this.realmColor=Color.RED;
        dragons=new Dragon[Config.MAX_NUM_DRAGONS];

    }
    private void initDragons(){
        //ENTER VALUES FOR:HEAD,WINGS,TAIL,HEART
        //NA->0
//        dragons[0]=new Dragon(new int[]{3,2,1,0});
//        dragons[1]=new Dragon(new int[]{3,2,1,0});
//        dragons[2]=new Dragon(new int[]{3,2,1,0});
//        dragons[3]=new Dragon(new int[]{3,2,1,0});
    }


}
