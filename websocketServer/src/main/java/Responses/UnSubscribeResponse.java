package Responses;

import Commands.UnSubscribeCommand;

public class UnSubscribeResponse {
    private final String gameCode;
    private final String user;

    public UnSubscribeResponse(String gameCode, String user) {
        this.gameCode = gameCode;
        this.user = user;
    }

    public UnSubscribeResponse(UnSubscribeCommand command) {
        this.gameCode = command.getGameCode();
        this.user = command.getUser();
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getUser() { return user; }
}
