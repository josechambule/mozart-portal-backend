package fgh.org.mz.mozartportalbackend.service;

import java.util.List;

import fgh.org.mz.mozartportalbackend.dao.IGenericDao;
import fgh.org.mz.mozartportalbackend.model.Submission;

public interface ISubmissionService {
	void setDao(IGenericDao<Submission> daoToSet);
	List<Submission> findAllSubmission(); 
	Submission findOne(final long id);
	Submission createSubmission(Submission submission);
}
