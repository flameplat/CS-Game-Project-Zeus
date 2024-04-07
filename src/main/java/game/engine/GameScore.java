package game.engine;

import game.Color;

import java.util.LinkedList;

public class GameScore {
    //Sum of score for every Realm
    private int noElementalCrests;
    private int totalScore;
    private RealmScore realmScore;

    /**
     * Inner class to represent the scores for each realm.
     */
    public static class RealmScore {

        private final Color realm;
        private int score;

        public RealmScore(Color realm, int score) {
            this.realm = realm;
            this.score = score;
        }

        public Color getRealm() {
            return realm;
        }

        public int getScore() {
            return score;
        }
    }
    public GameScore(){
        noElementalCrests=0;
        totalScore=0;
    }
/**
 *
 * Current score of the game, including scores in each realm, number of
 * elemental crests, and the total score for the current active player.
 *  The current {GameScore} object.
 */

}
