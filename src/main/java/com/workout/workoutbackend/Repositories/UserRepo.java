package com.workout.workoutbackend.Repositories;


import com.workout.workoutbackend.Models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
