package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.User;

/**
 * <p>
 * UserServices : Methods used to crud user details
 * </p>
 */
@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public Boolean saveUser(User user) {
        // a user with this username already exist
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

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
            updatedUser.setPhoneNumber(user.getPassword());
            updatedUser.setPicture(user.getPicture());
            userRepository.save(updatedUser);
            return true;
        }
    }

    public User getUserById(int userId) {
        return userRepository.findUserById(userId);
    }

    public Boolean deleteUserById(int userId) {
        if (userRepository.findUserById(userId) == null) {
            return false;
        } else {
            userRepository.deleteById(userId);
            return true;
        }
    }

}
