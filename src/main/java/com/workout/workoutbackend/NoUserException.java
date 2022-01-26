package com.workout.workoutbackend;

public class NoUserException extends CustomException {

    public NoUserException(String username) {
        super(username+" does not exist!",404);
    }
}
