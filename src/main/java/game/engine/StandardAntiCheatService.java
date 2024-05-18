package game.engine;

import game.collectibles.Collectibles;
import game.dice.Dice;
import game.exceptions.*;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;
import game.utilities.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StandardAntiCheatService implements AntiCheatService{
    private final Map<Player,Map<String,Integer>> previousCollectibles;
    private final Map<Player, Integer> previousScores;
    private final Dice[] previousDice;
    public StandardAntiCheatService(){
        this.previousScores=new HashMap<>();
        this.previousCollectibles=new HashMap<>();
        this.previousDice=new Dice[6];
    }
    @Override
    public void checkPlayerScore(Player player) throws CheatDetectedException {
        int currentScore=player.getGameScore().getTotalScore();
        Random r=new Random();
        int limit=r.nextInt(100)+100;
        if(previousScores.containsKey(player)){
            if((currentScore-previousScores.get(player))<0){
                throw new NegativeScoreException();
            }
            if((currentScore-previousScores.get(player))>limit){
                throw new HighScoreException();
            }
        }
        previousScores.put(player,currentScore);
    }

    @Override
    public void checkGameStatus(GameStatus gameStatus) {

    }

    @Override
    public void checkDice(Dice[] dice) throws DiceCheatException {
        int c=0;
        for(Dice die:dice){
            if((die.getRealm()!= Color.values()[c++]) || !(die.getValue()>0 && die.getValue()<7)){
                System.out.println(Arrays.toString(dice));
                throw new DiceCheatException();
            }
        }
        for(int i=0;i< dice.length;i++){
            this.previousDice[i]=Dice.getNewDice(dice[i].getRealm(),dice[i].getValue());
        }


    }

    @Override
    public void checkPlayerReward(Player player) throws RewardCheatException {
        if(player.getTotalArcaneBoostPowersCollected()> Collectibles.getCounter("ArcaneBoost")/2
        || player.getTotalTimeWarpPowersCollected()> Collectibles.getCounter("TimeWarp")/2){
            throw new RewardCheatException();
        }
        if(previousCollectibles.containsKey(player) && previousCollectibles.get(player).containsKey("TimeWarp") && previousCollectibles.get(player).containsKey("ArcaneBoost")){
            if(previousCollectibles.get(player).get("TimeWarp")>(Collectibles.getCounter("TimeWarp")/2+1)
            || previousCollectibles.get(player).get("ArcaneBoost")>(Collectibles.getCounter("ArcaneBoost")/2+1)){
                throw new RewardCheatException();
            }
        }
        previousCollectibles.put(player,previousCollectibles.getOrDefault(player,new HashMap<>()));
    }
    @Override
    public void checkPlayerFinalScore(Player player) throws InvalidFinalScoreCheat {
        CLIGameController controller=new CLIGameController();
        Player player1=controller.getActivePlayer();
        RedRealm redRealm= (RedRealm) player1.getRealms()[0];
        GreenRealm greenRealm= (GreenRealm) player1.getRealms()[1];
        Move[] redMoves=redRealm.getRealmMoves();
        for(Move move:redMoves){
            redRealm.attack(move);
            if(redRealm.checkReward()){
                redRealm.getReward();
            }
        }
        Move[] greenMoves=greenRealm.getRealmMoves();
        for(Move move:greenMoves){
            greenRealm.attack(move);
            if(greenRealm.checkReward()){
                greenRealm.getReward();
            }
        }
        for(int i=2;i<5;i++){
            Realm realm=player1.getRealms()[i];
            while (realm.isRealmAvailable()){
                Move m=realm.getRealmMoves()[realm.getRealmMoves().length-1];
                realm.attack(m);
                if(realm.checkReward()){
                    realm.getReward();
                }
            }
        }
        int finalScore=player1.getGameScore().getFinalScore();
        if(player.getGameScore().getFinalScore()>finalScore){
            throw new InvalidFinalScoreCheat();
        }
    }

    @Override
    public void handlePlayerScore(Player player) {
        //Resets player score to previous score
        if(previousScores.containsKey(player)){
            player.getGameScore().setTotalScore(previousScores.get(player));
        }
    }

    @Override
    public void handleDiceCheat(Dice[] dice) {
        System.arraycopy(previousDice, 0, dice, 0, dice.length);
    }
    @Override
    public void handleRewardCheat(Player player){
        player.resetRewards();
    }
}
