package game.engine;
import game.Realms.Realm;
import game.creatures.*;

import javax.swing.plaf.synth.Region;

public class Move {
    private Realm realm;
    private Creature creature;

    private HitRegions hitRegion;
    public Move(Realm realm,Creature creature){
        this.realm=realm;
        this.creature=creature;
        hitRegion=null;

    }
    public Move(Realm realm,Creature creature,HitRegions hitRegion){
        this(realm,creature);
        this.hitRegion=hitRegion;
    }
    public Realm getRealm() {
        return realm;
    }

    public Creature getCreature() {
        return creature;
    }
    public HitRegions getHitRegion(){
        return hitRegion;
    }

}
