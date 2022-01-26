package com.workout.workoutbackend;

import com.workout.workoutbackend.Models.Exercise;

import java.util.function.Predicate;

public class ExerciseFilter implements Predicate<Exercise> {

    private String type;

    public ExerciseFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean test(Exercise exercise) {

        if(exercise.getName().equals(type)){
            return true;
        }
        return false;
    }
}
