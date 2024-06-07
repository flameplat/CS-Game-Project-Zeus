package game.engine;

import game.collectibles.*;
import game.realms.Realm;
import game.realms.YellowRealm;
import game.utilities.GameColor;

import java.util.LinkedList;

public class MoveEvaluation {

    private Realm[] realms;
    private AIPlayer player;
    private Decision[][] redRealmDecisionGrid;
    private LinkedList<Move> pastMoves;
    private final YellowRealm yellowRealm;
    public MoveEvaluation(AIPlayer player, LinkedList<Move> pastMoves){
        Realm[] realms=player.getRealms();
        this.pastMoves=pastMoves;
        this.yellowRealm= (YellowRealm) realms[4];
        redRealmDecisionGrid = new Decision[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                redRealmDecisionGrid[i][j] = new Decision();
            }
        }
    }

    public int evaluateYellowRealmMove(Move move){
        double scoreContribution= (double) move.getDice().getValue() /6;
        double rewardContribution=1;
        double scoreEvaluation=scoreContribution*move.getDice().getValue()*yellowRealm.getScoreMultiplier()[yellowRealm.getCountHits()];
        double rewardEvalution=rewardContribution*getRewardEvaluation(yellowRealm.getCollectibles()[yellowRealm.getCountHits()]);
        int totalEvaluation = (int) (scoreEvaluation + rewardEvalution);
        return totalEvaluation;
    }
    public int getScoreWeight(Move move){
        if(){

        }
        Realm realm=player.getRealm(move.getDice());
        return realm.fakeAttack(move);
    }
    private int getRewardEvaluation(Collectibles collectible){
        if (collectible instanceof ArcaneBoost) {
            // return a value specific to ArcaneBoost
            return 15;
        } else if (collectible instanceof TimeWarp) {
            // return a value specific to TimeWarp
            return 5;
        } else if (collectible instanceof ElementalCrest) {
            // return a value specific to ElementalCrest
            return 30;
        } else if (collectible instanceof ColorBonus) {
            // return a value specific to colorBonus
            GameColor colorBonusColor = ((ColorBonus) collectible).getColor();
            switch (colorBonusColor) {
                case RED:
                    return 20;
                case BLUE:
                    return 11;
                case GREEN:
                    return 10;
                case YELLOW:
                    return 15;
                case MAGENTA:
                    return 12;
                default:
                    return 0;
            }
        }
        return 0;
    }

}
