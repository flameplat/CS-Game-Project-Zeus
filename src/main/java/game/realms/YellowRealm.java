package game.realms;

import game.Color;
import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.TimeWarp;
import game.creatures.Creature;
import game.creatures.Lion;
import game.engine.Move;
import game.dice.*;

public class YellowRealm extends Realm{
    // -----------------------Attributes-----------------------//

    private static final Color realmColor=Color.YELLOW;

    // -----------------------Constructor-----------------------//


    public YellowRealm() {


    }
    // -----------------------Methods-----------------------//
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Color getColor() {
        return realmColor;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public boolean isRealmAvailable() {
        return false;
    }

    @Override
    public Collectibles getReward() {
        return null;
    }

    @Override
    public boolean checkReward() {
        return false;
    }

    @Override
    public boolean attack(Move move) {
        return false;
    }

    @Override
    public int getTotalScore() {
        return 0;
    }

    @Override
    public int getNoElementalCrests() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Move[] getRealmMoves() {


    }

    @Override
    public Creature getCreature(Dice dice) {
        return null;
    }


}
