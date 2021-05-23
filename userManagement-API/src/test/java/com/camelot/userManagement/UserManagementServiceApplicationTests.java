package com.camelot.userManagement;

import com.camelot.userManagement.controller.UserController;
import com.camelot.userManagement.dto.UserDTO;
import com.camelot.userManagement.model.User;
import com.camelot.userManagement.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Transactional
@SpringBootTest(classes = UserManagementServiceApplication.class)
class UserManagementServiceApplicationTests {

	@Autowired
   private  UserController userController;
	@Autowired
	private UserService userService;


	@Test
	public void testCreateUser() throws Exception{
       UserDTO userDTO=createUserDTO();
       UserDTO newUserDTO= userController.createUser(userDTO);
       UserDTO retrievedUserDTO=userController.retrieveUserById(newUserDTO.getId());
       Assertions.assertEquals(newUserDTO.getFirstName(),retrievedUserDTO.getFirstName());
       Assertions.assertEquals(newUserDTO.getId(),retrievedUserDTO.getId());
       Assertions.assertTrue(retrievedUserDTO.getId() > 0);
	}

	@Test
	public void testUpdateUser() throws Exception{
		UserDTO userDTO=createUserDTO();
		UserDTO newUserDTO= userController.createUser(userDTO);
		UserDTO retrievedUserDTO=userController.retrieveUserById(newUserDTO.getId());
		retrievedUserDTO.setAddress("Oman");
		UserDTO updatedUser=userController.updateUser(retrievedUserDTO.getId(), retrievedUserDTO);
		assertThat(updatedUser.getAddress()).isEqualTo("Oman");
	}

	@Test
	public void testDeleteUser() throws Exception{
		UserDTO userDTO=createUserDTO();
		UserDTO savedUserDTO=userController.createUser(userDTO);
		userController.deleteUser(savedUserDTO.getId());
	}
	@Test
	public void testRetrieveAllUser() throws Exception{
		UserDTO userDTO= createUserDTO();
		userController.createUser(userDTO);
		UserDTO userDTO1=createUserDTO();
		userController.createUser(userDTO1);
		List<UserDTO> userDTOList=userController.retrieveAllUser();
		Assertions.assertEquals(userDTOList.size(),2);
	}

	@Test
	public void testRetrieveUserByFirstNameContaining()throws Exception{
		UserDTO userDTO= createUserDTO();
		userController.createUser(userDTO);
		UserDTO userDTO1=createUserDTO();
		userController.createUser(userDTO1);
		List<UserDTO> userDTOList= userController.retrieveUserByFirstNameContaining("Ash");
		assertThat(userDTOList.get(0).getFirstName()).startsWith("Ash");
	}



	private static UserDTO createUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Ashwini");
		userDTO.setLastName("Nayak");
		userDTO.setEmail("ashwini@example.com");
		userDTO.setAddress("Karnataka,India");
		userDTO.setAge(23);
		return userDTO;
	}

}
