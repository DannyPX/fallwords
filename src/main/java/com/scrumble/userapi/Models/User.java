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

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private final String name;

        public Builder(String name) {
            this.name = name;
        }

        public User build() {
            User user = new User();
            user.setName(name);
            return user;
        }
    }
}
