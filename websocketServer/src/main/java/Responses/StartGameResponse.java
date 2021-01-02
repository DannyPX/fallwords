package Responses;

public class StartGameResponse {
    private final String gameCode;

    public StartGameResponse(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getGameCode() {
        return gameCode;
    }
}
