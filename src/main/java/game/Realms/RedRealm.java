package game.Realms;

import game.Config;
import game.collectibles.Collectibles;
import game.creatures.Creature;
import game.creatures.Dragon;
import game.Color;
import game.engine.*;

public class RedRealm extends Realm {
    // -----------------------Attributes-----------------------//
    private int totalRealmScore;
    private Dragon[] dragons;
    private boolean[][] dragonsStatus;
    private Collectibles reward;
    private int noElementalCrests;
    private Color realmColor;
    private String name;

    // -----------------------Constructor-----------------------//
    public RedRealm(String name) {
        this.realmColor = Color.RED;
        this.name=name;
        dragons = new Dragon[Config.MAX_NUM_DRAGONS];
        initDragons();
        dragonsStatus = new boolean[4][4];
        totalRealmScore = 0;
    }

    // -----------------------Methods-----------------------//
    // Loop on all dragons on all regions update dead regions and alive regions
    public void updateDragonsStatus() {

    }

    // ENTER VALUES FOR:HEAD,WINGS,TAIL,HEART
    // NA->0
    private void initDragons() {
        dragons[0] = new Dragon(new int[] { 3, 2, 1, 0 }, 10);
        dragons[1] = new Dragon(new int[] { 6, 1, 0, 3 }, 14);
        dragons[2] = new Dragon(new int[] { 5, 0, 2, 4 }, 16);
        dragons[3] = new Dragon(new int[] { 0, 5, 4, 6 }, 20);
    }

    // get the score of the red realm
    public int getScore(int dragonNumber) {
        return dragons[dragonNumber].getScore();
    }

    // get the name of the realm
    @Override
    public String getName() {
        return name;
    }

    // get the realm color
    @Override
    public Color getColor() {
        return null;
    }

    // get the realm status
    public int getStatus() {
        return 0;
    }

    // get the rewards
    public Collectibles getReward() {
        return reward;
    }

    // Update reward attribute if sequence appears if no reward sets it to null
    // if reward is elemental crest set reward to null and increment elemental crests number
    @Override
    public boolean checkReward() {
        return false;
    }

    // Gets from Move: Creature to attack, Hit Region
    public boolean attack(Move move) {
        return false;
    }

    @Override
    public int getTotalScore() {
        return totalRealmScore;
    }
    public int getNoElementalCrests(){
        return noElementalCrests;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Creature[] getAliveCreatures() {
        return null;
    }


}
