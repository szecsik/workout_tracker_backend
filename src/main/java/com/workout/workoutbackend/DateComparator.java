package com.workout.workoutbackend;

import com.workout.workoutbackend.Models.Exercise;

import java.util.Comparator;

public class DateComparator implements Comparator<Exercise> {

    @Override
    public int compare(Exercise o1, Exercise o2) {
        if(o1.getDate().before(o2.getDate())){
            return 0;
        }
        return -1;
    }
}
