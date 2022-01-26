package com.workout.workoutbackend;

import com.workout.workoutbackend.Models.*;
import com.workout.workoutbackend.Repositories.UserRepo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.json.JavaCharStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

@ExceptionHandler
private ResponseEntity customHandler(CustomException customException){

return new ResponseEntity(customException.getMessage(), HttpStatus.valueOf(customException.getStatus()));
}

@ExceptionHandler
private ResponseEntity authExc(AuthenticationException authenticationException) throws IOException, ServletException {

    return new ResponseEntity(authenticationException.getMessage(), HttpStatus.FORBIDDEN);
}

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signin")
    public TokenResponse login (@RequestBody LoginDTO user){
                try{
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
                    JwtTokenUtil token = new JwtTokenUtil();
                    UserDetails details = mongoUserDetailsService.loadUserByUsername(user.getUsername());
                    return new TokenResponse(details.getUsername(),"kiki", token.generateToken(details));
                }catch(BadCredentialsException exc){
                    UserEntity userSearch = userRepo.findByUsername(user.getUsername());
                    if(userSearch.getFailedLogin() == 4){
                        userSearch.setActive(false);
                    }
                    userSearch.setFailedLogin(userSearch.getFailedLogin()+1);
                    userRepo.save(userSearch);
                    throw new BadCredentialsException("Bad Credential");
                }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity user) throws UserAlreadyExistsException{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);
            return user;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addExercise")
    public Exercise addUser(@RequestHeader("Authorization") String token, @RequestBody Exercise exercise){
        System.out.println(token);
        exercise.setUser(jwtTokenUtil.getUsernameFromToken(token.substring(7)));
        userService.addExc(exercise);
        return exercise;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profile")
    public UserEntity getUser( @RequestHeader("Authorization") String token) throws NoUserException{
     
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        UserEntity getUser=userRepo.findByUsername(username);
        if(getUser == null){
            throw new NoUserException(username);
        }
        return getUser;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/exercises")
    public List<Exercise> getExercise(@RequestHeader("Authorization") String token, @RequestParam String name, @RequestParam int limit) throws NoUserException{

        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));

        List<Exercise> result = userService.exerciseHist(username, name, limit);
        if(result.size()==0){
            throw new NoUserException(username);
        }
        return result;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/details")
    public DetailPage getDetails(@RequestHeader("Authorization") String token, @RequestParam String name, @RequestParam int limit) throws NoUserException{
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        DetailPage result = new DetailPage(username, name);
        List<Exercise> excList = userService.exerciseHist(username, name, limit);
        if(excList.size()==0){
            throw new NoUserException(username);
        }
        result.setExerciseList(excList);
        result.setHighest(userService.allTimeHigh(username,name));
        return result;
    }


}
