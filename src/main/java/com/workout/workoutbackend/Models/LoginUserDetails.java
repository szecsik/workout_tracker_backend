package com.workout.workoutbackend.Models;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginUserDetails extends UserDetails {
     String getName();

}
