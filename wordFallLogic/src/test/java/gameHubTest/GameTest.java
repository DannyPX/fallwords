package gameHubTest;

import Entity.Player;
import GameHub.GameHub;
import Interfaces.IGamePlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private GameHub gameHub;
    private String playerName1;
    private String playerName2;

    @BeforeEach
    void setUp() {
        gameHub = new GameHub();
        playerName1 = "Player1";
        playerName2 = "Player2";
    }


    // TC-03
    @Test
    void testCreateGame() {
        // Create Game
        String gameKey = gameHub.createGame();

        // Check amount of games, should be 1
        assertNotNull(gameKey);
        assertEquals(1, gameHub.getGames().size());
    }

    // TC-01
    @Test
    void testJoinGame() {
        // Join game
        String gameKey = gameHub.createGame();
        IGamePlayer game = gameHub.getGames().stream().filter(o -> o.getGameCode().equals(gameKey)).findFirst().get();
        game.addPlayer(playerName1);

        // Find player
        Player player = game.getPlayers().stream().filter(o -> o.getName().equals(playerName1)).findFirst().get();

        // Check if player is in the game
        assertEquals(playerName1, player.getName());
    }

    // TC-02
    @Test
    void testJoinGame_NonUniqueName() {
        // Join game
        String gameKey = gameHub.createGame();
        IGamePlayer game = gameHub.getGames().stream().filter(o -> o.getGameCode().equals(gameKey)).findFirst().get();
        game.addPlayer(playerName1);

        // Check if player is in the game
        assertFalse(game.addPlayer(playerName1));
    }

    // TC-05
    @Test
    void testStartGame() {
        // Join game
        String gameKey = gameHub.createGame();
        IGamePlayer game = gameHub.getGames().stream().filter(o -> o.getGameCode().equals(gameKey)).findFirst().get();
        game.addPlayer(playerName1);

        // Start game
        game.startGame();

        // Check if game has 10 words
        assertEquals(10, game.getGameWords().size());
    }

    // TODO TC-06

    // TC-07
    @Test
    void testEnterWord() {
        // Join game
        String gameKey = gameHub.createGame();
        IGamePlayer game = gameHub.getGames().stream().filter(o -> o.getGameCode().equals(gameKey)).findFirst().get();
        game.addPlayer(playerName1);

        // Start game
        game.startGame();
        game.enterWord(playerName1, game.getGameWords().stream().findFirst().get().getWord());

        Player player = game.getPlayers().stream().filter(o -> o.getName().equals(playerName1)).findFirst().get();

        // Check if player has more than 0 points
        assertTrue(player.getPoints() > 0);
    }
}
