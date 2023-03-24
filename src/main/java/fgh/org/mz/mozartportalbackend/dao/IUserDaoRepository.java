package fgh.org.mz.mozartportalbackend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fgh.org.mz.mozartportalbackend.model.User;

@Repository
public interface IUserDaoRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
