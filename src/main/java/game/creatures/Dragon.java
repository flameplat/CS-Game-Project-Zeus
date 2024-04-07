package game.creatures;

import game.Config;
import game.Realms.Realm;

public class Dragon extends Creature {
    // -----------------------Attributes-----------------------//
    private static int id = 0; // number of dragon
    private int[] health; // [HEAD,WING,TAIL,HEART]
    private int score; // score of the dragon
    private boolean isAlive; // staus of the dragons if ther are alive or dead

    // -----------------------Constructor-----------------------//
    /*
     * Dragon1(alive) [HEAD,WING,TAIL,HEART]
     */
    public Dragon(int[] hitValues, int score) {
        health = new int[Config.MAX_NUM_DRAGON_REGIONS];
        for (int i = 0; i < health.length; i++) {
            health[i] = hitValues[i];
        }
        isAlive = true;
        this.score = score;
        id++;
    }

    // -----------------------Methods-----------------------//
    @Override
    public boolean isAlive() { // check wheather a certain dragon is alive or not
        return false;
    }

    @Override
    public boolean attack(int value, HitRegionsOfDragons region) {
        // Already creature to attack is chosen
        return false;
    }

    @Override
    public int getScore() { // getter for score of the dragon
        return score;
    }

    public int getId() { // getter for the dragon number
        return id;
    }
}
