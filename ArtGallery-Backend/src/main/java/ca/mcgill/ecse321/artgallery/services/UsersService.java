package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
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
}
