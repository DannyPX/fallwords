package com.scrumble.userapi.Models;

import javax.persistence.*;

@Entity
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int points;

    @Column
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private final String name;
        private final int points;
        private final String password;

        public Builder(String name, String password) {
            this.name = name;
            this.points = 0;
            this.password = password;
        }

        public User build() {
            User user = new User();
            user.setName(name);
            user.setPoints(points);
            user.setPassword(password);
            return user;
        }
    }
}
