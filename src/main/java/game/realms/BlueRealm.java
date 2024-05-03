
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
    // -----------------------Attributes-----------------------//
    private int totalRealmScore;
    private int attackNumber;
    private int noElementalCrests;
    private Serpent[] serpent1;
    private Serpent[] serpent2;
    private Move movement = ;
    private Collectibles reward;
    private static final String name="Tide Abyss";
    private Properties rewardProperties;
    private Properties realmProperties;
    private static final Color realmColor=Color.BLUE;

    // -----------------------Constructor-----------------------//
    public BlueRealm() {
        serpent1 =new Serpent [5];

        for(int i=1; i<5;i++){
            serpent1[i] = new Serpent (i,true);
            if(serpent1[i].getHeadNumber()==4) {
                    serpent1[i].setReward("arcane boost");
                    break;
            }

        serpent2 =new Serpent [6];

        for(int j=1; j<6;j++){
            serpent2[j] = new Serpent (j,true);
            if(serpent2[j].getHeadNumber()==1) {
                serpent2[j].setReward("green bonus");
            }
            if(serpent2[j].getHeadNumber()==2) {
                serpent2[j].setReward("Elmental Crest");
            }
            if(serpent2[j].getHeadNumber()==4) {
                serpent2[j].setReward("Magenta bonus"); 
            }
            if(serpent2[j].getHeadNumber()==5) {
                serpent2[j].setReward("time Warp");
            }   
   
        }
    
    
    // -----------------------Methods-----------------------//
    @Override
    public String getName() {
        return name;
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
        movement=move;
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
        return getNextPossibleDiceValue(movement);
    }
    public Move[] getNextPossibleDiceValue(Move move){
        LinkedList <Move> moves =new LinkedList<>();
        /*Move move1= new Move (new BlueDice(1),new Serpent());
        Move move2= new Move (new BlueDice(2),new Serpent());
        Move move3= new Move (new BlueDice(3),new Serpent());
        Move move4= new Move (new BlueDice(4),new Serpent());
        Move move5= new Move (new BlueDice(5),new Serpent());
        Move move6= new Move (new BlueDice(6),new Serpent());*/
        if(serpent1[5].isAlive()==true){

           for(int i=0;serpent1[i].getHeadNumber()<5;i++){
                if (serpent1[i].isAlive())
                int j=serpent1[i].getHeadNumber();
                    while(j<=6){
                        Move move =new Move((new BlueDice(j),new Serpent()));
                        moves.addFirst(move);
                        j++;
                        
                    }
            }
                
                
            }
        }
    };
    @Override
    public Creature getCreature(Dice dice) {
        return null;
    }


}
