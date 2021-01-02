package Responses;

public class EnterWordOwnResponse {
    private final String gameCode;
    private final String newWord;
    private final boolean correctWord;

    public EnterWordOwnResponse(String gameCode, String newWord, boolean correctWord) {
        this.gameCode = gameCode;
        this.newWord = newWord;
        this.correctWord = correctWord;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getNewWord() {
        return newWord;
    }

    public boolean getCorrectWord() {
        return correctWord;
    }
}
