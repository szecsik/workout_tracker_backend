package com.workout.workoutbackend.Models;

import java.util.Date;

public class Exercise {

    private String id;
    private String user;
    private String name;
    private String muscle;
    private float weight;
    private int rep;
    private Date date = new Date();

    public Exercise(String user, String name, String muscle, float weight, int rep) {
        this.user = user;
        this.name = name;
        this.muscle = muscle;
        this.weight = weight;
        this.rep = rep;
    }

    public Exercise() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }
}
