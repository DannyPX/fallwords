package Responses;

import Commands.SubscribeCommand;

public class SubscribeResponse {
    private final String gameCode;
    private final String user;

    public SubscribeResponse(String gameCode, String user) {
        this.gameCode = gameCode;
        this.user = user;
    }

    public SubscribeResponse(SubscribeCommand command) {
        this.gameCode = command.getGameCode();
        this.user = command.getUser();
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getUser() { return user; }
}
