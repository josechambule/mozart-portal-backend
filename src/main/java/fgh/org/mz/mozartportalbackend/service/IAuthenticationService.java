package fgh.org.mz.mozartportalbackend.service;

import fgh.org.mz.mozartportalbackend.rest.auth.AuthenticationRequest;
import fgh.org.mz.mozartportalbackend.rest.auth.AuthenticationResponse;
import fgh.org.mz.mozartportalbackend.rest.auth.RegisterRequest;

public interface IAuthenticationService {
	AuthenticationResponse sign(RegisterRequest request);
	AuthenticationResponse login(AuthenticationRequest request);
}
