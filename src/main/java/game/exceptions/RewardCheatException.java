package game.exceptions;

public class RewardCheatException extends CheatDetectedException{
    public RewardCheatException() {
    }
    @Override
    public String getMessage(){
        return "Reward cheat detected!";
    }
}
