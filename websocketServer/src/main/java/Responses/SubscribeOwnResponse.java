package Responses;

import Commands.SubscribeCommand;

import java.util.List;

public class SubscribeOwnResponse {
    private final String gameCode;
    private final String players;

    public SubscribeOwnResponse(String gameCode, String players) {
        this.gameCode = gameCode;
        this.players = players;
    }

    public SubscribeOwnResponse(SubscribeCommand command, String players) {
        this.gameCode = command.getGameCode();
        this.players = players;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getPlayers() {
        return players;
    }
}
