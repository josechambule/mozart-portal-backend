package fgh.org.mz.mozartportalbackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fgh.org.mz.mozartportalbackend.model.User;
import fgh.org.mz.mozartportalbackend.service.IUserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserRestController {
	
	
	private IUserService userService;

	@Autowired
	public UserRestController(IUserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/auth")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping("/auth/{userId}")
	public User getUser(@PathVariable int userId) {
		User user = userService.findOne(userId);
		if (user == null) {
			throw new NotFoundException("User Id not found " + userId);
		}
		return user;
	}
	
	@PostMapping("/auth")
	public User addUser(@RequestBody User user) {
		User saveUser = userService.createOrUpdateUser(user);
		return saveUser;
	}
	
	@PutMapping("/auth")
	public User updateUser(@RequestBody User user) {
		User updateUser = userService.createOrUpdateUser(user);
		return updateUser;
	}
	
	@DeleteMapping("/auth/{userId}")
	public String deleteUser(@PathVariable int userId) {
		User deleteUser = userService.findOne(userId);
		
		if (deleteUser == null) {
			throw new NotFoundException("User id not found - " + userId);
		}
		
		userService.deleteUser(userId);
		
		return "Deleted user with id - " + userId;
	}

}
