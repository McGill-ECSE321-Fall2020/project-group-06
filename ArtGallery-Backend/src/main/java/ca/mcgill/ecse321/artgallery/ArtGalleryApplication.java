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

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

	@RequestMapping("/userTest")
	public User testUser() {
		User user = new User();
		user.setUsername("chaggy");
		user.setId(1);
		userRepository.save(user);
		return userRepository.findUserByUsername("chaggy");
	}

	@GetMapping("/user/{userId}") // What is this???: get a specific user by Id from the db. (Sen)
	public ResponseEntity<Optional<User>> findUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(userRepository.findById(userId));
	}

}
