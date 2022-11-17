package com.car.sale.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.sale.entities.User;
import com.car.sale.exception.UserException;
import com.car.sale.service.IUserService;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	IUserService userService;

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	// Mapping for sign up
	@PostMapping("sign-up")
	public ResponseEntity<String> signUp(@Valid @RequestBody User user, BindingResult br) {
		ResponseEntity<String> responseEntity;
		if (br.hasErrors()) {
			String error = "";
			for (ObjectError err : br.getAllErrors()) {
				error = error + err.getDefaultMessage() + "\n";
			}
			responseEntity = new ResponseEntity<String>(error, HttpStatus.UNAUTHORIZED);
		}
		else{ 
			try {
				User u1 = userService.signUp(user);
				responseEntity = new ResponseEntity<>("User " + u1.getUserId() + " added Successfully",
						HttpStatus.OK);
			} catch (UserException e) {
				responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return responseEntity;
	}
	
	// mapping for sign in
	@PostMapping("sign-in")
	public ResponseEntity<?> signIn(@RequestBody User user, HttpServletResponse response) {
		ResponseEntity responseEntity;
		try {
			User user1 = userService.signIn(user);
			responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);
		}  catch (UserException e) {
			System.out.println(e);
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// mapping for sign out
	@PostMapping("sign-out")
	public ResponseEntity<String> signOut(@RequestBody User user) {
		ResponseEntity<String> responseEntity;
		try {
			userService.signOut(user);
			responseEntity = new ResponseEntity<String>("Signed out succesfully", HttpStatus.OK);
		} catch (UserException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	// Change password
	@PutMapping("change-password/{id}")
	public ResponseEntity<String> changePassword(@PathVariable int id, @Valid @RequestBody User user,
			BindingResult br) {
		ResponseEntity<String> responseEntity;
		if (br.hasErrors()) {
			String error = "";
			for (ObjectError err : br.getAllErrors()) {
				error = error + "\n" + err.getDefaultMessage();
			}
			responseEntity = new ResponseEntity<String>(error, HttpStatus.UNAUTHORIZED);
		}
		else{
			try {
				userService.changePassword(id, user);
				responseEntity = new ResponseEntity<String>("Password changed", HttpStatus.OK);
			} catch (UserException e) {
				responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}
		return responseEntity;
	}
	
}
