package com.camelot.userManagement.service;

import com.camelot.userManagement.dto.UserDTO;
import com.camelot.userManagement.exception.NoDataFoundException;
import com.camelot.userManagement.exception.UserNotFoundException;
import com.camelot.userManagement.model.User;
import com.camelot.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public  User updateUser(User user,long userID){
        User retrievedUser = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));

        retrievedUser.setFirstName(user.getFirstName());
        retrievedUser.setLastName(user.getLastName());
        retrievedUser.setAddress(user.getAddress());
        retrievedUser.setEmail(user.getEmail());
        retrievedUser.setAge(user.getAge());

        User updatedUser = userRepository.save(user);
        return updatedUser;

    }

    @Override
    public List<User> retrieveAllUser() {
        var users = (List<User>) userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        return users;
    }

    @Override
    public User retrieveUserById(long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() ->  new UserNotFoundException(userID));

    }

    @Override
    public User retrieveUserByFirstName(String firstName) {

        return userRepository.findByFirstName(firstName);
    }

    @Override
    public void deleteUser(long userID) {


        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));

        userRepository.delete(user);



    }

    @Override
    public List<User> retrieveByFirstNameContaining(String sequence) {
        var users = (List<User>) userRepository.findByFirstNameContaining(sequence);
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        return users;

    }

    @Override
    public List<User> retrieveByFirstNameStartsWith(String sequence) {
        var users = (List<User>) userRepository.findByFirstNameStartingWith(sequence);
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        return users;

    }

    @Override
    public List<User> retrieveByFirstNameEndsWith(String sequence) {
        var users = (List<User>)  userRepository.findByFirstNameEndingWith(sequence);
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        return users;

    }









    /*@Override
    public List<String> retrieveUserFirstNamesStartWith(String sequence) {
        return retrieveAllUser().stream().map(user -> user.getFirstName()).collect(Collectors.toList())
                .stream().sorted().filter((user) -> user.startsWith(sequence)).collect(Collectors.toList())
    }

    public List<String> retrieveUserFirstNamesEndsWith(String sequence) {
        return retrieveAllUser().stream().map(user -> user.getFirstName()).collect(Collectors.toList())
                .stream().sorted().filter((user) -> user.endsWith(sequence)).collect(Collectors.toList());
    }
    public List<String> retrieveUserFirstNamesContains(String sequence) {
        return retrieveAllUser().stream().map(user -> user.getFirstName()).collect(Collectors.toList())
                .stream().sorted().filter((user) -> user.contains(sequence)).collect(Collectors.toList());
    }*/




}
