package com.car.sale.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car.sale.entities.JwtResponse;
import com.car.sale.entities.User;
import com.car.sale.service.CustomUserDetailService;

@RestController()
@CrossOrigin("http://localhost:4200")
public class JwtController {
	
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@GetMapping("welcome")
	public String welcome() {
		return "welcome";
	}
	
	@PostMapping("sign-in21")
	public ResponseEntity<?> generateToken(@RequestBody JwtUser user) throws Exception{
		System.out.println(user);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword()));
			UserDetails userDetails =customUserDetailService.loadUserByUsername(String.valueOf(user.getUserId()));
			String token = jwtUtil.generateToken(userDetails);
			
			System.out.println("jwt"+token);
			JwtResponseEntity jwtresp = new JwtResponseEntity(user.getUserId(),user.getRole(),user.getPassword(),token);
			return ResponseEntity.ok(jwtresp);
		}catch(UsernameNotFoundException e) {
			throw new Exception(e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Invalid Credentials");
		}
		
		
	}
}
