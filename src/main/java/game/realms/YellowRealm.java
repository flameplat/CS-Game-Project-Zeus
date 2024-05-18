package game.realms;

import game.utilities.Color;
import game.collectibles.Collectibles;
import game.creatures.Creature;
import game.creatures.Lion;
import game.engine.Move;
import game.dice.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import game.collectibles.*;

public class YellowRealm extends Realm{
    // -----------------------Attributes-----------------------//
    private int totalRealmScore;
    public Collectibles[] collectibles;
    private int countHits;
    private int noElementalCrests;
    private LinkedList<Move> realmMoves;
    private static final Color realmColor=Color.YELLOW;
    private static final String name="\u001B[33m"+"Yellow Realm"+"\u001B[0m";
    private Lion lion;
    private final int[] scoreMultiplier ={1,1,1,2,1,1,2,1,2,1,3};
    private Object[] score;
    private String[] rewardValues;

    // -----------------------Constructor-----------------------//
    public YellowRealm() {
        this.realmMoves=new LinkedList<>();
        this.rewardValues=new String[11];
        this.totalRealmScore=0;
        this.lion=new Lion();
        this.countHits=0;
        this.noElementalCrests=0;
        this.collectibles= getRewardsProperties();
        for(int i=1;i<7;i++){
            this.realmMoves.addLast(new Move(new YellowDice(i),lion));
        }
        this.score=new Object[11];
        for(int i=0;i<11;i++){
            score[i]=0+" ";
        }
    }


    // -----------------------Methods-----------------------//
    private Collectibles[] getRewardsProperties() {
        Properties properties = new Properties();
        Collectibles []rewardProperties=new Collectibles[11] ;
        try{
            FileInputStream fileInputStream=new FileInputStream("src/main/resources/config/RadiantSvannaRewards.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
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
        if(countHits<11)
            return true;
        return false;
    }

    @Override
    public Collectibles[] getReward() {
        Collectibles[] temp=new Collectibles[1];
        temp[0]=collectibles[countHits-1];
        return temp;
    }

    @Override
    public boolean checkReward() {
        if(collectibles[countHits-1]!=null) {
            if(collectibles[countHits-1] instanceof ElementalCrest){
                noElementalCrests++;
            }
            rewardValues[countHits-1]="X ";
            return true;
        }
        return false;

    }

    @Override
    public boolean attack(Move move) {
        if(isRealmAvailable()){
            boolean flag= false;
            for (Move realmMove : realmMoves) {
                if (move != null && move.getDice().getValue() == realmMove.getDice().getValue() && move.getDice().getRealm() == realmMove.getDice().getRealm()){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                return false;
            }
            int attackScore=move.getDice().getValue()*scoreMultiplier[countHits];
            lion.attack();
            score[countHits]=(attackScore<9)?attackScore+" ":attackScore;
            totalRealmScore=totalRealmScore+attackScore;
            countHits++;
            return true;
        }
        else {
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
        String string=String.format("Radiant Savanna: Solar Lion (YELLOW REALM):\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
                "+-----------------------------------------------------------------------+\n" +
                "|  H  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                "|  M  |     |     |     |x2   |     |     |x2   |     |x2   |     |x3   |\n" +
                "|  R  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                "+-----------------------------------------------------------------------+\n\n",
                score[0],score[1],score[2],score[3],score[4],score[5],score[6],score[7],score[8],score[9],score[10],
                rewardValues[0],rewardValues[1],rewardValues[2],rewardValues[3],rewardValues[4],rewardValues[5],rewardValues[6],rewardValues[7],rewardValues[8],rewardValues[9],rewardValues[10]);
        return string;
    }

    @Override
    public Move[] getRealmMoves() {
        if(isRealmAvailable()){
            return realmMoves.toArray(Move[]::new);
        }
        return new Move[0];

    }

    @Override
    public Creature getCreature(Dice dice) {
        if(dice.getRealm()==Color.YELLOW && (dice.getValue()<=6 &&dice.getValue()>=1)){
            return lion;
        }
        return null;
    }


}
