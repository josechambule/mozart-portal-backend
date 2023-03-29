package fgh.org.mz.mozartportalbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fgh.org.mz.mozartportalbackend.dao.ISubmissionDaoRepository;
import fgh.org.mz.mozartportalbackend.model.Submission;
import fgh.org.mz.mozartportalbackend.model.User;
import fgh.org.mz.mozartportalbackend.rest.NotFoundException;

@Service
public class SubmissionService implements ISubmissionService {
	
	private ISubmissionDaoRepository submissionDao;
	
	@Autowired
	public SubmissionService(ISubmissionDaoRepository submissionDao) {
		this.submissionDao = submissionDao;
	}

	@Override
	public List<Submission> findAllSubmission() {
		return submissionDao.findAll();
	}

	@Override
	public Submission findOne(long id) {
		Optional<Submission> submission = submissionDao.findById(id);
		if (!submission.isPresent()) {
			throw new NotFoundException("Submission Id not found " + id);
		}
		return submission.get();
	}

	@Override
	public Submission createSubmission(Submission submission) {
		return submissionDao.save(submission);
	}

	@Override
	public Submission updateSubmission(Submission submission) {
		return submissionDao.save(submission);
	}

	@Override
	public Page<Submission> findByUser(User user, Pageable pageable) {
		return submissionDao.findByUser(user, pageable);
	}

}
