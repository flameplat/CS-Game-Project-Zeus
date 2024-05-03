
package game.realms;

import java.util.Properties;

import org.drools.core.util.LinkedList;

import game.Color;
import game.collectibles.Collectibles;
import game.creatures.Creature;
import game.creatures.Dragon;
import game.creatures.Serpent;
import game.engine.Move;
import game.dice.*;

public class BlueRealm extends Realm{
    private int totalRealmScore;
    private Serpent[] serpent1;
    private Serpent[] serpent2;
    private Collectibles reward;
    private Collectibles[] rewardsarray;
    private int noElementalCrests;
    private Color realmColor;
    private static final String name="Tide Abyss";
    private Properties rewardProperties;
    private Properties realmProperties;
    
    public BlueRealm() {
        serpent1 =new Serpent [5];

        for(int i=1; i<5;i++){
            serpent1[i] = new Serpent (i,true);
        }

        serpent2 =new Serpent [6];

         for(int j=1; j<6;j++){
            serpent2[j] = new Serpent (j,true);
        }   

    }
    
    // -----------------------Methods-----------------------//
    @Override
    public String getName() {
        return "Blue Realm";
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
        if (totalRealmScore==66/*cahnged from the properties */){
            return false;
        }else{
            return true;
        }
        
    }

    @Override
    public Collectibles getReward() {
        for(int i=1; i<5;i++){ 
            switch (serpent1[i]) {
                case 1:
                case 2:
                case 3:

                case 4:
                    System.out.println("Case 4: serpent1[i] is 4"); // This case will be executed
                    break;
                case 5:
                    System.out.println("Case 5: serpent1[i] is 5");
                    break;
                default:
                    System.out.println("Default case: unknown value");
                    break;
        }
         for(int j=1; j<6;j++){
            
        }   
        return null;
    }

    @Override
    public boolean checkReward() {
        
        return false;
    }

    @Override
    public boolean attack(Move move) {
        if (move.getMoveNumber()>=serpentstatus.getFirst()){
            
        }
        return ;
    }

    @Override
    public int getTotalScore(){
        GetTotalScore(serpent1,serpent2);
    }

    public int GetTotalScore(Serpent[] serpent1,Serpent[] serpent2) {
        int score1=0;
        int score2=0;
        for(int i=1; i<5;i++){
            if(serpent1[i].isAlive()){
                break;
             }else{
                score1=score1+serpent1[i].getScore();
             }   
        }

        for(int j=1; j<6;j++){
            if(serpent2[j].isAlive()){
                break;
             }else{
                score2=score2+serpent2[j].getScore();
             }   
            
        }   
        
        totalRealmScore=score1+score2;
        return totalRealmScore;

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
        return new Move[0];
    }

    @Override
    public Creature getCreature(Dice dice) {
        return null;
    }


}
