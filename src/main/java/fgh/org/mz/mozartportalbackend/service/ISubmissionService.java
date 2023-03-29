package fgh.org.mz.mozartportalbackend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fgh.org.mz.mozartportalbackend.model.Submission;
import fgh.org.mz.mozartportalbackend.model.User;

public interface ISubmissionService {
	List<Submission> findAllSubmission(); 
	Submission findOne(final long id);
	Submission createSubmission(Submission submission);
	Submission updateSubmission(Submission submission);
	Page<Submission> findByUser(User user, Pageable pageable);
}
