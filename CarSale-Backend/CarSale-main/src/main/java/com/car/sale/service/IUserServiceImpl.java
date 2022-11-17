package com.car.sale.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.sale.entities.User;
import com.car.sale.exception.UserException;
import com.car.sale.repository.IUserRepository;

@Service
@Transactional
public class IUserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userRepository;

	// Sign up implementation
	@Override
	public User signUp(User user) throws UserException {
		User u1 = userRepository.save(user);
		if (u1 == null)
			throw new UserException("User not added");
		else
			return u1;
	}

	// Sign in implementation
	@Override
	public User signIn(User user) throws UserException {
		Optional<User> in = userRepository.findById(user.getUserId());
		if (in == null || !in.isPresent()) {
			System.out.println(in);
			throw new UserException("Wrong user id");
		} else {
			User signed = in.get();
			System.out.println(signed);
			boolean flag = user.equals(signed);
			if (flag)
				return user;
			else {
				throw new UserException("password is wrong or your role doesn't match");
			}
		}

	}

	// Sign out implementation
	@Override
	public User signOut(User user) throws UserException {
		Optional<User> out = userRepository.findById(user.getUserId());
		if (!out.isPresent()) {
			throw new UserException("You have already logged out or not present in the database");
		} else {
			User signout = out.get();
			boolean flag = user.equals(signout);
			if (flag)
				return signout;
			throw new UserException("password is wrong or your role doesn't match");
		}
	}

	// change password implementation
	@Override
	public User changePassword(long id, User user) throws UserException {
		Optional<User> optUser = userRepository.findById(id);
		if (!optUser.isPresent()) {
			throw new UserException("User not present");
		} else {
			User oldUser = optUser.get();
			if (oldUser.getRole().equalsIgnoreCase(user.getRole())) {
				oldUser.setPassword(user.getPassword());
				return oldUser;
			} else { // Exception is thrown also when your role doesn't match
				throw new UserException("your role doesn't match");
			}
		}

	}

}
