package Responses;

public class EnterWordOwnResponse {
    private final String gameCode;
    private final String newWord;
    private final String oldWord;
    private final boolean correctWord;
    private final String players;

    public EnterWordOwnResponse(String gameCode, String newWord, boolean correctWord, String oldWord, String players) {
        this.gameCode = gameCode;
        this.newWord = newWord;
        this.correctWord = correctWord;
        this.oldWord = oldWord;
        this.players = players;
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
    public String getOldWord() { return oldWord; }

    public String getPlayers() {
        return players;
    }
}
