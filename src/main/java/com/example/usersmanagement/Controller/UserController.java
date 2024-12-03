package com.example.usersmanagement.Controller;

import com.example.usersmanagement.ApiResponse.ApiResponse;
import com.example.usersmanagement.Model.User;
import com.example.usersmanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser (){
        return ResponseEntity.status(200).body(userService.getUser());
    }

@PostMapping("/add")
    public ResponseEntity addUser (@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }
    @PutMapping("/update/{id}")
   public ResponseEntity updateUser (@PathVariable Integer id , @RequestBody @Valid User user ,Errors errors ){
       if (errors.hasErrors()){
           return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
       }
       userService.updateUser(id,user);
       return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
   }

@DeleteMapping("/delete/{id}")
   public ResponseEntity deleteUser (@PathVariable Integer id ){
        userService.deleteUser(id);
       return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
   }

   @GetMapping("/userByEmail/{email}")
public ResponseEntity getUserByEmail (@PathVariable String  email){
      User user = userService.getUserByEmail(email);
    return ResponseEntity.status(200).body(user);
}
    @GetMapping("/userByRole/{role}")
public ResponseEntity getUserByRole (@PathVariable String  role){
    List<User> userByRole =userService.getUserByRole(role);
    return ResponseEntity.status(200).body(userByRole);


}

    @GetMapping("/userByAge/{age}")
    public ResponseEntity getUserByAgeAndAbove (@PathVariable Integer  age){
        List<User> userByAge =userService.getUserByAgeAndAbove(age);
        return ResponseEntity.status(200).body(userByAge);
    }


    @GetMapping("/check/{username}/{password}")
    public ResponseEntity checkUsernameAndPassword (@PathVariable String  username, @PathVariable String  password){
        User user = userService.checkUsernameAndPassword(username,password);
        return ResponseEntity.status(200).body(user);
    }


}
