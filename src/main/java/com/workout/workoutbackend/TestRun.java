package com.workout.workoutbackend;

import com.workout.workoutbackend.Models.Exercise;
import com.workout.workoutbackend.Repositories.ExerciseRepo;
import com.workout.workoutbackend.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//docker run --name db -d -p 27017:27017 mongo:latest


public class TestRun implements CommandLineRunner {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public UserService userService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Running");
/*
        userService.addUser("kiki");

        Exercise exec = new Exercise("kiki", "Chest Press","Chest",50,6);
        Exercise exec2 = new Exercise("kiki", "Chest Press","Chest",55,6);
        Exercise exec3 = new Exercise("kiki", "Bench Press","Chest",50,6);
        userService.addExc(exec);
        userService.addExc(exec2);
        userService.addExc(exec3);
*/

    }
}
