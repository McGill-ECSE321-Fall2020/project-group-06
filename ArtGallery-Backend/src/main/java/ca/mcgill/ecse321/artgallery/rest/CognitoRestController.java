package ca.mcgill.ecse321.artgallery.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.model.AuthRequest;
import ca.mcgill.ecse321.artgallery.services.CognitoService;
import ca.mcgill.ecse321.artgallery.util.JwtUtil;

@RestController
@RequestMapping("/api/cognito")
public class CognitoRestController {

    private static final Logger logger = LoggerFactory.getLogger(CognitoRestController.class);

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CognitoService cognitoService;

    /**
     * Login http requeast endpoint
     * 
     * @param AuthRequest
     * @return JWT Token
     * @author Sen Wang
     */

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (Exception e) {
            throw new Exception("invalid username/password");
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }

    /**
     * Change password http requeast endpoint
     * 
     * @param AuthRequest
     * @return
     * @author Sen Wang
     */

    @PutMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody AuthRequest authRequest) {

        logger.info("changing password");

        try {
            Boolean foundUser = cognitoService.changePassword(authRequest.getUserName(), authRequest.getPassword());
            if (foundUser) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            logger.info("Exception when changing password: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
