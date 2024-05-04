package game.realms;

import game.Color;
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
    private Move[]realmMoves;
    private static final Color realmColor=Color.YELLOW;
    private static final String name="Radiant Svanna";
    private Lion lion;
    private final int[] scoreMultiplier ={1,1,1,2,1,1,2,1,2,1,3};
    private int[] score;

    // -----------------------Constructor-----------------------//
    public YellowRealm() {
        this.totalRealmScore=0;
        this.lion=new Lion();
        this.countHits=0;
        this.noElementalCrests=0;
        this.collectibles= getRewardsProperties();
        this.realmMoves=new Move[]{
                new Move(new YellowDice(1),lion),
                new Move(new YellowDice(2),lion),
                new Move(new YellowDice(3),lion),
                new Move(new YellowDice(4),lion),
                new Move(new YellowDice(5),lion),
                new Move(new YellowDice(6),lion)};
        this.score=new int[11];
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
    public Collectibles getReward() {
        return collectibles[countHits-1];
    }

    @Override
    public boolean checkReward() {
        if(collectibles[countHits-1]!=null) {
            if(collectibles[countHits-1] instanceof ElementalCrest){
                noElementalCrests++;
            }
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
            score[countHits]=attackScore;
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
                "|  H  |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |\n" +
                "|  M  |     |     |     |x2   |     |     |x2   |     |x2   |     |x3   |\n" +
                "|  R  |     |     |TW   |     |RB   |AB   |     |EC   |     |MB   |     |\n" +
                "+-----------------------------------------------------------------------+\n\n",
                score[0],score[1],score[2],score[3],score[4],score[5],score[6],score[7],score[8],score[9],score[10]);
        return string;
    }

    @Override
    public Move[] getRealmMoves() {
        if(isRealmAvailable()){
            return realmMoves;
        }
        return new Move[0];

    }

    @Override
    public Creature getCreature(Dice dice) {
        return lion;
    }


}
