package Responses;

public class StartGameResponse {
    private final String gameCode;
    private final String words;

    public StartGameResponse(String gameCode, String words) {
        this.gameCode = gameCode;
        this.words = words;
    }

    public String getGameCode() {
        return gameCode;
    }
    public String getWords() {
        return words;
    }
}
