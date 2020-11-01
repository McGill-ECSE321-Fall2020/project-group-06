package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.User;

/**
 * <p>
 * CognitoServices: methods used to sign up, sign in, change password etc
 * </p>
 */
@Service
public class CognitoService {

    @Autowired
    UserRepository userRepository;

    /**
     * Change password
     * 
     * @param username
     * @param newPassword
     * @return boolean
     */
    @Transactional
    public Boolean changePassword(String username, String newPassword) {
        // get the user
        User user = userRepository.findUserByUsername(username);

        // return false if user does not exist
        if (user == null) {
            return false;
        }

        // set new password
        user.setPassword(newPassword);
        // save to db
        userRepository.save(user);

        // return true if found user
        return true;
    }

}
