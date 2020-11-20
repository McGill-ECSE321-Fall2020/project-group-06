package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.User;

/**
 * User Service Class
 * 
 * @author Sen Wang
 * @author Noah Chamberland
 * @author Justin Legrand
 * @author Olivier Normandin
 * @author Andre-Walter Panzini
 */
@Service
public class UsersService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Save user to database
	 * 
	 * @param user
	 * @return boolean true if successful
	 */
	@Transactional
	public Boolean saveUser(User user) {
		// a user with this username already exist
		if (userRepository.findUserByUsername(user.getUsername()) != null) {
			return false;
		} else {
			userRepository.save(user);
			return true;
		}
	}

	/**
	 * Update user in database
	 * 
	 * @param user
	 * @return boolean true if successful
	 */
	@Transactional
	public Boolean updateUser(User user) {
		// a user with this username does not exist
		if (userRepository.findUserByUsername(user.getUsername()) == null) {
			return false;
		} else {
			User updatedUser = new User();
			updatedUser = userRepository.findUserByUsername(user.getUsername());
			updatedUser.setDescription(user.getDescription());
			updatedUser.setEmail(user.getEmail());
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			updatedUser.setPhoneNumber(user.getPhoneNumber());
			updatedUser.setPicture(user.getPicture());
			userRepository.save(updatedUser);
			return true;
		}
	}

	/**
	 * Get user by id
	 * 
	 * @param int userId
	 * @return User found by Id
	 */
	@Transactional
	public User getUserById(int userId) {
		return userRepository.findUserById(userId);
	}

	/**
	 * Delete User by Id
	 * 
	 * @param int userId
	 * @return boolean true if successful
	 */
	@Transactional
	public Boolean deleteUserById(int userId) {
		if (userRepository.findUserById(userId) == null) {
			return false;
		} else {
			userRepository.deleteById(userId);
			return true;
		}
	}

	@Transactional
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

}
