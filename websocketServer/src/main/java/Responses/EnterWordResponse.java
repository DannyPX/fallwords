package Responses;

public class EnterWordResponse {
    private final String gameCode;
    private final String newWord;
    private final String oldWord;
    private final String players;

    public EnterWordResponse(String gameCode, String newWord, String oldWord, String players) {
        this.gameCode = gameCode;
        this.newWord = newWord;
        this.oldWord = oldWord;
        this.players = players;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getNewWord() {
        return newWord;
    }

    public String getOldWord() {
        return oldWord;
    }

    public String getPlayers() {
        return players;
    }
}
