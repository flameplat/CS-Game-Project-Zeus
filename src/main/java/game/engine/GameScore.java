package game.engine;

import game.Color;
import game.Config;
import game.Realms.Realm;
import java.util.LinkedList;

public class GameScore {
    //--------------------------Attributes--------------------------//
    // all of the scores for the Realms
    private int totalElementalCrests;
    private int totalScore;
    private Realm[] realms;

    //--------------------------Constructor--------------------------//
    public GameScore(Realm[] realms) {
        for (int i = 0; i < realms.length; i++) {
            this.realms=realms;
        }
        totalElementalCrests = 0;
        totalScore = 0;
    }
    //--------------------------Methods--------------------------//
    private void updateTotalScore() {

    }

    public void updateGameStatus(){
        //Reset attributes to recalculate them
        totalScore=0;
        totalElementalCrests=0;
        for (int i = 0; i < realms.length; i++) {
            totalScore += realms[i].getTotalScore();
            totalElementalCrests+=realms[i].getNoElementalCrests();
        }
    }

    public void displayGameScore() {

    }
    @Override
    public String toString(){
        return null;
    }
}
