package com.camelot.userManagement.service;

import com.camelot.userManagement.dto.UserDTO;
import com.camelot.userManagement.model.User;

import java.util.List;

public interface UserService {

    public User createUser(User user) ;
    public  User updateUser(User user,long userID);
    public List<User> retrieveAllUser();
    public void deleteUser(long userID);
    public  User retrieveUserById(long userID);
    public User retrieveUserByFirstName(String fname);
    public List<User> retrieveByFirstNameContaining(String sequence);
    public List<User> retrieveByFirstNameStartsWith(String sequence);
    public List<User> retrieveByFirstNameEndsWith(String sequence);



}
