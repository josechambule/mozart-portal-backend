package fgh.org.mz.mozartportalbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().and()
				.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/auth").hasAnyAuthority("DATAENTRY","MANAGER","ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/v1/auth/**").hasAnyAuthority("DATAENTRY","MANAGER","ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/v1/submission").hasAnyAuthority("DATAENTRY","MANAGER","ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/v1/submission").hasAnyAuthority("DATAENTRY","MANAGER","ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/v1/auth").hasAnyAuthority("MANAGER","ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/v1/auth").hasAnyAuthority("MANAGER","ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/auth/**").hasRole("ADMIN")
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		http.httpBasic();
		return http.build();
	}

}
