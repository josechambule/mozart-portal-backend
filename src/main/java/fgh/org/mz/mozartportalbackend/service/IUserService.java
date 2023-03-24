package fgh.org.mz.mozartportalbackend.service;

import java.util.List;

import fgh.org.mz.mozartportalbackend.model.User;

public interface IUserService {
	List<User> findAllUsers(); 
	User findOne(final long id);
	User createOrUpdateUser(User user);
	void deleteUser(long id);
}
