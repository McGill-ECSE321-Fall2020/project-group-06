package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Picture;
import ca.mcgill.ecse321.artgallery.model.Transaction;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArtGalleryPersistence {

	// TESTS TODO
	// Here is a test example
	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ArtworkRepository artworkRepository;

	@Autowired
	PictureRepository pictureRepository;

	@Autowired
	TransactionRepository transactionRepository;

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
		user.setId(1);
		userRepository.save(user);
		user = null;
		user = userRepository.findUserByUsername("chaggy");
		assertNotNull(user);
		assertEquals("chaggy", user.getUsername());
	}

	// Other tests made by Justin

	// @Test
	// public void testArtist() {
	// Artist artist = new Artist();
	// artist.setFirstName("John");
	// artist.setLastName("Smith");
	// artist.setId(101);
	// artistRepository.save(artist);
	// Artist oldArtist = artistRepository.findArtistById(101);
	// assertNotNull(oldArtist);
	// assertEquals(artist, oldArtist);
	// }

	// @Test
	// public void testCustomer() {
	// Customer customer = new Customer();
	// customer.setFirstName("Sidney");
	// customer.setLastName("Crosby");
	// customer.setId(87);
	// customerRepository.save(customer);
	// Customer oldCustomer = customerRepository.findCustomerById(87);
	// assertNotNull(oldCustomer);
	// assertEquals(customer, oldCustomer);
	// }

	// @Test
	// public void testArtGallery() {
	// ArtGallery artGallery = new ArtGallery();
	// artGallery.setName("VanGoghEstNous");
	// artGallery.setId(2);
	// artGalleryRepository.save(artGallery);
	// ArtGallery oldGallery = artGalleryRepository.findArtGalleryById(2);
	// assertNotNull(oldGallery);
	// assertEquals(artGallery, oldGallery);
	// }

	// @Test
	// public void testArtwork() {
	// Artwork artwork = new Artwork();
	// artwork.setName("Starry Nights");
	// artwork.setId(3);
	// artworkRepository.save(artwork);
	// Artwork oldArtwork = artworkRepository.findArtworkById(3);
	// assertNotNull(oldArtwork);
	// assertEquals(artwork, oldArtwork);
	// }

	// @Test
	// public void testPicture() {
	// Picture picture = new Picture();
	// picture.setId(4);
	// pictureRepository.save(picture);
	// Picture oldPicture = pictureRepository.findPictureById(4);
	// assertNotNull(oldPicture);
	// assertEquals(picture, oldPicture);
	// }

	// @Test
	// public void testTransaction() {
	// Transaction transaction = new Transaction();
	// transaction.setCommisionCut(0.15);
	// transaction.setId(5);
	// transactionRepository.save(transaction);
	// Transaction oldTransaction = transactionRepository.findTransactionById(5);
	// assertNotNull(oldTransaction);
	// assertEquals(transaction, oldTransaction);
	// }

}
