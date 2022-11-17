package com.car.sale.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.car.sale.entities.User;
import com.car.sale.exception.UserException;
import com.car.sale.repository.IUserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		int userId =Integer.parseInt(userName);
		Optional<User> in = userRepository.findById((long) userId);
		if (in == null || !in.isPresent()) {
			System.out.println(in);
			throw new UsernameNotFoundException("Invalid user");
		} else {
			User signed = in.get();
			System.out.println(signed);
			return new org.springframework.security.core.userdetails.User(String.valueOf(signed.getUserId()), signed.getPassword(),new ArrayList<>());
		}
		
				
	}

}
