package com.example.oppoUser.config.util;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.oppoUser.dao.UserDao;
import com.example.oppoUser.entity.MyUser;

@Service 
public class MyUserDetailsService implements UserDetailsService {
	
	//@Autowired
	//private MyUserDetails userDtl;
	
	@Autowired 
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<MyUser> record = dao.findUserByName(username);
		MyUser user = record.orElse(null);
		if(null != user && user.getId() > 0) {
			System.out.println(user.getUser_name());
			return new User(user.getUser_name(), user.getPassword(), new ArrayList<>());
		}
		//return new User("kittu","kittu@2020", new ArrayList<>());
		
		return null;
	}

}
