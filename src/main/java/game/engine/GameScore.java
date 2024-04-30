package game.engine;

import game.realms.Realm;

public class GameScore {
    //--------------------------Attributes--------------------------//
    // all of the scores for the Realms
    private int totalElementalCrests;
    private int yellowRealmScore;
    private int greenRealmScore;
    private int redRealmScore;
    private int magentaRealmScore;
    private int blueRealmScore;
    private int totalScore;
    private Realm[] realms;

    //--------------------------Constructor--------------------------//
    public GameScore(Realm[] realms) {
        this.realms=realms;
        totalElementalCrests = 0;
        totalScore = 0;
    }
    //--------------------------Methods--------------------------//


    public void updateGameScore(){
        //Reset attributes to recalculate them
        totalScore=0;
        totalElementalCrests=0;
        for (int i = 0; i < realms.length; i++) {
            totalScore += realms[i].getTotalScore();
            totalElementalCrests+=realms[i].getNoElementalCrests();
        }
    }

    public int getYellowRealmScore() {
        return yellowRealmScore;
    }

    public int getGreenRealmScore() {
        return greenRealmScore;
    }

    public int getRedRealmScore() {
        return redRealmScore;
    }

    public int getMagentaRealmScore() {
        return magentaRealmScore;
    }

    public int getBlueRealmScore() {
        return blueRealmScore;
    }

    public void displayGameScore() {

    }

    public int getTotalElementalCrests() {
        return totalElementalCrests;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public String toString(){
        return null;
    }
}
