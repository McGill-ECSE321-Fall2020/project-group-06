package ca.mcgill.ecse321.artgallery;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;
import ca.mcgill.ecse321.artgallery.dao.PictureRepository;
import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.Picture;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.User;

@RestController
@SpringBootApplication
public class ArtGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtGalleryApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerRepository customerRespository;

	@Autowired
	ArtistRepository ArtistRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	ArtGalleryRepository artGalleryRepository;

	@Autowired
	ArtworkRepository ArtworkRepository;

	@Autowired
	PictureRepository pictureRepository;

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

	@RequestMapping("/customerTest")
	public Optional<Customer> customerTest() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setCreditCardNumber(1234);
		customerRespository.save(customer);
		return customerRespository.findById(1);
	}

	@RequestMapping("/delete")

	public void delete() {
		transactionRepository.deleteAll();
	}

	@RequestMapping("/TransactionTest")
	public Optional<Transaction> transactionTest() {
		Transaction transaction = new Transaction();
		Artist artist = new Artist();
		artist.setId(2);
		ArtistRepository.save(artist);
		Customer customer = new Customer();
		customer.setId(3);
		customer.setCreditCardNumber(1234);
		customerRespository.save(customer);
		ArtGallery artGallery = new ArtGallery();
		artGallery.setId(1);
		artGalleryRepository.save(artGallery);
		Artwork artwork = new Artwork();
		artwork.setId(1);
		artwork.setArtGallery(artGallery);
		artwork.setArtist(artist);
		ArtworkRepository.save(artwork);
		transaction.setArtist(artist);
		transaction.setCustomer(customer);
		transaction.setArtGallery(artGallery);
		transaction.setArtwork(artwork);
		transaction.setId(1);
		transactionRepository.save(transaction);
		return transactionRepository.findById(1);
	}

	@GetMapping("/user/{userId}") // What is this???: get a specific user by Id from the db. (Sen)
	public ResponseEntity<Optional<User>> findUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(userRepository.findById(userId));
	}

}
