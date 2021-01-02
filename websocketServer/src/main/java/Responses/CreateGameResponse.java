package Responses;

public class CreateGameResponse {
    private final String gameCode;

    public CreateGameResponse(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getGameCode() {
        return gameCode;
    }
}
