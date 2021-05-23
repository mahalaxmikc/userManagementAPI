package com.camelot.userManagement.util;

import com.camelot.userManagement.dto.UserDTO;
import com.camelot.userManagement.model.User;
import org.springframework.beans.BeanUtils;

public class UserDTOConverter {

    public    UserDTO convertEntityToDTO(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public User convertDTOToEntity(UserDTO userDTO){
        User user= new User();
        BeanUtils.copyProperties(userDTO,user);
        return  user;
    }
}
