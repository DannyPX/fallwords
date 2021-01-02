package Commands;

public class SubscribeCommand {
    private final String gameCode;
    private final String authenticationKey;
    private final String user;

    public SubscribeCommand(String gameCode, String authenticationKey, String user) {
        this.gameCode = gameCode;
        this.authenticationKey = authenticationKey;
        this.user = user;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public String getUser() { return user; }
}

