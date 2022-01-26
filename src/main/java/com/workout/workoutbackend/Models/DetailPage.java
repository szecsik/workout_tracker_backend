package com.workout.workoutbackend.Models;

import java.util.ArrayList;
import java.util.List;

public class DetailPage {
    private String username;
    private String name;
    private List<Exercise> exerciseList = new ArrayList<Exercise>();
    private float highest;

    public DetailPage(String username,String name) {
        this.username = username;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public float getHighest() {
        return highest;
    }

    public void setHighest(float highest) {
        this.highest = highest;
    }
}
