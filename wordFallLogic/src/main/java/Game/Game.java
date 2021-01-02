package Game;

import Entity.Player;
import Entity.Word;
import Interfaces.IGamePlayer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Game implements IGamePlayer {
    private final String gameCode;
    private List<Word> dictionary;
    private List<Player> players;
    private List<Word> gameWords;

    public Game() {
        gameCode = generateType1UUID().toString();
        //TODO Dictionary
    }

    @Override
    public boolean startGame() {
        return false;
    }

    @Override
    public boolean addPlayer(String player) {
        return false;
    }

    @Override
    public boolean removePlayer(String player) {
        return false;
    }

    @Override
    public String enterWord(String player, String word) {
        return null;
    }

    @Override
    public List<String> getPlayers() {
        return null;
    }

    @Override
    public String getGameCode() {
        return gameCode;
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
