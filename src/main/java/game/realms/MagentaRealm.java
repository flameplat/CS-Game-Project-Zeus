package game.realms;

import game.utilities.Color;
import game.collectibles.Collectibles;
import game.creatures.Creature;
import game.creatures.Phoenix;
import game.engine.Move;
import game.dice.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import game.collectibles.*;


public class MagentaRealm extends Realm{
    // -----------------------Attributes-----------------------//
    private static final Color realmColor=Color.MAGENTA;
    private final Collectibles[] collectibles;
    private int totalRealmScore;
    private int noElementalCrests;
    private static final String name="\u001B[35m"+"Magenta Realm"+"\u001B[0m";
    private final Move[]realmMoves;
    private final Phoenix phoenix;
    private int counterHits;
    private Move[]realmPossibleMoves;
    private final int[] score;
    private final String[] attackValues;
    private final String[] rewardValues;
    // -----------------------Constructor-----------------------//
    public MagentaRealm(){
        this.rewardValues=new String[11];
        this.collectibles = getRewardsProperties();
        this.attackValues=new String[11];
        for(int i=0;i<11;i++){
            attackValues[i]=" ";
        }
        this.phoenix=new Phoenix();
        totalRealmScore=0;
        this.score=new int[11];
        this.noElementalCrests=0;
        this.realmMoves=new Move[]{new Move(new MagentaDice(1),phoenix),
                new Move(new MagentaDice(2),phoenix),
                new Move(new MagentaDice(3),phoenix),
                new Move(new MagentaDice(4),phoenix),
                new Move(new MagentaDice(5),phoenix),
                new Move(new MagentaDice(6),phoenix) };
        this.realmPossibleMoves=realmMoves;
        this.counterHits=0;
    }
    // -----------------------Methods-----------------------//
    private void updatePossibleMoves(Move move){
        LinkedList<Move> list=new LinkedList<>();
        for (Move realmMove : realmMoves) {
            if ((move.getDice().getValue() % realmMoves.length) < realmMove.getDice().getValue()) {
                list.addLast(realmMove);
            }
        }
        this.realmPossibleMoves=list.toArray(Move[]::new);
    }
    private Collectibles[] getRewardsProperties() {
        Properties properties = new Properties();
        Collectibles []rewardProperties=new Collectibles[11] ;
        try{
            FileInputStream fileInputStream=new FileInputStream("src/main/resources/config/MysticalSkyRewards.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println("File Not Found");
        }
        for (int i = 0; i < 11; i++) {

            String reward = properties.getProperty("hit"+(i+1)+"Reward");
            rewardProperties[i]=Collectibles.getCollectibleFromString(reward);
            if(rewardProperties[i]!=null){
                rewardValues[i]=rewardProperties[i].toString();
            }
            else{
                rewardValues[i]="  ";
            }



        }
        return rewardProperties;
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
        return counterHits < 11;
    }

    @Override
    public Collectibles[] getReward() {
        Collectibles[] temp=new Collectibles[1];
        temp[0]=collectibles[counterHits-1];
        return temp;
    }

    @Override
    public boolean checkReward() {
        if(collectibles[counterHits-1]!=null){
            if(collectibles[counterHits-1] instanceof ElementalCrest){
                noElementalCrests++;
            }
            rewardValues[counterHits-1]="X ";

            return true;
        }
        return false;

    }

    @Override
    public boolean attack(Move move) {
        if(isRealmAvailable()){
            boolean flag=false;
            for(Move i : realmPossibleMoves){
                if(move !=null && i.getDice().getRealm()==move.getDice().getRealm() && i.getDice().getValue()==move.getDice().getValue()){
                    flag = true;
                    break;
                }

            }
            if(!flag){
                return false;
            }
            updatePossibleMoves(move);
            phoenix.attack();
            int attackScore=move.getDice().getValue();
            if(attackScore==6){
                attackValues[counterHits]=String.valueOf(0);
            }
            else{
                attackValues[counterHits]= String.valueOf(attackScore);
            }
            score[counterHits]=attackScore;
            totalRealmScore+=attackScore;
            counterHits++;
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public int getTotalScore() {
        return totalRealmScore;
    }

    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    @Override
    public String toString() {
        return String.format("Mystical Sky: Majestic Phoenix (MAGENTA REALM):\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  H  |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |\n" +
                "|  C  |<    |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |\n" +
                "|  R  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                "+-----------------------------------------------------------------------+\n\n\n",
                score[0],score[1],score[2],score[3],score[4],score[5],score[6],score[7],score[8],score[9],score[10],
                attackValues[0],attackValues[1],attackValues[2],attackValues[3],attackValues[4],attackValues[5],attackValues[6],attackValues[7],attackValues[8],attackValues[9],
                rewardValues[0],rewardValues[1],rewardValues[2],rewardValues[3],rewardValues[4],rewardValues[5],rewardValues[6],rewardValues[7],rewardValues[8],rewardValues[9],rewardValues[10]);
    }


    @Override
    public Move[] getRealmMoves() {
        return realmPossibleMoves;
    }

    @Override
    public Creature getCreature(Dice dice) {
        if(dice.getRealm()==Color.MAGENTA && (dice.getValue()<=6 &&dice.getValue()>=1)){
            return phoenix;
        }
        return null;
    }


}
