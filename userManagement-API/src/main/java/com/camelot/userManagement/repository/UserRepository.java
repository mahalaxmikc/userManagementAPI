package com.camelot.userManagement.repository;

import com.camelot.userManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

   List<User> findByFirstNameContaining(String sequence);
    List<User> findByFirstNameStartingWith(String sequence);
    List<User> findByFirstNameEndingWith(String sequence);
    User findByFirstName(String firstName);

}
