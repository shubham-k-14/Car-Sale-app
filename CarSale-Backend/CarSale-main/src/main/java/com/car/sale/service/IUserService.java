package com.car.sale.service;

import com.car.sale.entities.User;
import com.car.sale.exception.UserException;


public interface IUserService {
	public User signUp(User user)throws UserException;
	public User signIn(User user)throws UserException;
	public User signOut(User user)throws UserException;
	public User changePassword(long id, User user) throws UserException;

}
