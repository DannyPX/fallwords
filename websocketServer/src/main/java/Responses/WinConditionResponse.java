package Responses;

public class WinConditionResponse {
    private final boolean winCondition;
    private final String user;
    private final String gameCode;

    public WinConditionResponse(boolean condition, String user, String gameCode) {
        this.winCondition = condition;
        this.user = user;
        this.gameCode = gameCode;
    }

    public boolean getWinCondtion() {
        return winCondition;
    }

    public String getUser() {
        return user;
    }

    public String getGameCode() {
        return gameCode;
    }
}
