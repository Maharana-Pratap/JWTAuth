package com.example.oppoUser.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.oppoUser.entity.MyUser;

public interface UserDao extends JpaRepository<MyUser, Integer>{

	@Query(value="From MyUser u where u.user_name = ?1")
	public Optional<MyUser> findUserByName(String name);
}
