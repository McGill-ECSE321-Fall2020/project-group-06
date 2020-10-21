package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Month;
import java.time.LocalDate;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Artwork.TypeOfArtwork;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.Picture;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;
import ca.mcgill.ecse321.artgallery.model.User;

/**
 * @author Sen Wang
 * @author Noah Chamberland
 * @author Justin Legrand
 * @author Olivier Normandin
 * @author André-Walter Panzini
 *         <p>
 *         Persistence Layer Unit Tests. Each method tests the save or delete
 *         functionality of a single entity.
 *         </p>
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArtGalleryPersistence {

	// Dependency Injections
	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ArtworkRepository artworkRepository;

	@Autowired
	private PictureRepository pictureRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ArtGalleryRepository artGalleryRepository;

	// Clear Database
	@AfterEach
	public void clearDatabase() {
		transactionRepository.deleteAll();
		artworkRepository.deleteAll();
		artistRepository.deleteAll();
		customerRepository.deleteAll();
		pictureRepository.deleteAll();
		artGalleryRepository.deleteAll();
		userRepository.deleteAll();
	}

	// Save User Test
	@Test
	public void testPersistenceAndLoadUser() {
		User user = new User();
		user.setUsername("John");
		user.setPassword("password");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setDescription("Hi, my name is John Doe and I'm under the water");
		user.setEmail("John@email.com");
		user.setPhoneNumber("1111111");
		user.setId(102);
		userRepository.save(user);
		user = null;
		user = userRepository.findUserByUsername("John");
		assertNotNull(user);
		assertEquals("John", user.getUsername());
		assertEquals("password", user.getPassword());
		assertEquals("John", user.getFirstName());
		assertEquals("Doe", user.getLastName());
		assertEquals("John@email.com", user.getEmail());
		assertEquals("1111111", user.getPhoneNumber());
		userRepository.deleteAll();
	}

	// null username test
	@Test
	public void testpersistenceAndLoadUserWithNullUsername() {
		User user = new User();
		user.setPassword("password");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setDescription("Hi, my name is John Doe and I'm under the water");
		user.setEmail("John@email.com");
		user.setPhoneNumber("1111111");
		user.setId(102);
		try {
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println(e);
		}
		user = null;
		user = userRepository.findUserByUsername(null);
		assertNull(user);
	}

	// null password test
	@Test
	public void testPersistenceAndLoadUserWithNullPassword() {
		User user = new User();
		user.setUsername("John");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setDescription("Hi, my name is John Doe and I'm under the water");
		user.setEmail("John@email.com");
		user.setPhoneNumber("1111111");
		user.setId(102);
		try {
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println(e);
		}
		user = null;
		user = userRepository.findUserByUsername("John");
		assertNull(user);
	}

	// Delete User Test
	@Test
	public void testPersistenceAndDeleteUser() {
		User user = new User();
		user.setUsername("John");
		user.setPassword("password");
		userRepository.save(user);
		int userId = user.getId();
		user = null;
		userRepository.deleteById(userId);
		user = userRepository.findUserByUsername("John");
		assertNull(user);
	}

	// Save Artist Test
	@Test
	public void testPersistenceAndLoadArtist() {
		Artist artist = new Artist();
		String firstName = "John";
		String lastName = "Smith";
		String username = "jsmith";
		String email = "john.smith@mail.ca";
		String description = "Hi I am a fancy artist";
		String password = "12345";
		String phoneNumber = "5141234567";
		String bankAccountNumber = "9999999";

		artist.setFirstName(firstName);
		artist.setLastName(lastName);
		artist.setUsername(username);
		artist.setEmail(email);
		artist.setDescription(description);
		artist.setPassword(password);
		artist.setPhoneNumber(phoneNumber);
		artist.setBankAccountNumber(bankAccountNumber);

		artistRepository.save(artist);

		int artistId = artist.getId();

		artist = null;

		artist = artistRepository.findArtistById(artistId);
		assertEquals(artistId, artist.getId());
		assertEquals(firstName, artist.getFirstName());
		assertEquals(lastName, artist.getLastName());
		assertEquals(username, artist.getUsername());
		assertEquals(email, artist.getEmail());
		assertEquals(description, artist.getDescription());
		assertEquals(password, artist.getPassword());
		assertEquals(phoneNumber, artist.getPhoneNumber());
		assertEquals(bankAccountNumber, artist.getBankAccountNumber());
	}

	// Delete Artist Test
	@Test
	public void testPersistenceAndDeleteArtist() {
		Artist artist = new Artist();
		String firstName = "John";
		String lastName = "Smith";
		String username = "jsmith";
		String email = "john.smith@mail.ca";
		String description = "Hi I am a fancy artist";
		String password = "12345";
		String phoneNumber = "5141234567";
		String bankAccountNumber = "9999999";

		artist.setFirstName(firstName);
		artist.setLastName(lastName);
		artist.setUsername(username);
		artist.setEmail(email);
		artist.setDescription(description);
		artist.setPassword(password);
		artist.setPhoneNumber(phoneNumber);
		artist.setBankAccountNumber(bankAccountNumber);

		artistRepository.save(artist);
		int id = artist.getId();
		artist = null;
		artistRepository.deleteById(id);

		artist = artistRepository.findArtistById(id);
		assertNull(artist);
	}

	// Save artgallery test
	@Test
	public void testPersistenceAndLoadArtGallery() {
		ArtGallery artGallery = new ArtGallery();
		artGallery.setName("VanGoghEstNous");
		artGallery.setAdress("1000 Rue des Arts");

		artGalleryRepository.save(artGallery);
		int artGalleryId = artGallery.getId();
		ArtGallery oldGallery = artGalleryRepository.findArtGalleryById(artGalleryId);
		assertNotNull(oldGallery);
		assertEquals(artGalleryId, oldGallery.getId());
		assertEquals(artGallery.getAdress(), oldGallery.getAdress());
		assertEquals(artGallery.getName(), oldGallery.getName());
	}

	// delete artgallery test
	@Test
	public void testPersistenceAndDeleteArtGallery() {
		ArtGallery artGallery = new ArtGallery();
		artGallery.setName("VanGoghEstNous");
		artGallery.setAdress("1000 Rue des Arts");
		artGalleryRepository.save(artGallery);
		int id = artGallery.getId();
		artGallery = null;
		artGallery = artGalleryRepository.findArtGalleryById(id);
		assertNotNull(artGallery);
		assertEquals("1000 Rue des Arts", artGallery.getAdress());
		assertEquals("VanGoghEstNous", artGallery.getName());
		artGalleryRepository.deleteById(id);
		artGallery = null;
		artGallery = artGalleryRepository.findArtGalleryById(0);
		assertNull(artGallery);
	}

	// save artwork test
	@Test
	public void testPersistenceAndLoadArtwork() {
		Artwork artwork = new Artwork();
		artwork.setName("Starry Nights");

		ArtGallery artGallery = new ArtGallery();
		artGalleryRepository.save(artGallery);
		int artGalleryId = artGallery.getId();
		artwork.setArtGallery(artGallery);

		User artist = new Artist();
		artist.setUsername("user");
		artist.setPassword("password");
		userRepository.save(artist);
		int artistId = artist.getId();
		artistRepository.save((Artist) artist);
		artwork.setArtist((Artist) artist);

		artwork.setDescription("An absolute masterpiece");
		artwork.setForSale(true);
		artwork.setPrice(40);

		artwork.setTypeOfArtwork(TypeOfArtwork.Painting);

		artworkRepository.save(artwork);
		int artworkId = artwork.getId();
		Artwork oldArtwork = artworkRepository.findArtworkById(artworkId);
		assertNotNull(oldArtwork);
		assertEquals(artworkId, oldArtwork.getId());
		assertEquals(artGalleryId, oldArtwork.getArtGallery().getId());
		assertEquals(artwork.getName(), oldArtwork.getName());
		assertEquals(artwork.getArtist().getId(), oldArtwork.getArtist().getId());
		assertEquals(artwork.getDescription(), oldArtwork.getDescription());
		assertEquals(artwork.getPrice(), oldArtwork.getPrice());
		assertEquals(artwork.getTypeOfArtwork().name(), oldArtwork.getTypeOfArtwork().name());
	}

	// delete artwork test
	@Test
	public void testPersistenceAndDeleteArtwork() {
		Artwork artwork = new Artwork();
		artwork.setName("David and Goliath");
		// artwork.setId(10);
		Artist artist = new Artist();
		artist.setUsername("artist");
		artist.setPassword("password");
		artistRepository.save(artist);
		ArtGallery artGallery = new ArtGallery();
		artGalleryRepository.save(artGallery);
		artwork.setArtist(artist);
		artwork.setArtGallery(artGallery);
		artworkRepository.save(artwork);
		int artworkId = artwork.getId();

		artworkRepository.deleteById(artworkId);
		artwork = null;
		artwork = artworkRepository.findArtworkById(artworkId);
		assertNull(artwork);
	}

	// save customer test
	@Test
	public void testPersistenceAndLoadCustomer() {
		Customer customer = new Customer();
		customer.setUsername("username");
		customer.setPassword("password");
		long creditCardNumber = 1111222233334444L;
		String description = "My Description";
		String email = "my.email@gmail.com";
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "5147777777";
		String username = "myUsername";
		customer.setCreditCardNumber(creditCardNumber);
		customer.setDescription(description);
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPhoneNumber(phoneNumber);
		customer.setUsername(username);
		customerRepository.save(customer);
		int customerId = customer.getId();
		customer = null;
		customer = customerRepository.findCustomerById(customerId);
		assertNotNull(customer);
		assertEquals(creditCardNumber, customer.getCreditCardNumber());
		assertEquals(description, customer.getDescription());
		assertEquals(email, customer.getEmail());
		assertEquals(firstName, customer.getFirstName());
		assertEquals(customerId, customer.getId());
		assertEquals(lastName, customer.getLastName());
		assertEquals(phoneNumber, customer.getPhoneNumber());
		assertEquals(username, customer.getUsername());
	}

	// delete customer test
	@Test
	public void testPersistenceAndDeleteCustomer() {
		Customer customer = new Customer();
		customer.setUsername("customer");
		customer.setPassword("password");
		long creditCardNumber = 1111222233334444L;
		customer.setCreditCardNumber(creditCardNumber);
		customerRepository.save(customer);
		int customerId = customer.getId();
		customer = null;
		customer = customerRepository.findCustomerById(customerId);
		assertNotNull(customer);
		assertEquals(creditCardNumber, customer.getCreditCardNumber());
		customerRepository.delete(customer);
		customer = null;
		customer = customerRepository.findCustomerById(customerId);
		assertNull(customer);
	}

	// save picture test
	@Test
	public void testPersistenceAndLoadPicture() {
		Picture picture = new Picture();
		pictureRepository.save(picture);
		int pictureId = picture.getId();
		picture = null;
		picture = pictureRepository.findPictureById(pictureId);
		assertNotNull(picture);
		assertEquals(pictureId, picture.getId());
	}

	// delete picture test
	@Test
	public void testPersistenceAndDeletePicture() {
		Picture picture = new Picture();
		pictureRepository.save(picture);
		int pictureId = picture.getId();
		picture = null;
		picture = pictureRepository.findPictureById(pictureId);
		assertNotNull(picture);
		assertEquals(pictureId, picture.getId());
		pictureRepository.delete(picture);
		picture = null;
		picture = pictureRepository.findPictureById(pictureId);
		assertNull(picture);
	}

	// save transaction test
	@Test
	public void testPersistenceAndLoadSaveTransaction() {
		Transaction transaction = new Transaction();

		ArtGallery artGallery = new ArtGallery();
		artGalleryRepository.save(artGallery);
		int artGalleryId = artGallery.getId();
		transaction.setArtGallery(artGallery);

		Artist artist = new Artist();
		artist.setUsername("artist");
		artist.setPassword("password");
		artistRepository.save(artist);
		int artistId = artist.getId();
		transaction.setArtist(artist);

		Artwork artwork = new Artwork();
		artwork.setArtist(artist);
		artwork.setArtGallery(artGallery);
		artworkRepository.save(artwork);
		int artworkId = artwork.getId();
		transaction.setArtwork(artwork);

		Customer customer = new Customer();
		customer.setUsername("username");
		customer.setPassword("password");
		customerRepository.save(customer);
		int customerId = customer.getId();
		transaction.setCustomer(customer);

		transactionRepository.save(transaction);
		int transactionId = transaction.getId();
		transaction = null;
		transaction = transactionRepository.findTransactionById(transactionId);

		assertNotNull(transaction);
		assertEquals(artGalleryId, transaction.getArtGallery().getId());
		assertEquals(artistId, transaction.getArtist().getId());
		assertEquals(artworkId, transaction.getArtwork().getId());
		assertEquals(customerId, transaction.getCustomer().getId());
	}

	// delete transaction test
	@Test
	public void testPersistenceAndDeleteTransaction() {
		Transaction transaction = new Transaction();
		ArtGallery artGallery = new ArtGallery();
		artGalleryRepository.save(artGallery);
		int artGalleryId = artGallery.getId();
		transaction.setArtGallery(artGallery);
		Artist artist = new Artist();
		artist.setUsername("artist");
		artist.setPassword("password");
		artistRepository.save(artist);
		int artistId = artist.getId();
		transaction.setArtist(artist);
		Artwork artwork = new Artwork();
		artwork.setArtist(artist);
		artwork.setArtGallery(artGallery);
		artworkRepository.save(artwork);
		int artworkId = artwork.getId();
		transaction.setArtwork(artwork);
		Customer customer = new Customer();
		customer.setUsername("customer");
		customer.setPassword("password");
		customerRepository.save(customer);
		int customerId = customer.getId();
		transaction.setCustomer(customer);
		transactionRepository.save(transaction);
		int transactionId = transaction.getId();
		transaction = null;
		transaction = transactionRepository.findTransactionById(transactionId);
		assertNotNull(transaction);
		assertEquals(artGalleryId, transaction.getArtGallery().getId());
		assertEquals(artistId, transaction.getArtist().getId());
		assertEquals(artworkId, transaction.getArtwork().getId());
		assertEquals(customerId, transaction.getCustomer().getId());
		transactionRepository.delete(transaction);
		transaction = null;
		transaction = transactionRepository.findTransactionById(transactionId);
		assertNull(transaction);
	}

}
