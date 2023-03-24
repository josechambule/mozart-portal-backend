package fgh.org.mz.mozartportalbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fgh.org.mz.mozartportalbackend.dao.IUserDaoRepository;
import fgh.org.mz.mozartportalbackend.model.User;
import fgh.org.mz.mozartportalbackend.rest.NotFoundException;

@Service
public class UserService implements IUserService {

	private IUserDaoRepository userDao;

	@Autowired
	public UserService(IUserDaoRepository userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User findOne(long id) {
		Optional<User> user = userDao.findById(id);
		if (!user.isPresent()) {
			throw new NotFoundException("User Id not found " + id);
		}

		return user.get();
	}

	@Override
	public User createOrUpdateUser(User user) {
		return userDao.save(user);
	}

	@Override
	public void deleteUser(long id) {
		userDao.deleteById(id);
		;
	}

}
