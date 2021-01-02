package Interfaces;

import Entity.Player;
import Entity.Word;

import java.util.List;

public interface IGamePlayer {
    boolean startGame();
    boolean addPlayer(String player);
    boolean removePlayer(String player);
    String enterWord(String player, String word);
    List<String> getPlayers();
    String getGameCode();
}
