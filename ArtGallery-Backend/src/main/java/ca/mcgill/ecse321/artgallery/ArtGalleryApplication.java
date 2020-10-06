package ca.mcgill.ecse321.artgallery;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.User;

@RestController
@SpringBootApplication
public class ArtGalleryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtGalleryApplication.class, args);
    }

    // RANDOM TEST
    // TODO 

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String greeting() {
        return "Hello world!";
    }

    @RequestMapping("/userTest")
    public User test() {
        User user = new User();
        user.setUsername("chaggy");
        user.setId(1);
        userRepository.save(user);
        return userRepository.findUserByUsername("chaggy");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userRepository.findById(userId));
    }

}
