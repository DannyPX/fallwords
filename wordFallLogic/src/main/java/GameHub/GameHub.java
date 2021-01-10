package GameHub;

import Factory.GameFactory;
import Interfaces.IGamePlayer;

import java.util.ArrayList;
import java.util.List;

public class GameHub {
    List<IGamePlayer> games;

    public GameHub() {
        games = new ArrayList<>();
    }

    public String createGame() {
        IGamePlayer game = GameFactory.createGame();
        games.add(game);
        return game.getGameCode();
    }

    public boolean deleteGame(String gameCode) {
        try {
            games.removeIf(o -> o.getGameCode().equals(gameCode));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public List<IGamePlayer> getGames() {
        return games;
    }
}
