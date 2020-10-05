package ca.mcgill.ecse321.artgallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	
//	@Autowired
//	UserRepository userRepository;
//
//	@RequestMapping("/")
//	public String greeting(){
//		return "Hello world!";
//	}
//	
//	@RequestMapping("test")
//	public User test() {
//		User user = new User();
//		user.setUsername("chaggy");
//		userRepository.save(user);
//		return userRepository.findUserByUsername("chaggy");
//	}

}
