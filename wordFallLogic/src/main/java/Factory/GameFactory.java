package Factory;

import Game.Game;
import Interfaces.IGamePlayer;

public class GameFactory {
    public static IGamePlayer createGame() {
        return new Game();
    }
}
