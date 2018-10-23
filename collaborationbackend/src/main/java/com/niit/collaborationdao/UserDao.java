package com.niit.collaborationdao;

import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.User;
@Repository
public interface UserDao {

	void registration(User user);
	boolean isEmailUnique(String email);
	User login(User user);
	void updateUser(User user);
	User getUser(String email);
}
