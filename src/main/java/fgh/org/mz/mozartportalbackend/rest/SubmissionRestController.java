package fgh.org.mz.mozartportalbackend.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fgh.org.mz.mozartportalbackend.dao.IUserDaoRepository;
import fgh.org.mz.mozartportalbackend.model.Submission;
import fgh.org.mz.mozartportalbackend.model.User;
import fgh.org.mz.mozartportalbackend.rest.auth.SubmissionResponse;
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
	public ResponseEntity<Map<String, Object>> getAllSubmission(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			Optional<User> user = userService.findByUsername(userDetails.getUsername());

			Pageable paging = PageRequest.of(page, size);
			Page<Submission> pageSubmission = submissionService.findByUser(user.get(), paging);
			List<Submission> submission = pageSubmission.getContent();

			List<SubmissionResponse> listSubmission = new ArrayList<SubmissionResponse>();
			for (Submission sub : submission) {
				listSubmission.add(new SubmissionResponse(sub));
			}

			Map<String, Object> response = new HashMap<>();
			response.put("submission", listSubmission);
			response.put("currentPage", pageSubmission.getNumber());
			response.put("totalItems", pageSubmission.getTotalElements());
			response.put("totalPages", pageSubmission.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/submission/{submissionId}")
	public ResponseEntity<SubmissionResponse> getSubmission(@PathVariable int submissionId) {
		if (submissionService.findOne(submissionId) == null) {
			throw new NotFoundException("Submission Id not found " + submissionId);
		}
		SubmissionResponse sub = new SubmissionResponse(submissionService.findOne(submissionId));
		return ResponseEntity.ok(sub);
	}

	@PostMapping("/submission")
	public ResponseEntity<SubmissionResponse> submission(@RequestBody Submission submission) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<User> user = userService.findByUsername(userDetails.getUsername());
		submission.setUser(user.get());
		SubmissionResponse sub = new SubmissionResponse(submissionService.createSubmission(submission));
		return ResponseEntity.ok(sub);
	}

	@PutMapping("/submission")
	public ResponseEntity<SubmissionResponse> updateSubmission(@RequestBody Submission submission) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<User> user = userService.findByUsername(userDetails.getUsername());
		submission.setUser(user.get());
		SubmissionResponse sub = new SubmissionResponse(submissionService.updateSubmission(submission));
		return ResponseEntity.ok(sub);
	}
}
