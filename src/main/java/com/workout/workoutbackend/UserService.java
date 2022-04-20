package com.workout.workoutbackend;

import com.workout.workoutbackend.Models.Exercise;
import com.workout.workoutbackend.Models.ExerciseList;

import com.workout.workoutbackend.Models.UserEntity;
import com.workout.workoutbackend.Repositories.ExerciseRepo;
import com.workout.workoutbackend.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ExerciseRepo exerciseRepo;

    private List<Exercise> sorted (List<Exercise> list){
        List<Exercise> result = new ArrayList<Exercise>();

        return result;
    }

    public UserEntity addUser(UserEntity user) throws UserAlreadyExistsException{

        UserEntity testUser = userRepo.findByUsername(user.getUsername());
        if(testUser != null){
            throw new UserAlreadyExistsException(user.getUsername());
        }
        userRepo.save(user);
        return user;
    }

    private List<Exercise> clupList(List<Exercise> exList, String name){
        List<Exercise> baseList = exList;
        List<Exercise> filterable = exList.stream().filter(new ExerciseFilter(name)).collect(Collectors.toList());

        if(filterable.size()>=6){
            baseList.removeAll(filterable);
            filterable.sort(new DateComparator());
            filterable.remove(0);
            baseList.addAll(filterable);
        }
        return baseList;
    }

    public List<Exercise> exerciseHist (String username, String name, int limit){

        return exerciseRepo.findByUserAndName(username, name, PageRequest.of(0,limit));
    }

    public float allTimeHigh  (String username, String name){
        Pageable pageableHighest =  PageRequest.of(0,1, Sort.by("weight").descending());
        return exerciseRepo.findByUserAndName(username,name, pageableHighest).get(0).getWeight();
    }

    public Exercise addExc(Exercise exc){
        UserEntity user = userRepo.findByUsername(exc.getUser());
        ExerciseList exerciseList = user.getExerciseList();
        exerciseRepo.save(exc);
        List<Exercise> list;
        switch(exc.getMuscle()){
            case "Chest":
                list = clupList(exerciseList.getChest(), exc.getName());
                list.add(exc);
                exerciseList.setChest(list);
                break;
            case "Biceps":
                 list = clupList(exerciseList.getBiceps(),exc.getName());
                 list.add(exc);
                 exerciseList.setBiceps(list);
                 break;
            case "Triceps":
                list = clupList(exerciseList.getTriceps(),exc.getName());
                list.add(exc);
                exerciseList.setTriceps(list);
                break;
            case "Shoulder":
                list = clupList(exerciseList.getShoulder(),exc.getName());
                list.add(exc);
                exerciseList.setShoulder(list);
                break;
            case "Leg":
                list = clupList(exerciseList.getLeg(),exc.getName());
                list.add(exc);
                exerciseList.setLeg(list);
                break;
            case "Back":
                list = clupList(exerciseList.getBack(),exc.getName());
                list.add(exc);
                exerciseList.setBack(list);
                break;
        }

        userRepo.save(user);
        return exc;
    }

}
