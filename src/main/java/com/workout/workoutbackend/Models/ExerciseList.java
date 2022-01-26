package com.workout.workoutbackend.Models;

import java.util.ArrayList;
import java.util.List;

public class ExerciseList {
    private List<Exercise> chest = new ArrayList<>();
    private List<Exercise> biceps = new ArrayList<>();
    private List<Exercise> triceps = new ArrayList<>();
    private List<Exercise> shoulder = new ArrayList<>();
    private List<Exercise> leg = new ArrayList<>();
    private List<Exercise> back = new ArrayList<>();

    public ExerciseList() {
    }

    public List<Exercise> getChest() {
        return chest;
    }

    public void setChest(List<Exercise> chest) {
        this.chest = chest;
    }

    public List<Exercise> getBiceps() {
        return biceps;
    }

    public void setBiceps(List<Exercise> biceps) {
        this.biceps = biceps;
    }

    public List<Exercise> getTriceps() {
        return triceps;
    }

    public void setTriceps(List<Exercise> triceps) {
        this.triceps = triceps;
    }

    public List<Exercise> getShoulder() {
        return shoulder;
    }

    public void setShoulder(List<Exercise> shoulder) {
        this.shoulder = shoulder;
    }

    public List<Exercise> getLeg() {
        return leg;
    }

    public void setLeg(List<Exercise> leg) {
        this.leg = leg;
    }

    public List<Exercise> getBack() {
        return back;
    }

    public void setBack(List<Exercise> back) {
        this.back = back;
    }
}
