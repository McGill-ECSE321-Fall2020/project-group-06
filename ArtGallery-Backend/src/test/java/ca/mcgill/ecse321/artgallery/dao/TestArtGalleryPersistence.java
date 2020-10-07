package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.Picture;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArtGalleryPersistence {

	// TESTS TODO
	// Tests must check for all attributes.
	// One test for save and one for delete for each entity
	// Here is a test example

	// Andre
	@Autowired
	private ArtistRepository artistRepository;

	// Sen
	@Autowired
	private UserRepository userRepository;

	// Noah
	@Autowired
	CustomerRepository customerRepository;

	// Justin
	@Autowired
	ArtworkRepository artworkRepository;

	// Justin
	@Autowired
	PictureRepository pictureRepository;

	// Oliver
	@Autowired
	TransactionRepository transactionRepository;

	// Justin
	@Autowired
	ArtGalleryRepository artGalleryRepository;

	@AfterEach
	public void clearDatabase() {
		artistRepository.deleteAll();
		userRepository.deleteAll();
		customerRepository.deleteAll();
		artworkRepository.deleteAll();
		pictureRepository.deleteAll();
		transactionRepository.deleteAll();
		artGalleryRepository.deleteAll();
	}

	@Test
	public void testPersistenceAndLoadUser() {
		User user = new User();
		user.setUsername("chaggy");
		user.setPassword("password");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setDescription("Hi, my name is John Doe and I'm under the water");
		user.setEmail("John@email.com");
		user.setPhoneNumber("1111111");
		user.setId(2);
		userRepository.save(user);
		user = null;
		user = userRepository.findUserByUsername("chaggy");
		assertNotNull(user);
		assertEquals("chaggy", user.getUsername());
		assertEquals("password", user.getPassword());
		assertEquals("John", user.getFirstName());
		assertEquals("Doe", user.getLastName());
		assertEquals("John@email.com", user.getEmail());
		assertEquals("1111111", user.getPhoneNumber());
		userRepository.deleteAll();
	}

	// @Test
	// public void testPersistenceAndDeleteUser() {
	// User user = new User();
	// user.setId(2);
	// user.setUsername("user");
	// userRepository.save(user);
	// userRepository.deleteById(2);
	// user = null;
	// user = userRepository.findUserByUsername("user");
	// assertNull(user);
	// }

	// Other tests
	// made by Justin

	// @Test
	// public void testPersistenceAndLoadArtist() {
	// Artist artist = new Artist();
	// artist.setFirstName("John");
	// artist.setLastName("Smith");
	// artist.setId(101);
	// artistRepository.save(artist);
	// Artist oldArtist = artistRepository.findArtistById(101);
	// assertNotNull(oldArtist);
	// assertEquals(artist.getId(), oldArtist.getId());
	// }

	// @Test
	// public void testPersistenceAndLoadCustomer() {
	// Customer customer = new Customer();
	// customer.setFirstName("Sidney");
	// customer.setLastName("Crosby");
	// customer.setId(87);
	// customerRepository.save(customer);
	// Customer oldCustomer = customerRepository.findCustomerById(87);
	// assertNotNull(oldCustomer);
	// assertEquals(customer.getId(), oldCustomer.getId());
	// }

	// @Test
	// public void testPersistenceAndLoadArtGallery() {
	// ArtGallery artGallery = new ArtGallery();
	// artGallery.setName("VanGoghEstNous");
	// artGallery.setId(2);
	// artGalleryRepository.save(artGallery);
	// ArtGallery oldGallery = artGalleryRepository.findArtGalleryById(2);
	// assertNotNull(oldGallery);
	// assertEquals(artGallery.getId(), oldGallery.getId());
	// }

	// @Test
	// public void testPersistenceAndLoadArtwork() {
	// Artwork artwork = new Artwork();
	// artwork.setName("Starry Nights");
	// artwork.setId(3);
	// artworkRepository.save(artwork);
	// Artwork oldArtwork = artworkRepository.findArtworkById(3);
	// assertNotNull(oldArtwork);
	// assertEquals(artwork.getId(), oldArtwork.getId());
	// }

	// @Test
	// public void testPersistenceAndLoadPicture() {
	// Picture picture = new Picture();
	// picture.setId(4);
	// pictureRepository.save(picture);
	// Picture oldPicture = pictureRepository.findPictureById(4);
	// assertNotNull(oldPicture);
	// assertEquals(picture.getId(), oldPicture.getId());
	// }

	// @Test
	// public void testPersistenceAndLoadSaveTransaction() {
	// Transaction transaction = new Transaction();
	// transaction.setCommisionCut(0.15);
	// transaction.setId(5);
	// transactionRepository.save(transaction);
	// Transaction oldTransaction = transactionRepository.findTransactionById(5);
	// assertNotNull(oldTransaction);
	// assertEquals(transaction.getId(), oldTransaction.getId());
	// }

}
