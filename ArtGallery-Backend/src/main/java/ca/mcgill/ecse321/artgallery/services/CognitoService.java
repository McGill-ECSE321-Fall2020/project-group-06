package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.User;

/**
 * Cognito Service Class: Used for authentication
 * @author Sen Wang
 * @author Noah Chamberland
 * @author Justin Legrand
 * @author Olivier Normandin
 * @author Andre-Walter Panzini
 */
@Service
public class CognitoService {

    @Autowired
    UserRepository userRepository;

    /**
     * Change password
     * 
     * @param String username
     * @param String newPassword
     * @return boolean true if successful
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
