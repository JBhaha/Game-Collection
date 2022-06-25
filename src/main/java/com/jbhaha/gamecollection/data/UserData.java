package com.jbhaha.gamecollection.data;

import com.jbhaha.gamecollection.model.User;

import java.util.List;

public class UserData {

    private static final UserData instance = new UserData();

    public static User findUser(String username, String password){

        User user = new User();
        List<User> userList = DataHandler.getUserList();

        for (User entry : userList){
            if (entry.getUserName().equals(username) && entry.getPassword().equals(password)){
                user = entry;
            }
        }
        return user;
    }

}
