package fgh.org.mz.mozartportalbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fgh.org.mz.mozartportalbackend.dao.IGenericDao;
import fgh.org.mz.mozartportalbackend.model.Submission;

@Service
public class SubmissionService implements ISubmissionService {
	
	private IGenericDao<Submission> dao;
	
	@Autowired
	@Override
	public void setDao(IGenericDao<Submission> daoToSet) {
		dao = daoToSet;
	    dao.setClazz(Submission.class);		
	}

	@Override
	public List<Submission> findAllSubmission() {
		return dao.findAll();
	}

	@Override
	public Submission findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	@Transactional
	public Submission createSubmission(Submission submission) {
		return dao.create(submission);
	}

}
