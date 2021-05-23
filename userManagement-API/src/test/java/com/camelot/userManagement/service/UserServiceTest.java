package com.camelot.userManagement.service;

import com.camelot.userManagement.dto.UserDTO;
import com.camelot.userManagement.model.User;
import com.camelot.userManagement.repository.UserRepository;
import com.camelot.userManagement.util.UserDTOConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    UserDTOConverter userDTOConverter;

    private UserDTO userDTO;
    private User user;

    private List<UserDTO> userDTOList;
    private List<User> userList;

    @BeforeEach
    public void setUp() {
        userDTOList= new ArrayList<>();
        userList= new ArrayList<>();
       userDTO= new UserDTO(1,"Ashwini","Nayak",
               "ashwini@example.com","Karnataka,India",23);
       userDTOList.add(userDTO);
        user= new User(1l,"Ashwini","Nayak",
                "ashwini@example.com","Karnataka,India",23);
        userList.add(user);
    }

    @Test
    public void createUserShouldReturnAddedUser() {
       when(userRepository.save(any())).thenReturn(user);
        userService.createUser(userDTO);
        verify(userRepository,times(1)).save(any());
    }

    @Test
    public void givenIdThenShouldReturnProductOfThatId() {
        userRepository.save(user);
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        UserDTO userDTO = userService.retrieveUserById(user.getId());
        assertEquals("Ashwini", userDTO.getFirstName());
    }

    @Test
    public void shouldReturnListOfAllUsers(){
        userRepository.save(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> userDTOList1 =userService.retrieveAllUser();
        assertEquals(userDTOList1.size(),userList.size());
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findAll();
    }


    @Test
    public void givenUserIdShouldDeleteUser() {
          userRepository.save(user);
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        userService.deleteUser(user.getId());
    }

    public void givenIdShouldUpdateUser() {
       UserDTO newUserDTO =new UserDTO(1l,"Ashish","Nayak",
               "ashwini@example.com","Karnataka,India",22);
        User newUser =new User(1l,"Ashish","Nayak",
                "ashwini@example.com","Karnataka,India",22);
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        userService.updateUser(newUserDTO, userDTO.getId());
        verify(userRepository).save(newUser);
        verify(userRepository).findById(user.getId());
    }

    @Test
    public void givenSeqThenShouldReturnUserListFirstNameStart() {
        userRepository.save(user);
        when(userRepository.findByFirstNameStartingWith("ash")).thenReturn(userList);
        List<UserDTO> userDTOList1 =userService.retrieveByFirstNameStartsWith("ash");
        assertEquals(userDTOList1.size(),userList.size());
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findByFirstNameStartingWith("ash");
    }

    @Test
    public void givenSeqThenShouldReturnUserListFirstNameContain() {
        userRepository.save(user);
        when(userRepository.findByFirstNameContaining("ash")).thenReturn(userList);
        List<UserDTO> userDTOList1 =userService.retrieveByFirstNameContaining("ash");
        assertEquals(userDTOList1.size(),userList.size());
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findByFirstNameContaining("ash");
    }

    @Test
    public void givenSeqThenShouldReturnUserListFirstNameEnd() {
        userRepository.save(user);
        when(userRepository.findByFirstNameEndingWith("ash")).thenReturn(userList);
        List<UserDTO> userDTOList1 =userService.retrieveByFirstNameEndsWith("ash");
        assertEquals(userDTOList1.size(),userList.size());
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findByFirstNameEndingWith("ash");
    }

}
