package Commands;

public class UnSubscribeCommand {
    private final String gameCode;
    private final String user;

    public UnSubscribeCommand(String gameCode, String user) {
        this.gameCode = gameCode;
        this.user = user;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getUser() { return user; }
}
