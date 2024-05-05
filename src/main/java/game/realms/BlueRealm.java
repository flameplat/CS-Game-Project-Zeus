
package game.realms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



import game.Color;
import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.ElementalCrest;
import game.collectibles.EssenceBonus;
import game.collectibles.TimeWarp;
import game.creatures.Creature;
import game.creatures.Serpent;
import game.engine.Move;
import game.dice.*;
import java.util.LinkedList;

public class BlueRealm extends Realm{
    // -----------------------Attributes-----------------------//
    private int hitcount;
    private int noElementalCrests;
    private final int S1HeadNumber=5;
    private final int S2HeadNumber=6;
    private Serpent serpent1;
    private Serpent serpent2;
    private Move movement ;
    private LinkedList<Move> possibleMoveS2;
    private LinkedList<Move> possibleMoveS1;
    private Collectibles[] rewardProperties;
    private static final String name="Tide Abyss";
    private static final Color realmColor=Color.BLUE;
    private int[] score;
    private String[] attackValues;
    private String[] rewardValues;

    //-----------------------Constructor-----------------------//
    public BlueRealm() {
        attackValues=new String[11];
        rewardValues=new String[]{"     ","     ","     ","AB   ","     ","GB   ","EC   ","     ","MB   ","TW   ","     "};
        for(int i=0;i<11;i++){
            attackValues[i]="---";
        }
        serpent1 =new Serpent(1,5);
        serpent2=new Serpent(2,6);
        rewardProperties=getRewardsProperties("src/main/resources/config/TideAbyssRewards.properties");
        possibleMoveS1=new LinkedList<>();
        possibleMoveS2=new LinkedList<>();
        for(int i=1;i<7;i++){
            possibleMoveS1.addLast(new Move(new BlueDice(i),serpent1));
            possibleMoveS2.addLast(new Move(new BlueDice(i),serpent2));
        }

        this.score=new int[11];
    }
    // -----------------------Methods-----------------------//
    @Override
    public boolean attack(Move move) {
        if(isRealmAvailable()){
            if(hitcount<5 && ((Serpent)move.getCreature()).getSerpentNumber()==serpent1.getSerpentNumber()){
                for(Move possibleMove:possibleMoveS1){
                    if(possibleMove.getDice().getValue()==move.getDice().getValue()){
                        hitcount++;
                        score[hitcount-1]=calculateScore(hitcount);
                        attackValues[hitcount-1]=move.getDice().getValue() +"  ";
                        possibleMoveS1.removeFirst();
                        serpent1.attack();
                        return true;
                    }
                }

            }
            if(hitcount>=5 && ((Serpent)move.getCreature()).getSerpentNumber()==serpent2.getSerpentNumber()){
                for(Move possibleMove:possibleMoveS2){
                    if(possibleMove.getDice().getValue()==move.getDice().getValue()){
                        hitcount++;
                        score[hitcount-1]=calculateScore(hitcount);
                        possibleMoveS2.removeFirst();
                        serpent2.attack();
                        return true;
                    }
                }

            }
        }
        return false;
    }
    private int calculateScore(int hitCount){
        int sum=0;
        for(int i=1;i<=hitCount;i++){
            sum+=i;
        }
        return sum;
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
        return score[hitcount-1];
    }

    @Override
    public Creature getCreature(Dice dice) {
        if(dice.getRealm()==Color.GREEN && (dice.getValue()<=6 &&dice.getValue()>=1)){
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
        String string=String.format("Tide Abyss: Hydra Serpents (BLUE REALM):\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  #  |H11  |H12  |H13  |H14  |H15  |H21  |H22  |H23  |H24  |H25  |H26  |\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  H  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |\n" +
                "|  C  |≥1   |≥2   |≥3   |≥4   |≥5   |≥1   |≥2   |≥3   |≥4   |≥5   |≥6   |\n" +
                "|  R  |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  S  |1    |3    |6    |10   |15   |21   |28   |36   |45   |55   |66   |\n" +
                "+-----------------------------------------------------------------------+\n\n",
                attackValues[0],attackValues[1],attackValues[2],attackValues[3],attackValues[4],attackValues[5],attackValues[6],attackValues[7],attackValues[8],attackValues[9],attackValues[10],
                rewardValues[0],rewardValues[1],rewardValues[2],rewardValues[3],rewardValues[4],rewardValues[5],rewardValues[6],rewardValues[7],rewardValues[8],rewardValues[9],rewardValues[10]);
        return string;
    }

    @Override
    public Move[] getRealmMoves() {
        if(hitcount<5){
            return possibleMoveS1.toArray(Move[]::new);
        }
        return possibleMoveS2.toArray(Move[]::new);
    }

    @Override
    public Collectibles getReward() {
        return rewardProperties[hitcount-1];
    }

    @Override
    public boolean checkReward() {
        if(rewardProperties[hitcount-1]!=null){
            if(rewardProperties[hitcount-1] instanceof ElementalCrest){
                noElementalCrests++;
            }
            rewardValues[hitcount-1]="X";
            return true;
        }else{
            return false;
        }

    }





    private Collectibles[] getRewardsProperties(String path) {
        Properties properties = new Properties();
        Collectibles []rewardProperties=new Collectibles[11] ;
        try{
            FileInputStream fileInputStream=new FileInputStream(path);
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println("File Not Found");
        }
        for (int i = 0; i < 11; i++) {

            String reward = properties.getProperty("hit"+(i+1)+"Reward");
            if(reward!=null){
                switch (reward){
                    case "TimeWarp":rewardProperties[i]=new TimeWarp();break;
                    case "ArcaneBoost":rewardProperties[i]=new ArcaneBoost();break;
                    case "EssenceBonus":rewardProperties[i]=new EssenceBonus();break;
                    case "RedBonus":rewardProperties[i]=new ColorBonus(Color.RED);break;
                    case "BlueBonus":rewardProperties[i]=new ColorBonus(Color.BLUE);break;
                    case "GreenBonus":rewardProperties[i]=new ColorBonus(Color.GREEN);break;
                    case "MagentaBonus":rewardProperties[i]=new ColorBonus(Color.MAGENTA);break;
                    case "YellowBonus":rewardProperties[i]=new ColorBonus(Color.YELLOW);break;
                    case "ElementalCrest":rewardProperties[i]=new ElementalCrest();break;
                    default:rewardProperties[i]=null;
                }
            }

        }
        return rewardProperties;
    }



}
