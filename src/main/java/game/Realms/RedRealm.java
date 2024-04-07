package game.Realms;

import game.Config;
import game.collectibles.Collectibles;
import game.creatures.Dragon;
import game.Color;

import java.util.concurrent.locks.Condition;


public class RedRealm extends Realm{
    private int totalRealmScore;
    private Dragon[] dragons;
    private boolean[][] dragonsStatus;
    private Collectibles reward;
    private Color realmColor;
    public RedRealm(){
        this.realmColor=Color.RED;
        dragons=new Dragon[Config.MAX_NUM_DRAGONS];
        initDragons();
        dragonsStatus=new boolean[4][4];
        totalRealmScore=0;
    }
    public void updateDragonsStatus(){
        //Loop on all dragons on all regions update dead regions and alive regions
    }
    private void initDragons(){
        //ENTER VALUES FOR:HEAD,WINGS,TAIL,HEART
        //NA->0
        dragons[0]=new Dragon(new int[]{3,2,1,0},4);
        dragons[1]=new Dragon(new int[]{3,2,1,0},4);
        dragons[2]=new Dragon(new int[]{3,2,1,0},4);
        dragons[3]=new Dragon(new int[]{3,2,1,0},4);
    }
    public int getScore(int dragonNumber){
        return dragons[dragonNumber].getScore();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }

    public int getStatus(){
        return 0;
    }
    public Collectibles getReward(){
        return reward;
    }

    @Override
    public boolean checkReward() {
        //Update reward attribute if sequence appears
        //
        return false;
    }



}
