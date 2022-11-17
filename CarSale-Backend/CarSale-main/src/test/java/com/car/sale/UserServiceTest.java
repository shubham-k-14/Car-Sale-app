package com.car.sale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.car.sale.entities.User;
import com.car.sale.exception.UserException;
import com.car.sale.repository.IUserRepository;
import com.car.sale.service.IUserService;

@SpringBootTest
public class UserServiceTest {

	@MockBean // creating mock repository so data doesn't affect database
	IUserRepository userRepository;

	@Autowired
	IUserService userService;

	/* ================ passing test cases ========================*/

	// Test for sign up
	@Test
	public void signUpTest() {
		User user = new User(1, "qwerty123", "admin");
		when(userRepository.save(user)).thenReturn(user);
		try {
			userService.signUp(user);
			assertEquals(userService.signUp(user), user);
		} catch (UserException e) {
			assertFalse(true); //failing if exception is thrown
		}
	}
	
	// test for sign in
	@Test
	public void signInTest1() {
		User user = new User(1, "qwerty123", "admin");
		when(userRepository.findById((long) 1)).thenReturn(Optional.of(user));
		try {
			User result = userService.signIn(user);
			assertEquals(result, user);
		} catch (UserException e) {
			assertFalse(true);  //failing if exception is thrown
		}
	}
	
	// Test for sign out
	@Test
	public void signOutTest1() {
		User user = new User(1, "qwerty123", "admin");
		when(userRepository.findById((long) 1)).thenReturn(Optional.of(user));
		try {
			User result = userService.signOut(user);
			assertEquals(result, user);
		} catch (UserException e) {
			assertFalse(true);  //failing if exception is thrown
		}
	}
	
	// Test for change password
	@Test
	public void changePasswordTest1() {
		User user1 = new User(1, "qwerty123", "admin");
		User user2 = new User(2, "qwerty", "admin");
		when(userRepository.save(user1)).thenReturn(user1);
		when(userRepository.findById((long) 1)).thenReturn(Optional.of(user1));
		try {
			User result = userService.changePassword(1, user2);
			assertEquals(result.getPassword(), user2.getPassword());
		} catch (UserException e) {
			assertFalse(true);   //failing if exception is thrown
		}
	}
	
	/* ================== Exception testing cases =======================*/
	
	//checking if sign in throws exception
	@Test
	public void signInTest2() {
		User user1 = new User(1, "qwerty123", "admin");
		when(userRepository.findById((long) 1)).thenReturn(null); //gives null when searched for '1'
		assertThrows(UserException.class, () -> userService.signIn(user1)); //succeed id exception is thrown

	}

	// checking if sign out throws exception
	@Test
	public void signOutTest2() {
		User user1 = new User(1, "qwerty123", "admin");
		User user2 = new User(1, "qwerty", "admin");     // checking when password is wrong
		when(userRepository.findById((long) 1)).thenReturn(Optional.of(user1));
		assertThrows(UserException.class, () -> userService.signIn(user2));

	}

	// checking if change Password throws exception
	@Test
	public void changePasswordTest2() {
		User user1 = new User(1, "qwerty123", "admin");
		User user2 = new User(2, "qwerty", "customer");
		when(userRepository.save(user1)).thenReturn(user1); //checking if role is wrong
		when(userRepository.findById((long) 1)).thenReturn(Optional.of(user1));
		assertThrows(UserException.class, () -> userService.changePassword(1, user2));
	}
}
