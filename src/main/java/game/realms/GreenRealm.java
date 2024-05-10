package game.realms;

import game.Color;
import game.collectibles.*;
import game.creatures.Creature;
import game.creatures.Guardian;
import game.engine.*;
import game.dice.*;
public class GreenRealm extends Realm{
    // -----------------------Attributes-----------------------//
    private static final Color realmColor=Color.GREEN;
    private static final String name="Terras Heartland";
    private Move[] realmMoves;
    private Move[] availableRealmMoves;
    private Collectibles[] collectibles;
    private String[] attackValues;
    private String[] rewardValues;
    private Guardian guardian;
    private int realmScore;
    private int deadGuardians;
    private int noElementalCrests;
    



    // -----------------------Constructor-----------------------//
    public GreenRealm(){
        this.deadGuardians = 0;
        this.realmScore = 0;
        this.noElementalCrests = 0;
        this.attackValues = new String[]{"X","2","3","4","5","6","7","8","9","10","11"};
        this.rewardValues = new String[6];
        this.realmMoves = new Move[10];
        for(i=2;i<13;i++){
            realmMoves[i] = new Move(new GreenDice(i),guardian);
        }
        this.availableRealmMoves = realmMoves;
        this.collectibles = getRewardsProperties();


    }
    // -----------------------Methods-----------------------//
    public Move[] getAvialableRealmMoves(){
        return availableRealmMoves;
    }
    public Collectibles[] getRewardsProperties(){
        Properties properties = new Properties();
        Collectibles []rewardProperties=new Collectibles[6] ;
        try{
            FileInputStream fileInputStream=new FileInputStream("src/main/resources/config/TerrasHeartlandRewards.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println("File Not Found");
        }
        for (int i = 0; i <3; i++) {
            String rowReward = properties.getProperty("row"+(i+1)+"Reward");
            rewardProperties[i]=getCollectibleFromString(rowReward);
            if(rewardProperties[i]==null){
                System.out.println("Error in reading the file");
                System.exit(1);
            }
            rewardValues[i] = rewardProperties[i].toString();
        }
        for (int i=3;i<7;i++){
            String columnReward = properties.getProperty("column"+(i+1)+"Reward");
            rewardProperties[i] = getCollectibleFromString(columnReward);
            if(rewardProperties[i]==null){
                System.out.println("Error in reading the file");
                System.exit(1);
            }
            rewardValues[i] = rewardProperties[i].toString();
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
        if(deadGuardians<11)
            return true;
        else
            return false;
    }

    @Override
    public Collectibles[] getReward() {
        Collectibles[] earnedRewards = new Collectibles[2];
        if(checkRowReward()){
            earnedRewards[0] = getRowReward();
        }
        if(checkColumnReward()){
            earnedRewards[1] = getColumnReward();
        }
        return earnedRewards;
        
    }
    public Collectibles getRowReward(){
        Collectibles[] tmp = new Collectibles[1];
        //checks rewards in rows(c count position of column, l loop position of row)
        for(c=0,l=0;l<3;c++){
            if(attackValues[c+l*4] != "X"){
                l++;c=0;
            }
            if(c == 3){
                if(rewardValues[l]!= "X"){
                    tmp[0] = collectibles[l];
                    rewardValues[l] = "X";
                    return tmp[0];
                }
                else{
                    c=0;l++;
                }

            }
        }
    }
    public Collectibles getColumnReward(){
        Collectibles[] tmp = new Collectibles[1];
        //checks rewards in columns(r count position of row, l loop position of column)
        for(r=0, l=0;l<4;r++){
            if(attackValues[r*4+l] != "X"){
                l++;r=0;
            }
            if(r == 2){
                if(rewardValues[l+3] != "X"){
                    tmp[0] = collectibles[l+3];
                    rewardValues[l+3] = "X";
                    return tmp[0];
                }
                else{
                    r=0;l++;
                }
            }
        }
    }
    @Override
    public boolean checkReward() {
        return checkRowReward() || checkColumnReward();
    }
    public boolean checkRowReward(){
        //checks rewards in rows(c count position of column, l loop position of row)
        for(c=0,l=0;l<3;c++){
            if(attackValues[c+l*4] != "X"){
                l++;c=0;
            }
            if(c == 3){
                if(rewardValues[l]!= "X"){
                    if(rewardProperties[l]  instanceof ElementalCrest){
                        noElementalCrests++;
                    }
                    return true;
                }
                else{
                    c=0;l++;
                }

            }
        }
        return false;
    }
    public boolean checkColumnReward(){
        //checks rewards in columns(r count position of row, l loop position of column)
        for(r=0, l=0;l<4;r++){
            if(attackValues[r*4+l] != "X"){
                l++;r=0;
            }
            if(r == 2){
                if(rewardValues[l+3] != "X"){
                    if(rewardProperties[l+3]  instanceof ElementalCrest){
                        noElementalCrests++;
                    }
                    return true;
                }
                else{
                    r=0;l++;
                }
            }
        }
        return false;
    }

    @Override
    public boolean attack(Move move) {
        if(isRealmAvailable()){
            if(move.getDice().getRealm()== Color.Green || move.getDice().getRealm()== Color.White){
                int sumOfValues = CLIGameController.getAllDice()[1].getValue() + CLIGameController.getAllDice()[5].getValue();
                for(Move availableRealmMove : availableRealmMoves){
                    if(move!= null && sumOfValues == availableRealmMove.getDice().getValue()){
                       availableRealmMove.getCreature().attack();
                       deadGuardians++;
                       realmScore += deadGuardians;
                       updateAvailableMoves(move);
                       return true;
                    }
                }
                return false;
            }

            }
            return false;
        }

    public void updateAvailableMoves(Move move){
        Move[] tmp = Move[availableRealmMoves.length()-1];
        for(i=0,k=0;i<availableRealmMoves.length();i++){
            if(availableRealmMoves[i] != move){
                tmp[k] = availableRealmMoves[i];
                k++;
            }
            if(availableRealmMoves[i] == move){
                for(j=1;j<attackValues.length();j++){
                    if(parseInt(attackValues[j]) == availableRealmMoves[i].getDice().getValue()){
                        attackValues[j] = "X";
                    }
                }
            }
        }
        availableRealmMoves = tmp;
    }

    @Override
    public int getTotalScore() {
        return realmScore;
    }

    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    @Override
    public String toString() {
        return  String.format("Terras Heartland: (Green Realm):\n" +
                "+-----------------------------------+\n"+
                "|  #  |1    |2    |3    |4    |R    |\n"+
                "+-----------------------------------+\n" +  
                "|  1  |%s    |%s   |%s   |%s   |%s   |\n" +
                "|  2  |%s   |%s   |%s   |%s   |%s   |\n"+
                "|  3  |%s   |%s   |%s   |%s   |%s   |\n"+
                "+-----------------------------------+\n"+
                "|  R  |%s   |%s   |%s   |%s   |     |\n"+
                "+-----------------------------------------------------------------------------+\n"+
                "|  S  |0    |1    |2    |4    |7    |11   |16   |22   |29   |37   |46   |56   |\n"+
                "+-----------------------------------------------------------------------------+\n\n",
                attackValues[0],attackValues[1],attackValues[2],attackValues[3],rewardValues[0],
                attackValues[4],attackValues[5],attackValues[6],attackValues[7],rewardValues[1],
                attackValues[8],attackValues[9],attackValues[10],attackValues[11],rewardValues[2],
                rewardValues[3],rewardValues[4],rewardValues[5],rewardValues[6]);        
    }

    @Override
    public Move[] getRealmMoves() {
        return realmMoves;
    }

    @Override
    public Creature getCreature(Dice dice) {
        if(dice.getRealm()==Color.Green){
            return guardian;
        }
        return ;
    }
}

