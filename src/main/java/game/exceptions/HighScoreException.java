package game.exceptions;

public class HighScoreException extends CheatDetectedException {
    public HighScoreException() {

    }

    @Override
    public String getMessage() {
        return "Score cheat detected!";
    }
}
