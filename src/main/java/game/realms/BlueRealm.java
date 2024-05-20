
package game.realms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Properties;



import game.utilities.Color;
import game.collectibles.Collectibles;
import game.collectibles.ElementalCrest;

import game.creatures.Creature;
import game.creatures.Serpent;
import game.engine.Move;
import game.dice.*;

public class BlueRealm extends Realm{
    // -----------------------Attributes-----------------------//
    private int hitcount;
    private int noElementalCrests;
    private final Serpent serpent1;
    private final Serpent serpent2;
    private final PriorityQueue<Move> possibleMoveS2;
    private final PriorityQueue<Move> possibleMoveS1;
    private final Collectibles[] rewardProperties;
    private static final String name="\u001B[34m"+"Blue Realm"+"\u001B[0m";
    private static final Color realmColor=Color.BLUE;
    private int[] score;
    private final String[] attackValues;
    private final String[] rewardValues;

    //-----------------------Constructor-----------------------//
    public BlueRealm() {
        this.score=new int[11];
        attackValues=new String[11];
        rewardValues=new String[11];
        for(int i=0;i<11;i++){
            attackValues[i]="---";
        }
        serpent1 =new Serpent(1,5);
        serpent2=new Serpent(2,6);
        rewardProperties=getRewardsProperties();
        possibleMoveS1=new PriorityQueue<>();
        possibleMoveS2=new PriorityQueue<>();
        for(int i=1;i<7;i++){
            possibleMoveS1.add(new Move(new BlueDice(i),serpent1));
            possibleMoveS2.add(new Move(new BlueDice(i),serpent2));
        }

        hitcount=0;
    }
    // -----------------------Methods-----------------------//
    @Override
    public boolean attack(Move move) {
        if(isRealmAvailable()){
            if(hitcount<5 && ((Serpent)move.getCreature()).getSerpentNumber()==serpent1.getSerpentNumber()){
                if(possibleMoveS1.contains(move)){
                    hitcount++;
                    attackValues[hitcount-1]=move.getDice().getValue() +"  ";
                    possibleMoveS1.remove();
                    serpent1.attack();
                    return true;
                }

            }
            if(hitcount>=5 && ((Serpent)move.getCreature()).getSerpentNumber()==serpent2.getSerpentNumber()){
                if(possibleMoveS2.contains(move)){
                    hitcount++;
                    attackValues[hitcount-1]=move.getDice().getValue() +"  ";
                    possibleMoveS2.remove();
                    serpent2.attack();
                    return true;
                }

            }

        }
        return false;
    }
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
        return (serpent1.isAlive() || serpent2.isAlive() || hitcount<11);

    }
    @Override
    public int getTotalScore(){
        if(hitcount==0){
            return 0;
        }
        return score[hitcount-1];
    }

    @Override
    public Creature getCreature(Dice dice) {
        if(dice.getRealm()==Color.BLUE && (dice.getValue()<=6 &&dice.getValue()>=1)){
            if(hitcount<5){
                return serpent1;
            }
            else{
                return serpent2;
            }

        }
        return null;

    }

    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    @Override
    public String toString() {
        return String.format("Tide Abyss: Hydra Serpents (BLUE REALM):\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  #  |H11  |H12  |H13  |H14  |H15  |H21  |H22  |H23  |H24  |H25  |H26  |\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  H  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |\n" +
                "|  C  |≥1   |≥2   |≥3   |≥4   |≥5   |≥1   |≥2   |≥3   |≥4   |≥5   |≥6   |\n" +
                "|  R  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  S  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                "+-----------------------------------------------------------------------+\n\n\n",
                attackValues[0],attackValues[1],attackValues[2],attackValues[3],attackValues[4],attackValues[5],attackValues[6],attackValues[7],attackValues[8],attackValues[9],attackValues[10],
                rewardValues[0],rewardValues[1],rewardValues[2],rewardValues[3],rewardValues[4],rewardValues[5],rewardValues[6],rewardValues[7],rewardValues[8],rewardValues[9],rewardValues[10],
                score[0]<10?score[0]+" ":score[0],score[1]<10?score[1]+" ":score[1],score[2]<10?score[2]+" ":score[2],score[3]<10?score[3]+" ":score[3],score[4]<10?score[4]+" ":score[4],score[5]<10?score[5]+" ":score[5],score[6]<10?score[6]+" ":score[6],score[7]<10?score[7]+" ":score[7],score[8]<10?score[8]+" ":score[8],score[9]<10?score[9]+" ":score[9],score[10]<10?score[10]+" ":score[10]);
    }

    @Override
    public Move[] getRealmMoves() {
        if(hitcount<5){
            return possibleMoveS1.toArray(Move[]::new);
        }
        return possibleMoveS2.toArray(Move[]::new);
    }

    @Override
    public Collectibles[] getReward() {
        Collectibles[] temp=new Collectibles[1];
        temp[0]=rewardProperties[hitcount-1];
        return temp;
    }

    @Override
    public boolean checkReward() {
        if(rewardProperties[hitcount-1]!=null){
            if(rewardProperties[hitcount-1] instanceof ElementalCrest){
                noElementalCrests++;
            }
            rewardValues[hitcount-1]="X ";
            return true;
        }else{
            return false;
        }

    }





    private Collectibles[] getRewardsProperties() {
        Properties serpentsScoreProperties=new Properties();
        Properties properties = new Properties();
        Collectibles []rewardProperties=new Collectibles[11] ;
        try{
            FileInputStream fileInputStream=new FileInputStream("src/main/resources/config/TideAbyssRewards.properties");
            properties.load(fileInputStream);
            FileInputStream fileInputStream2=new FileInputStream("src/main/resources/config/TideAbyssScores.properties");
            serpentsScoreProperties.load(fileInputStream2);
            fileInputStream2.close();
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println("File Not Found");
        }
        try{
            for(int i=0;i<score.length;i++){
                score[i]=Integer.parseInt(serpentsScoreProperties.getProperty("hit"+(i+1)+"Score"));
            }
        }
        catch (NumberFormatException e){
            score=new int[]{1,3,6,10,15,21,28,36,45,55,66};
        }

        for (int i = 0; i < 11; i++) {

            String reward = properties.getProperty("hit"+(i+1)+"Reward");
            rewardProperties[i]=Collectibles.getCollectibleFromString(reward);
            if(rewardProperties[i]==null){
                rewardValues[i]="  ";
            }
            else{
                rewardValues[i]=rewardProperties[i].toString();
            }

        }
        return rewardProperties;
    }



}
