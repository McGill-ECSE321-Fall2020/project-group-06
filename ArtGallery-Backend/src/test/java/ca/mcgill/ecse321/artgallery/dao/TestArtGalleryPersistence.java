package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Month;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Picture;
import ca.mcgill.ecse321.artgallery.model.Transaction;


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

	@Test
	public void testPersistenceAndLoadCustomer(){
		Customer customer = new Customer();
		HashSet<Artwork> artworks = new HashSet<Artwork>();
		Artwork artwork = new Artwork();
		artworks.add(artwork);
		artworkRepository.save(artwork);
		long creditCardNumber = 1111222233334444L;
		String description = "My Description";
		String email = "my.email@gmail.com";
		String firstName = "John";
		int id = 12345;
		String lastName = "Doe";
		String phoneNumber = "5147777777";
		Picture picture = new Picture();
		pictureRepository.save(picture);
		HashSet<Transaction> transactions = new HashSet<Transaction>();
		Transaction transaction = new Transaction();
		transactions.add(transaction);
		transactionRepository.save(transaction);
		String username = "myUsername";
		customer.setArtwork(artworks);
		customer.setCreditCardNumber(creditCardNumber);
		customer.setDescription(description);
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setId(id);
		customer.setLastName(lastName);
		customer.setPhoneNumber(phoneNumber);
		customer.setPicture(picture);
		customer.setTransaction(transactions);
		customer.setUsername(username);
		customerRepository.save(customer);
		customer = null;
		customer = customerRepository.findCustomerById(1);
		assertNotNull(customer);
		assertEquals(artwork, customer.getArtwork());
		assertEquals(creditCardNumber, customer.getCreditCardNumber());
		assertEquals(description, customer.getDescription());
		assertEquals(email, customer.getEmail());
		assertEquals(firstName, customer.getFirstName());
		assertEquals(id, customer.getId());
		assertEquals(lastName, customer.getLastName());
		assertEquals(phoneNumber, customer.getPhoneNumber());
		assertEquals(picture, customer.getPicture());
		assertEquals(transactions, customer.getTransaction());
		assertEquals(username, customer.getUsername());
	}

	@Test
	public void testPersistenceAndDeleteCustomer(){
		Customer customer = new Customer();
		HashSet<Artwork> artworks = new HashSet<Artwork>();
		Artwork artwork = new Artwork();
		artworks.add(artwork);
		long creditCardNumber = 1111222233334444L;
		int id = 12345;
		customer.setArtwork(artworks);
		customer.setCreditCardNumber(creditCardNumber);
		customer.setId(id);
		customerRepository.save(customer);
		customer = null;
		customer = customerRepository.findCustomerById(12345);
		assertNotNull(customer);
		assertEquals(artwork, customer.getArtwork());
		assertEquals(creditCardNumber, customer.getCreditCardNumber());
		assertEquals(id, customer.getId());
		customerRepository.delete(customer);
		customer = null;
		customer = customerRepository.findCustomerById(12345);
		assertNull(customer);
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
