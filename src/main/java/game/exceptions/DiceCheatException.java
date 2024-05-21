package game.exceptions;

public class DiceCheatException extends CheatDetectedException {
    public DiceCheatException() {

    }

    @Override
    public String getMessage() {
        return "Dice cheat detected!";
    }
}
