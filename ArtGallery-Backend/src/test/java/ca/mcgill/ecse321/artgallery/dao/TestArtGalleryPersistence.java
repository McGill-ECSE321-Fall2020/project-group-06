package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	// Other tests
	// made by Justin

	@Test
	public void testPersistenceAndLoadArtist() {
		Artist artist = new Artist();
		artist.setFirstName("John");
		artist.setLastName("Smith");
		artist.setId(101);
		artistRepository.save(artist);
		Artist oldArtist = artistRepository.findArtistById(101);
		assertNotNull(oldArtist);
		assertEquals(artist.getId(), oldArtist.getId());
	}

	@Test
	public void testPersistenceAndLoadCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Sidney");
		customer.setLastName("Crosby");
		customer.setId(87);
		customerRepository.save(customer);
		Customer oldCustomer = customerRepository.findCustomerById(87);
		assertNotNull(oldCustomer);
		assertEquals(customer.getId(), oldCustomer.getId());
	}

	@Test
	public void testPersistenceAndLoadArtGallery() {
		ArtGallery artGallery = new ArtGallery();
		artGallery.setName("VanGoghEstNous");
		artGallery.setId(2);
		artGalleryRepository.save(artGallery);
		ArtGallery oldGallery = artGalleryRepository.findArtGalleryById(2);
		assertNotNull(oldGallery);
		assertEquals(artGallery.getId(), oldGallery.getId());
	}

	@Test
	public void testPersistenceAndLoadArtwork() {
		Artwork artwork = new Artwork();
		artwork.setName("Starry Nights");
		artwork.setId(3);
		artworkRepository.save(artwork);
		Artwork oldArtwork = artworkRepository.findArtworkById(3);
		assertNotNull(oldArtwork);
		assertEquals(artwork.getId(), oldArtwork.getId());
	}

	@Test
	public void testPersistenceAndLoadPicture() {
		Picture picture = new Picture();
		picture.setId(4);
		pictureRepository.save(picture);
		Picture oldPicture = pictureRepository.findPictureById(4);
		assertNotNull(oldPicture);
		assertEquals(picture.getId(), oldPicture.getId());
	}

	@Test
	public void testPersistenceAndLoadSaveTransaction() {
		Transaction transaction = new Transaction();
		transaction.setCommisionCut(0.15);
		transaction.setId(5);
		transactionRepository.save(transaction);
		Transaction oldTransaction = transactionRepository.findTransactionById(5);
		assertNotNull(oldTransaction);
		assertEquals(transaction.getId(), oldTransaction.getId());
	}

}
