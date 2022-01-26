package com.workout.workoutbackend;

public class CustomException extends Exception{

    private int status;

    public CustomException(String message, int status) {
        super(message);
        this.status=status;
    }

    public int getStatus() {
        return status;
    }
}
