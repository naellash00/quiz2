package com.example.quiz2.Service;

import com.example.quiz2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(String id, User user){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getSameBalanceOrAbove(double balance){
        ArrayList<User> sameBalanceOrAbove = new ArrayList<>();
        for(User user : users){
            if(user.getBalance() >= balance){
                sameBalanceOrAbove.add(user);
            }
        }
        return sameBalanceOrAbove;

    }

    public ArrayList<User> getSameAgeOrAbove(int age){
        ArrayList<User> sameAgeOrAbove = new ArrayList<>();
        for(User user : users){
            if(user.getAge()>= age){
                sameAgeOrAbove.add(user);
            }
        }
        return sameAgeOrAbove;
    }


}
