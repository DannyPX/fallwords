package Entity;

public class Player {
    private int id;
    private int points;
    private String name;

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
        this.points =+ points;
    }
}


