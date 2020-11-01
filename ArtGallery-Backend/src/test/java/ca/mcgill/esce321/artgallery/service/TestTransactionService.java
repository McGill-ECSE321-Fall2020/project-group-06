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

		Boolean transactionSaved = false;

		try {
			transactionSaved = transactionService.saveTransaction(testCustomerId, testArtistId, testArtworkId,
					testArtGalleryId);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, transactionSaved);
	}

	// test public boolean updateTransaction(Transaction transaction)
	@Test
	public void testUpdateTransaction() {
		
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
