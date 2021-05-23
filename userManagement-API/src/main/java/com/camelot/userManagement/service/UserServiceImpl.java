package com.camelot.userManagement.service;

import com.camelot.userManagement.dto.UserDTO;
import com.camelot.userManagement.exception.NoDataFoundException;
import com.camelot.userManagement.exception.UserNotFoundException;
import com.camelot.userManagement.model.User;
import com.camelot.userManagement.repository.UserRepository;
import com.camelot.userManagement.util.UserDTOConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
      saveOrUpdate
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        return convertEntityToDTO(userRepository.save(convertDTOToEntity(userDTO)));
    }

    public  UserDTO updateUser(UserDTO userDTO,long userID){
        User retrievedUser = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));

        retrievedUser.setFirstName(userDTO.getFirstName());
        retrievedUser.setLastName(userDTO.getLastName());
        retrievedUser.setAddress(userDTO.getAddress());
        retrievedUser.setEmail(userDTO.getEmail());
        retrievedUser.setAge(userDTO.getAge());

        User saved =userRepository.save(retrievedUser);
        UserDTO updatedUserDTO = convertEntityToDTO(saved);
        return updatedUserDTO;

    }

    @Override
    public List<UserDTO> retrieveAllUser() {
        var users = (List<User>) userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        return users.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO retrieveUserById(long userID) {
        User user= userRepository.findById(userID)
                .orElseThrow(() ->  new UserNotFoundException(userID));
        return convertEntityToDTO(user);

    }

    @Override
    public UserDTO retrieveUserByFirstName(String firstName) {

        return convertEntityToDTO(userRepository.findByFirstName(firstName));
    }

    @Override
    public void deleteUser(long userID) {

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));

        userRepository.delete(user);

    }

    @Override
    public List<UserDTO> retrieveByFirstNameContaining(String sequence) {
        var users = (List<User>) userRepository.findByFirstNameContaining(sequence);
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        return users.stream().map(this::convertEntityToDTO).collect(Collectors.toList());

    }

    @Override
    public List<UserDTO> retrieveByFirstNameStartsWith(String sequence) {
        var users = (List<User>) userRepository.findByFirstNameStartingWith(sequence);
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        return users.stream().map(this::convertEntityToDTO).collect(Collectors.toList());

    }

    @Override
    public List<UserDTO> retrieveByFirstNameEndsWith(String sequence) {
        var users = (List<User>)  userRepository.findByFirstNameEndingWith(sequence);
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        return users.stream().map(this::convertEntityToDTO).collect(Collectors.toList());

    }

    private   UserDTO convertEntityToDTO(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    private User convertDTOToEntity(UserDTO userDTO){
        User user= new User();
        BeanUtils.copyProperties(userDTO,user);
        return  user;
    }



}
