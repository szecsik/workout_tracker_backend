package com.workout.workoutbackend;

import com.workout.workoutbackend.Models.LoginUser;
import com.workout.workoutbackend.Models.LoginUserDetails;
import com.workout.workoutbackend.Models.UserEntity;
import com.workout.workoutbackend.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username);

        if(userEntity == null){
            throw new UsernameNotFoundException(userEntity.getUsername());
        }

        List<SimpleGrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority("user"));
        return new User(userEntity.getUsername(), userEntity.getPassword(),true,true,true,userEntity.isActive(), authorityList);
    }

}
