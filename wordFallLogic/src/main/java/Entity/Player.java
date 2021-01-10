package Entity;

public class Player {
    private int id;
    private int points;
    private final String name;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void addPoints(int points) {
        this.points = this.points + points;
    }
}


