package Game;

import Entity.Player;
import Entity.Word;
import Interfaces.IGamePlayer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

public class Game implements IGamePlayer {
    private final String gameCode;
    private List<Player> players;
    private List<Word> gameWords;

    public Game() {
        gameCode = generateType1UUID().toString();
        players = new ArrayList<>();
        gameWords = new ArrayList<>();
    }

    @Override
    public boolean startGame() {
        for (int i = 0; i < 10; i++) {
            gameWords.add(new Word(RandomWordGenerator.getRandomWord()));
        }
        return true;
    }

    @Override
    public boolean addPlayer(String player) {
        if(players.stream().anyMatch(o -> o.getName().equals(player))) {
            return false;
        }
        players.add(new Player(player));
        return true;
    }

    @Override
    public boolean removePlayer(String player) {
        players.remove(new Player(player));
        return true;
    }

    @Override
    public String enterWord(String player, String word) {
        String newWord = null;
        if(gameWords.stream().anyMatch(o -> o.getWord().equals(word))) {
            players.stream().filter(o -> o.getName().equals(player)).findFirst().get().addPoints(word.length());
            gameWords.removeIf(o -> o.getWord().equals(word));
            newWord = RandomWordGenerator.getRandomWord();
            gameWords.add(new Word(newWord));
        }
        return newWord;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String getGameCode() {
        return gameCode;
    }

    @Override
    public boolean winCondition() {
        return players.stream().anyMatch(o -> o.getPoints() >= 100);
    }

    @Override
    public Player topPlayer() {
        return players.stream().filter(o -> o.getPoints() >= 100).findFirst().get();
    }

    @Override
    public List<Word> getGameWords() {
        return gameWords;
    }

    private static long get64LeastSignificantBitsForVersion1() {
        Random random = new Random();
        long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
        long variant3BitFlag = 0x8000000000000000L;
        return random63BitLong + variant3BitFlag;
    }

    private static long get64MostSignificantBitsForVersion1() {
        LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
        Duration duration = Duration.between(start, LocalDateTime.now());
        long seconds = duration.getSeconds();
        long nanos = duration.getNano();
        long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
        long least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
        long version = 1 << 12;
        return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;
    }

    public static UUID generateType1UUID() {

        long most64SigBits = get64MostSignificantBitsForVersion1();
        long least64SigBits = get64LeastSignificantBitsForVersion1();

        return new UUID(most64SigBits, least64SigBits);
    }
}
