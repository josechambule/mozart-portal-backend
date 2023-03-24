package fgh.org.mz.mozartportalbackend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fgh.org.mz.mozartportalbackend.rest.auth.AuthenticationRequest;
import fgh.org.mz.mozartportalbackend.rest.auth.AuthenticationResponse;
import fgh.org.mz.mozartportalbackend.rest.auth.RegisterRequest;
import fgh.org.mz.mozartportalbackend.service.IAuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {

	private IAuthenticationService service;

	@Autowired
	public AuthenticationController(IAuthenticationService service) {
		this.service = service;
	}

	@PostMapping("/sign")
	public ResponseEntity<AuthenticationResponse> sign(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.sign(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.login(request));
	}

}
