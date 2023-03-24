package fgh.org.mz.mozartportalbackend.rest.auth;

public class AuthenticationResponse {
	
	private String token;
	
	private String name;
	
	private String username;
	
	public AuthenticationResponse() {}

	public AuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

}
