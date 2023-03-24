package fgh.org.mz.mozartportalbackend.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fgh.org.mz.mozartportalbackend.dao.IUserDaoRepository;
import fgh.org.mz.mozartportalbackend.model.Submission;
import fgh.org.mz.mozartportalbackend.model.User;
import fgh.org.mz.mozartportalbackend.service.ISubmissionService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class SubmissionRestController {
	
	private ISubmissionService submissionService;
	private IUserDaoRepository userService;
	
	@Autowired
	public SubmissionRestController(ISubmissionService submissionService, IUserDaoRepository userService) {
		this.submissionService = submissionService;
		this.userService = userService;
	}

	@GetMapping("/submission")
	public List<Submission> getAllSubmission() {		
		return submissionService.findAllSubmission();
	}

	@GetMapping("/submission/{submissionId}")
	public Submission getSubmission(@PathVariable int submissionId) {
		if (submissionService.findOne(submissionId) == null) {
			throw new NotFoundException("Submission Id not found " + submissionId);
		}
		return submissionService.findOne(submissionId);
	}
	
	@PostMapping("/submission")
	public ResponseEntity<Submission> submission(@RequestBody Submission submission){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<User> user = userService.findByUsername(userDetails.getUsername());
		submission.setUser(user.get());
		return ResponseEntity.ok(submissionService.createSubmission(submission));
	}
	
	@PutMapping("/submission")
	public ResponseEntity<Submission> updateSubmission(@RequestBody Submission submission){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<User> user = userService.findByUsername(userDetails.getUsername());
		submission.setUser(user.get());
		return ResponseEntity.ok(submissionService.createSubmission(submission));
	}
}
