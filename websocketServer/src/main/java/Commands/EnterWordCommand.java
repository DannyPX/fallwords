package Commands;

public class EnterWordCommand {
    private final String gameCode;
    private final String word;
    private final String user;

    public EnterWordCommand(String gameCode, String word, String user) {
        this.gameCode = gameCode;
        this.word = word;
        this.user = user;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getWord() {
        return word;
    }

    public String getUser() {
        return user;
    }
}
