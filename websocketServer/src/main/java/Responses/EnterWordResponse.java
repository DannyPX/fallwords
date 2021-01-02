package Responses;

public class EnterWordResponse {
    private final String gameCode;
    private final String word;

    public EnterWordResponse(String gameCode, String word) {
        this.gameCode = gameCode;
        this.word = word;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getWord() {
        return word;
    }
}
