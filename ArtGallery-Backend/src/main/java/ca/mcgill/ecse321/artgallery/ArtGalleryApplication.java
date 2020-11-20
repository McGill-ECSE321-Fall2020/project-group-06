package ca.mcgill.ecse321.artgallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ArtGalleryApplication {

	// main application
	public static void main(String[] args) {
		SpringApplication.run(ArtGalleryApplication.class, args);
	}

	// default http endpoint
	@RequestMapping("/")
	public String greeting() {
		return "Hello world from art-gallery backend group-06!";
	}

}
