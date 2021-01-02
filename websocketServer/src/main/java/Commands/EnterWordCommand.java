package Commands;

public class EnterWordCommand {
    private final String gameCode;
    private final String word;
    private final String player;

    public EnterWordCommand(String gameCode, String word, String player) {
        this.gameCode = gameCode;
        this.word = word;
        this.player = player;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getWord() {
        return word;
    }

    public String getPlayer() {
        return player;
    }
}
