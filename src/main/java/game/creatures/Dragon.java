package game.creatures;

import game.Config;
import game.Realms.Realm;

public class Dragon extends Creature{
    //-----------------------Attributes-----------------------//
    private static int id=0;
    private int[] health;//[HEAD,WING,TAIL,HEART]
    /*  Dragon1[HEAD,WING,TAIL,HEART]
        Dragon2[HEAD,WING,TAIL,HEART]
        Dragon3[HEAD,WING,TAIL,HEART]
        Dragon4[HEAD,WING,TAIL,HEART]
     */

    private int score;
    private boolean isAlive;
    public Dragon(int[] hitValues,int score){
        health=new int[Config.MAX_NUM_DRAGON_REGIONS];
        for(int i=0;i<health.length;i++){
            health[i]=hitValues[i];
        }
        isAlive=true;
        this.score=score;
        id++;
    }
    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public boolean attack(int value,HitRegions region) {
        //Already creature to attack is chosen
        return false;
    }

    @Override
    public int getScore() {
        return score;
    }




    public int getId(){
        return id;
    }
}


