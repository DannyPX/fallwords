package Commands;

public class StartGameCommand {
    private final String gameCode;

    public StartGameCommand(String gameCode, String authenticationKey) {
        this.gameCode = gameCode;
    }

    public String getGameCode() {
        return gameCode;
    }
}
