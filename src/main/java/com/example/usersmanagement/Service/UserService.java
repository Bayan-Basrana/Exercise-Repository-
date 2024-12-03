package com.example.usersmanagement.Service;

import com.example.usersmanagement.ApiResponse.ApiException;
import com.example.usersmanagement.Model.User;
import com.example.usersmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User> getUser (){
        return userRepository.findAll();
    }


    public void addUser (User user){
        userRepository.save(user);
    }

    public void updateUser (Integer id , User user){
        User old= userRepository.findUserById(id);
        if (old==null){
            throw new ApiException("user id not found");
        }

        old.setName(user.getName());
        old.setUsername(user.getUsername());
        old.setPassword(user.getPassword());
        old.setEmail(user.getEmail());
        old.setRole(user.getRole());
        old.setAge(user.getAge());
        userRepository.save(old);
    }


    public void deleteUser (Integer id ){
        User user = userRepository.findUserById(id);
        if (user==null){
            throw new ApiException("user id not found");
        }
        userRepository.delete(user);
    }


    public User  getUserByEmail (String email){
        User user = userRepository.findUserByEmail(email);
        if (user==null){
            throw new ApiException("user email not found");
        }
        return user;
    }


    public List<User> getUserByRole (String role){
        List<User> userByRole = userRepository.getUsersByRole(role);
        if (userByRole==null){
            throw new ApiException("this role have no user");
        }
        return userByRole;
    }

    public List<User> getUserByAgeAndAbove (Integer age){
        List<User> userByAge = userRepository.allUserByAgeAnd(age);
        if (userByAge==null){
            throw new ApiException("this age have no user");
        }
        return userByAge;
    }


    public User checkUsernameAndPassword (String username,String password){
        User user =userRepository.findUserByUsernameAndPassword(username,password);
        if (user==null){
            throw new ApiException("username and password not correct");
        }
        return user;    }

}
