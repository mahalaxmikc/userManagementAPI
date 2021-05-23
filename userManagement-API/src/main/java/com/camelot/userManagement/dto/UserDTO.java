package com.camelot.userManagement.dto;

import org.hibernate.annotations.Nationalized;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Represents the form that user request and response data takes. Does not map
 * to the database directly.
 */
public class UserDTO {

    private long id;

    @Column(length = 100)
    @NotBlank
    private String firstName;

    @Column(length = 100)
    @NotBlank
    private String lastName;

    @Email(message = "Invalid Email ID,Please enter valid")
    private String email;

    private String address;

    @Min(18)
    @NotNull
    private int age;

    public UserDTO() {
    }

    public UserDTO(long id, String firstName, String lastName,
                   String email, String address, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
