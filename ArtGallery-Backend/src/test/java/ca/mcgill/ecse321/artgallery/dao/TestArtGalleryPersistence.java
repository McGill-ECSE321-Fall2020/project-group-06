package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

	@Test
	public void testPersistenceAndLoadArtist() {
		Artist artist = new Artist();
		artist.setId(101);
		artist.setFirstName("John");
		artist.setLastName("Smith");
		artist.setUsername("jsmith");
		artist.setEmail("john.smith@mcgill.ca");
		artist.setDescription("Hi I am a fancy artist");
		artist.setPassword("12345");
		artist.setPhoneNumber("5141234567");
		artist.setBankAccountNumber("99999999");

		Artwork artwork = new Artwork();
		artwork.setId(102);
		artwork.setArtist(artist);
		Set<Artwork> artworkSet = new HashSet<Artwork>();
		artworkSet.add(artwork);
		artist.setArtwork(artworkSet);

		Transaction transaction = new Transaction();
		transaction.setId(103);
		transaction.setArtist(artist);
		transaction.setArtwork(artwork);
		Set<Transaction> transactionSet = new HashSet<Transaction>();
		transactionSet.add(transaction);
		artist.setTransaction(transactionSet);

		artistRepository.save(artist);
		Artist artistQueried = artistRepository.findArtistById(101);

		assertNotNull(artistQueried);
		assertEquals(artist.getId(), artistQueried.getId());
		assertEquals(artist.getFirstName(), artistQueried.getFirstName());
		assertEquals(artist.getLastName(), artistQueried.getLastName());
		assertEquals(artist.getUsername(), artistQueried.getUsername());
		assertEquals(artist.getEmail(), artistQueried.getEmail());
		assertEquals(artist.getDescription(), artistQueried.getDescription());
		assertEquals(artist.getPassword(), artistQueried.getPassword());
		assertEquals(artist.getPhoneNumber(), artistQueried.getPhoneNumber());
		assertEquals(artist.getBankAccountNumber(), artistQueried.getBankAccountNumber());
		assertEquals(artist.getArtwork(), artistQueried.getArtwork());
		assertEquals(artist.getTransaction(), artistQueried.getTransaction());
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
		artGallery.setAdress("1000 Rue des Arts");

		Artwork artwork = new Artwork();
		artwork.setId(11);
		Set<Artwork> artworkSet = new HashSet<Artwork>();
		artworkSet.add(artwork);
		artGallery.setArtwork(artworkSet);

		Transaction transaction = new Transaction();
		transaction.setId(12);
		Set<Transaction> transactionSet = new HashSet<Transaction>();
		transactionSet.add(transaction);
		artGallery.setTransaction(transactionSet);

		artGalleryRepository.save(artGallery);
		ArtGallery oldGallery = artGalleryRepository.findArtGalleryById(2);
		assertNotNull(oldGallery);
		assertEquals(artGallery.getId(), oldGallery.getId());
		assertEquals(artGallery.getAdress(), oldGallery.getAdress());
		assertEquals(artGallery.getArtwork(), oldGallery.getArtwork());
		assertEquals(artGallery.getName(), oldGallery.getName());
		assertEquals(artGallery.getTransaction(), oldGallery.getTransaction());
	}

	@Test
	public void testPersistenceAndLoadArtwork() {
		Artwork artwork = new Artwork();
		artwork.setName("Starry Nights");
		artwork.setId(3);

		ArtGallery artGallery = new ArtGallery();
		artGallery.setId(6);
		artwork.setArtGallery(artGallery);

		Artist artist = new Artist();
		artist.setId(7);
		artwork.setArtist(artist);

		artwork.setDescription("An absolute masterpiece");
		artwork.setForSale(true);

		Picture picture = new Picture();
		picture.setId(8);
		Set<Picture> pictureSet = new HashSet<Picture>();
		pictureSet.add(picture);
		artwork.setPicture(pictureSet);

		artwork.setPrice(40);

		Transaction transaction = new Transaction();
		transaction.setId(9);
		Set<Transaction> transactionSet = new HashSet<Transaction>();
		transactionSet.add(transaction);
		artwork.setTransaction(transactionSet);

		artwork.setTypeOfArtwork(TypeOfArtwork.Painting);

		artworkRepository.save(artwork);
		Artwork oldArtwork = artworkRepository.findArtworkById(3);
		assertNotNull(oldArtwork);
		assertEquals(artwork.getId(), oldArtwork.getId());
		assertEquals(artwork.getArtGallery().getId(), oldArtwork.getArtGallery().getId());
		assertEquals(artwork.getName(), oldArtwork.getName());
		assertEquals(artwork.getArtist().getId(), oldArtwork.getArtist().getId());
		assertEquals(artwork.getDescription(), oldArtwork.getDescription());
		assertEquals(artwork.getPicture(), oldArtwork.getPicture());
		assertEquals(artwork.getPrice(), oldArtwork.getPrice());
		assertEquals(artwork.getTransaction(), oldArtwork.getTransaction());
		assertEquals(artwork.getTypeOfArtwork().name(), oldArtwork.getTypeOfArtwork().name());
	}

	public void testPersistenceAndDeleteArtwork() {
		Artwork artwork = new Artwork();
		artwork.setName("David and Goliath");
		artwork.setId(10);
		artworkRepository.save(artwork);
		artworkRepository.delete(artwork);
		Artwork oldArtwork = artworkRepository.findArtworkById(10);
		assertNull(oldArtwork);
	}

	@Test
	public void testPersistenceAndLoadPicture() {
		Picture picture = new Picture();
		picture.setId(4);

		Artwork artwork = new Artwork();
		artwork.setId(15);
		Set<Artwork> artworkSet = new HashSet<Artwork>();
		artworkSet.add(artwork);
		picture.setFavorites(artworkSet);

		User user = new User();
		user.setId(16);
		picture.setUser(user);

		pictureRepository.save(picture);
		Picture oldPicture = pictureRepository.findPictureById(4);
		assertNotNull(oldPicture);
		assertEquals(picture.getId(), oldPicture.getId());
		assertEquals(picture.getFavorites(), picture.getFavorites());
		assertEquals(picture.getId(), picture.getId());
		assertEquals(picture.getUser().getId(), picture.getUser().getId());
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
