package game.engine;
import java.util.Arrays;

import game.utilities.GameColor;
import game.dice.*;
import game.gui.GUIGameController;
import game.realms.*;

public class Ai extends Player{
    private GUIGameController guiGameController;
    //WeightOfMove
    public double getWeightOfMove(Move move){
        double weight = 0;
        double scoreFraction;
        double colorBonusFraction1; 
        double colorBonusFraction2;
        double ECFraction;
        double ACFraction;
        GameColor realm = move.getDice().getRealm();
        if(realm == GameColor.RED ){
            
        }
        if(realm == GameColor.GREEN){

        }
        if(realm == GameColor.BLUE){
        
        }
        if(realm == GameColor.MAGENTA){
        
        }
        if(realm == GameColor.YELLOW){
        
        }
    }
    public double getWeightOfDice(Dice die){
        double selectedWeight=0;
        double tempWeight;
        Move[] possibleMoves = guiGameController.getPossibleMovesForADie(this, die);
        for(int i=0; i<possibleMoves.length;i++){
            tempWeight = getWeightOfMove(possibleMoves[i]);
            if(selectedWeight<tempWeight){
                selectedWeight = tempWeight;
            }
        }
        return selectedWeight*getTurnWeight(die);
    }
    private double getTurnWeight(Dice selecteddDice) {
        int[] arrayOfAvailableDice = new int[guiGameController.getAvailableDice().length];
        for(int i=0; i<guiGameController.getAvailableDice().length;i++){
            arrayOfAvailableDice[i] = guiGameController.getAvailableDice()[i].getValue();
        }
        Arrays.sort(arrayOfAvailableDice);
        double value;
        int Turn= guiGameController.gameStatus.getTurn();
        CurrentStatus status = guiGameController.gameStatus.getGameStatus();
        if(status == CurrentStatus.ARCANE_BOOST || status == CurrentStatus.PASSIVE_TURN || Turn == 3){
            value = 1;
        } 
        else{// math bitch
            if(arrayOfAvailableDice.length == 6){
                value = 1-(0.15*(findIndex(arrayOfAvailableDice,selecteddDice.getValue())));
              }
            if(arrayOfAvailableDice.length == 5){
              value = 1-(0.2*(findIndex(arrayOfAvailableDice,selecteddDice.getValue())));
            }
            if(arrayOfAvailableDice.length == 4){
                value = 1-(0.25*(findIndex(arrayOfAvailableDice,selecteddDice.getValue())));
              }
            if(arrayOfAvailableDice.length == 3){
                value = 1-(0.35*(findIndex(arrayOfAvailableDice,selecteddDice.getValue())));
              }
            if(arrayOfAvailableDice.length == 2){
                value = 1-(0.5*(findIndex(arrayOfAvailableDice,selecteddDice.getValue())));
              }
            else
                value = 1;
        }
        return value;
    }

    //helper method used in getTurnWeight method
    private static int findIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public double[] getWeightOfAllDice(){
        double [] weights = new double[guiGameController.filterDiceWithPossibleMoves(this,diceArray).toArray().length];
        for(int i=0;i<weights.length;i++){
           weights[i] = getWeightOfDice((Dice)guiGameController.filterDiceWithPossibleMoves(this,diceArray).toArray()[i]);
        }
        return weights;
    }
    //GetEC
    public int getEC(){
        
    }
    //GetColorBonus
    //GetAC
    public int getAC(){
        return 12;
    }
    //GetScore

}
