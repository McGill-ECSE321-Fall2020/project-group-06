package ca.mcgill.ecse321.artgallery.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.services.UsersService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UsersService usersService;

    /**
     * REQ1.1(A): The art gallery system shall allow the user to register a new
     * account.
     * 
     * @param user
     * @return
     */
    @PostMapping("/createUser")
    public ResponseEntity<Void> createUserProfile(@Valid @RequestBody User user) {

        logger.info("creating user profile");

        if (user.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {

            if (usersService.saveUser(user) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }

        } catch (Exception e) {
            logger.error("Exception when creating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/updateUser")
    public ResponseEntity<Void> updateUserProfile(@Valid @RequestBody User user) {

        logger.info("updating user profile");

        if (user.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (usersService.updateUser(user) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception e) {
            logger.error("Exception when updating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        logger.info("getting user");
        if (username == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            if (usersService.getUserByUsername(username) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.ok(usersService.getUserByUsername(username));
            }
        } catch (Exception e) {
            logger.error("Exception when updating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
}
