package fgh.org.mz.mozartportalbackend.rest.auth;

import java.util.List;

import fgh.org.mz.mozartportalbackend.model.Role;

public class RegisterRequest {
	
	private String name;
	
	private String username;
	
	private String password;
	
	private List<Role> role;
	
	public RegisterRequest() {}

	public RegisterRequest(String name, String username, String password, List<Role> role) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

}
