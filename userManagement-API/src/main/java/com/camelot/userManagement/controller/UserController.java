package com.camelot.userManagement.controller;

import com.camelot.userManagement.dto.UserDTO;
import com.camelot.userManagement.model.User;
import com.camelot.userManagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/user")
@Api(tags = {"User"})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
})
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   @PostMapping("/newUser")
   @ApiOperation(value = "Creates a User object")
    public UserDTO createUser(@Valid  @RequestBody UserDTO userDTO) {

        return userService.createUser(userDTO);
    }


    @GetMapping("/allUsers")
    @ApiOperation(value = "Returns a list of all Users ")
    public List<UserDTO> retrieveAllUser(){
        return userService.retrieveAllUser();

    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "Retrieves the  User by its ID")
    public UserDTO retrieveUserById(@PathVariable(value = "id") Long userId) {
      return userService.retrieveUserById(userId);
    }

    @PutMapping("/users/{id}")
    @ApiOperation(value = "Updated the existing User based on ID")
    public UserDTO updateUser(@PathVariable(value = "id") Long userId,
                              @Valid @RequestBody UserDTO userDTO) {

        return userService.updateUser(userDTO,userId);
    }

    @GetMapping("/user/{sequence}")
    @ApiOperation(value = "Retrieves the List of Users with Similar FirstName")
    public List<UserDTO> retrieveUserByFirstNameContaining(@PathVariable("sequence") String sequence){
        return userService.retrieveByFirstNameContaining(sequence);
        //return users.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation(value = "Deletes the existing User based on ID")
    public void deleteUser(@PathVariable(value = "id") Long userId){
        userService.deleteUser(userId);
    }



}
