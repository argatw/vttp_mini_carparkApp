package com.example.miniProject.Service;

import java.util.UUID;

import com.example.miniProject.Model.User;
import com.example.miniProject.Repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class UserService {
    @Autowired
    private UserRepo uRepo;

    // public boolean insertUser(User user) {
    //     UUID uuid = UUID.randomUUID();
    //     String id = uuid.toString().substring(0, 8);
    //     user.setId(id);
        
    //     boolean added = uRepo.save(user);
    //     if (added) {
    //         return true;
    //     } else {
    //         return false;
    //     }
        
    // }

    public boolean insertUser(User user) {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0,8);
        user.setId(id);
        boolean added = uRepo.save(user);
        if (added) {
            return true;
        } else {
            return false;
        }
    }

    public boolean auth(String email, String password) {
        return 1== uRepo.getUserByEmailAndPassword(email, password);
    }


}
