package com.workout.workoutbackend;

public class UserAlreadyExistsException extends CustomException{
    public UserAlreadyExistsException(String username) {
        super(username+" already exists.",500);
    }
}
