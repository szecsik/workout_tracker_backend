package com.workout.workoutbackend;

public class UserLockedException extends CustomException{
    public UserLockedException() {
        super("User is locked", 400);
    }
}
