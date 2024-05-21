package game.exceptions;

public class RewardCheatException extends CheatDetectedException {
    private String message;

    public RewardCheatException() {
    }

    public RewardCheatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
