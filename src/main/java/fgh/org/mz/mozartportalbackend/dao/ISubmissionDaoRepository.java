package fgh.org.mz.mozartportalbackend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fgh.org.mz.mozartportalbackend.model.Submission;
import fgh.org.mz.mozartportalbackend.model.User;

@Repository
public interface ISubmissionDaoRepository extends JpaRepository<Submission, Long> {
	Page<Submission> findByUser(User user, Pageable pageable);
}
