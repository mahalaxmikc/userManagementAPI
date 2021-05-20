package com.camelot.userManagement.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {

        super(String.format("User with Id %d does not exist", id));
    }


}
