package com.camelot.userManagement.service;

import com.camelot.userManagement.dto.UserDTO;
import com.camelot.userManagement.model.User;

import java.util.List;

public interface UserService {

    public UserDTO createUser(UserDTO userDTO) ;
    public  UserDTO updateUser(UserDTO userDTO,long userID);
    public List<UserDTO> retrieveAllUser();
    public void deleteUser(long userID);
    public  UserDTO retrieveUserById(long userID);
    public UserDTO retrieveUserByFirstName(String fname);
    public List<UserDTO> retrieveByFirstNameContaining(String sequence);
    public List<UserDTO> retrieveByFirstNameStartsWith(String sequence);
    public List<UserDTO> retrieveByFirstNameEndsWith(String sequence);



}
