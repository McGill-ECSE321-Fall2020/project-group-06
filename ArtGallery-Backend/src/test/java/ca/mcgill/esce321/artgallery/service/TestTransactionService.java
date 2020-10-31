package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;

import javax.validation.constraints.AssertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;
import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.dto.TransactionDto;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;
import ca.mcgill.ecse321.artgallery.services.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TestTransactionService {

	@Mock
	private TransactionRepository transactionRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ArtworkRepository artworkRepository;
	@Mock
	private ArtGalleryRepository artGalleryRepository;
	@Mock
	private ArtistRepository artistRepository;

	@InjectMocks
	private TransactionService transactionService;

	private static final int TRANSACTION_KEY = 9999;
	private static final int testArtGalleryId = 1;
	private static final int testArtistId = 2;
	private static final int testArtworkId = 3;
	private static final int testCustomerId = 4;
	private static final String NONEXISTING_KEY = "NotATransaction";

	@BeforeEach
	public void setMockOutput() {
		// transaction invocation on mock
		lenient().when(transactionRepository.findTransactionById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TRANSACTION_KEY)) {
				Transaction transaction = new Transaction();
				transaction.setId(TRANSACTION_KEY);
				return transaction;
			} else {
				return null;
			}
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(transactionRepository.save(any(Transaction.class))).thenAnswer(returnParameterAsAnswer);

		// art gallery invocation on mock
		lenient().when(artGalleryRepository.findArtGalleryById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(testArtGalleryId)) {
				ArtGallery artGallery = new ArtGallery();
				artGallery.setId(testArtGalleryId);
				return artGallery;
			} else {
				return null;
			}
		});

		// artist invocation on mock
		lenient().when(artistRepository.findArtistById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(testArtistId)) {
				Artist artist = new Artist();
				artist.setId(testArtistId);
				return artist;
			} else {
				return null;
			}
		});

		// artwork invocation on mock
		lenient().when(artworkRepository.findArtworkById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(testArtworkId)) {
				Artwork artwork = new Artwork();
				artwork.setId(testArtworkId);
				return artwork;
			} else {
				return null;
			}
		});

		// customer invocation on mock
		lenient().when(customerRepository.findCustomerById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(testCustomerId)) {
				Customer customer = new Customer();
				customer.setId(testCustomerId);
				return customer;
			} else {
				return null;
			}
		});
	}

	// test public boolean saveTransaction(int customerId, int artistId, int
	// artworkId, int artGalleryId)
	@Test
	public void testSaveTransaction() {
		// We dont need to create objects here because we only pass ids as parameter
		// Customer customer = new Customer();
		// customerRepository.save(customer);

		// Artist artist = new Artist();
		// artistRepository.save(artist);

		// Artwork artwork = new Artwork();
		// artworkRepository.save(artwork);

		// ArtGallery artGallery = new ArtGallery();
		// artGalleryRepository.save(artGallery);
		Boolean transactionSaved = false;
		// Don't need this because when u save its already bidirectional;
		// Artist newArtist = artistRepository.findArtistById(artist.getId());
		// Transaction transaction = (Transaction)
		// newArtist.getTransaction().toArray()[0];
		try {
			transactionSaved = transactionService.saveTransaction(testCustomerId, testArtistId, testArtworkId,
					testArtGalleryId);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, transactionSaved);
		// assertEquals(newArtist.getTransaction().isEmpty(), false);
		// assertEquals(transaction.getCustomer().getId(), customer.getId());
		// assertEquals(transaction.getArtist().getId(), artist.getId());
		// assertEquals(transaction.getArtwork().getId(), artwork.getId());
		// assertEquals(transaction.getArtGallery().getId(), artGallery.getId());
	}

	// test public boolean updateTransaction(Transaction transaction)
	@Test
	public void testUpdateTransaction() {
		// Customer customer = new Customer();
		// customerRepository.save(customer);

		// Artist artist = new Artist();
		// artistRepository.save(artist);

		// Artwork artwork = new Artwork();
		// artworkRepository.save(artwork);

		// ArtGallery artGallery = new ArtGallery();
		// artGalleryRepository.save(artGallery);

		// transactionService.saveTransaction(customer.getId(), artist.getId(),
		// artwork.getId(), artGallery.getId());

		// artist = artistRepository.findArtistById(artist.getId());
		// Transaction transaction = (Transaction) artist.getTransaction().toArray()[0];
		// TransactionDto transactionDTO = TransactionDto.convertToDTO(transaction);

		// Customer newCustomer = new Customer();
		// customerRepository.save(newCustomer);

		// Artist newArtist = new Artist();
		// artistRepository.save(newArtist);

		// Artwork newArtwork = new Artwork();
		// artworkRepository.save(newArtwork);

		// ArtGallery newArtGallery = new ArtGallery();
		// artGalleryRepository.save(newArtGallery);

		// Date dateOfTransaction = new Date(100000);
		// transactionDTO.setCommisionCut(0.30);
		// transactionDTO.setArtGalleryId(newArtGallery.getId());
		// transactionDTO.setArtistId(newArtist.getId());
		// transactionDTO.setArtworkId(newArtwork.getId());
		// transactionDTO.setCustomerId(newCustomer.getId());
		// transactionDTO.setDateOfTransaction(dateOfTransaction);
		// transactionDTO.setDeliveryType(DeliveryType.Delivered);
		// transactionService.updateTransaction(transactionDTO);

		// artist = artistRepository.findArtistById(artist.getId());
		// Transaction updatedTransaction = (Transaction)
		// artist.getTransaction().toArray()[0];
		// assertEquals(transaction.getId(), updatedTransaction.getId());
		// assertEquals(newArtGallery, updatedTransaction.getArtGallery().getId());

		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setId(TRANSACTION_KEY);
		transactionDto.setArtGalleryId(testArtGalleryId);
		transactionDto.setArtistId(testArtistId);
		transactionDto.setArtworkId(testArtworkId);
		transactionDto.setCustomerId(testCustomerId);

		Boolean transactionUpdated = false;
		try {
			transactionUpdated = transactionService.updateTransaction(transactionDto);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, transactionUpdated);
	}

	// test public boolean removeTransaction(int transactionId)
	@Test
	public void testRemoveTransaction() {
		Boolean transactionDeleted = false;
		try {
			transactionDeleted = transactionService.removeTransaction(TRANSACTION_KEY);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, transactionDeleted);
	}

	// test public Transaction getTransactionById(int id)

	@Test
	public void testgetTransaction() {
		Transaction existingTransaction = new Transaction();
		try {
			existingTransaction = transactionService.getTransactionById(TRANSACTION_KEY);
		} catch (Exception e) {
			fail();
		}

		assertNotNull(existingTransaction);
		assertEquals(TRANSACTION_KEY, existingTransaction.getId());
	}

	// TODO ADD MORE TESTS FOR TRANSACTION IF NEEDED

}
