package com.example.quiz2.Controller;

import com.example.quiz2.ApiResponse.ApiResponse;
import com.example.quiz2.Model.User;
import com.example.quiz2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userServices;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        ArrayList<User> users = userServices.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userServices.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("users added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user, Errors errors){
        boolean isUpdated = userServices.updateUser(id, user);
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        boolean isDeleted = userServices.deleteUser(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @GetMapping("/same/balance/{balance}")
    public ResponseEntity getSameBalanceOrAbove(@PathVariable double balance){
        ArrayList sameBalanceOrAbove = userServices.getSameBalanceOrAbove(balance);
        if(sameBalanceOrAbove.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("enter correct balance"));

        return ResponseEntity.status(200).body(sameBalanceOrAbove);
    }

    @GetMapping("/same/age/{age}")
    public ResponseEntity getSameAgeOrAbove(@PathVariable int age){
        ArrayList<User> sameAgeOrAbove = userServices.getSameAgeOrAbove(age);
        if(age < 15)
            return ResponseEntity.status(400).body(new ApiResponse("enter correct age. cannot be less than 15"));

        return ResponseEntity.status(200).body(sameAgeOrAbove);
    }
}
