package game.engine;

import game.Color;
import game.Config;
import game.Realms.Realm;
import java.util.LinkedList;

public class GameScore {
    //Sum of score for every Realm
    private int noElementalCrests;
    private int totalScore;
    private RealmxScore[] realmxScores;

    /**
     * Inner class to represent the scores for each realm.
     */
    public class RealmxScore {

        private final Realm realm;
        private int score;
        /*  RED,GREEN,BLUE,MAGENTA,YELLOW   TOTAL
            5     6     7     4      3        65
         */
        private RealmxScore(Realm realm, int score) {
            this.realm = realm;
            this.score = score;
        }

    }
    public GameScore(Realm[] realms){
        for(int i=0;i<realmxScores.length;i++){
            realmxScores[i]=new RealmxScore(realms[i],0);
        }
        noElementalCrests=0;
        totalScore=0;
    }
    private void updateTotalScore(){
        for(int i=0;i<realmxScores.length;i++){
            totalScore+=realmxScores[i].score;
        }
    }
    public void updateGameScore(Realm realm,int score){

        //Update total score, no Elemental Crests and score for each realm
        for(int i=0;i<realmxScores.length;i++){
            if(realmxScores[i].realm==realm){
                realmxScores[i].score=score;
                break;
            }
        }
        updateTotalScore();

    }
    public void displayGameScore(){

    }
/**
 *
 * Current score of the game, including scores in each realm, number of
 * elemental crests, and the total score for the current active player.
 *  The current {GameScore} object.
 */

}
