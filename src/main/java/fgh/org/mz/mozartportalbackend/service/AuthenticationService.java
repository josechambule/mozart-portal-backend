package fgh.org.mz.mozartportalbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fgh.org.mz.mozartportalbackend.dao.IUserDaoRepository;
import fgh.org.mz.mozartportalbackend.model.Role;
import fgh.org.mz.mozartportalbackend.model.User;
import fgh.org.mz.mozartportalbackend.rest.auth.AuthenticationRequest;
import fgh.org.mz.mozartportalbackend.rest.auth.AuthenticationResponse;
import fgh.org.mz.mozartportalbackend.rest.auth.RegisterRequest;
import fgh.org.mz.mozartportalbackend.security.JwtService;

@Service
public class AuthenticationService implements IAuthenticationService {

	private final IUserDaoRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(IUserDaoRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationResponse sign(RegisterRequest request) {
		User user = new User();

		user.setName(request.getName());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		user.setActive(true);

		repository.save(user);

		String jwtToken = jwtService.generateToken(user);
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setToken(jwtToken);
		authResponse.setName(user.getName());
		authResponse.setUsername(user.getUsername());

		return authResponse;
	}

	public AuthenticationResponse login(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		Optional<User> user = repository.findByUsername(request.getUsername());

		String jwtToken = jwtService.generateToken(user.get());
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setToken(jwtToken);
		authResponse.setName(user.get().getName());
		authResponse.setUsername(user.get().getUsername());
		
		List<String> role = new ArrayList<String>();
		
		for (Role strRole :user.get().getRole()) {
			role.add(strRole.getRole());
		}
		
		authResponse.setRole(role);

		return authResponse;
	}

}
