package ca.mcgill.ecse321.artgallery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

	// RANDOM TEST
	// TODO

	@Autowired
	UserRepository userRepository;
//	ArtistRepository artistRepository;
//	CustomerRepository customerRepository;
//	ArtworkRepository artworkRepository;
//	PictureRepository pictureRepository;
//	TransactionRepository transactionRepository;
//	ArtGalleryRepository artGalleryRepository;

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

//	public void testArtist() {
//		Artist artist = new Artist();
//		artist.setFirstName("John");
//		artist.setLastName("Smith");
//		artist.setId(101);
//		artistRepository.save(artist);
//		Artist oldArtist = artistRepository.findArtistById(101);
//		assertNotNull(oldArtist);
//		assertEquals(artist, oldArtist);
//	}
//
//	public void testCustomer() {
//		Customer customer = new Customer();
//		customer.setFirstName("Sidney");
//		customer.setLastName("Crosby");
//		customer.setId(87);
//		customerRepository.save(customer);
//		Customer oldCustomer = customerRepository.findCustomerById(87);
//		assertNotNull(oldCustomer);
//		assertEquals(customer, oldCustomer);
//	}
//
//	public void testArtGallery() {
//		ArtGallery artGallery = new ArtGallery();
//		artGallery.setName("VanGoghEstNous");
//		artGallery.setId(2);
//		artGalleryRepository.save(artGallery);
//		ArtGallery oldGallery = artGalleryRepository.findArtGalleryById(2);
//		assertNotNull(oldGallery);
//		assertEquals(artGallery, oldGallery);
//	}
//
//	public void testArtwork() {
//		Artwork artwork = new Artwork();
//		artwork.setName("Starry Nights");
//		artwork.setId(3);
//		artworkRepository.save(artwork);
//		Artwork oldArtwork = artworkRepository.findArtworkById(3);
//		assertNotNull(oldArtwork);
//		assertEquals(artwork, oldArtwork);
//	}
//
//	public void testPicture() {
//		Picture picture = new Picture();
//		picture.setId(4);
//		pictureRepository.save(picture);
//		Picture oldPicture = pictureRepository.findPictureById(4);
//		assertNotNull(oldPicture);
//		assertEquals(picture, oldPicture);
//	}
//
//	public void testTransaction() {
//		Transaction transaction = new Transaction();
//		transaction.setCommisionCut(0.15);
//		transaction.setId(5);
//		transactionRepository.save(transaction);
//		Transaction oldTransaction = transactionRepository.findTransactionById(5);
//		assertNotNull(oldTransaction);
//		assertEquals(transaction, oldTransaction);
//	}

	@GetMapping("/user/{userId}") // What is this???: get a specific user by Id from the db. (Sen)
	public ResponseEntity<Optional<User>> findUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(userRepository.findById(userId));
	}

}
