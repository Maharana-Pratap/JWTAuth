package com.example.oppoUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.oppoUser.config.util.JwtRequest;
import com.example.oppoUser.config.util.JwtResponse;
import com.example.oppoUser.config.util.JwtTokenUtils;
import com.example.oppoUser.config.util.MyUserDetailsService;

@RestController 
@CrossOrigin
public class UserController {

	@Autowired 
	private AuthenticationManager manager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtTokenUtils jwtUtils;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello Oppo users";
	}
	
	@RequestMapping(value="/authenticate" , method=RequestMethod.POST)
	public ResponseEntity<?> authUser(@RequestBody JwtRequest request) throws Exception {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch(Exception e) {
			throw new Exception("Invalid User and Password");
		}
		final UserDetails details = myUserDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtUtils.generateToken(details);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
