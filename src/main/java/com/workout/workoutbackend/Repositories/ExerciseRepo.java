package com.workout.workoutbackend.Repositories;

import com.workout.workoutbackend.Models.Exercise;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepo extends CrudRepository<Exercise, String> {


    List<Exercise> findByUserAndName(String username, String name, Pageable pageable);

}
