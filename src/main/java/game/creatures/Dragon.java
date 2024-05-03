package game.creatures;



public class Dragon extends Creature {
    // -----------------------Attributes-----------------------//
    private int dragonNumber; // number of dragon
    private int[] health; // [HEAD,WING,TAIL,HEART]
    private int score; // score of the dragon
    private boolean isAlive; // status of the dragons if they are alive or dead

    // -----------------------Constructor-----------------------//

    public Dragon(int[] hitValues, int score,int dragonNumber) {
//        health = new int[Config.MAX_NUM_DRAGON_REGIONS];
//        for (int i = 0; i < health.length; i++) {
//            health[i] = hitValues[i];
//        }
//        isAlive = true;
//        this.score = score;
//        this.dragonNumber=dragonNumber;

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

    public int getDragonNumber() { // getter for the dragon number
        return dragonNumber;
    }
    @Override
    public String toString(){
        return null;
    }
}
