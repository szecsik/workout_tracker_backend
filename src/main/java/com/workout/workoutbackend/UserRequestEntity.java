package com.workout.workoutbackend;

public class UserRequestEntity {
private String username;

    public UserRequestEntity(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
