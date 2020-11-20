package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;


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

	private static final int TransactionID = 9999;
	
	private static final int ArtGalleryId = 1;
	
	private static final int ArtistId = 2;
	
	private static final int ArtworkId = 3;
	
	private static final int CustomerId = 4;
	
	private static final int NonExistant_MultiPurpose = 10;
//Hello world
	@BeforeEach
	public void setMockOutput() {
		// transaction invocation on mock
		lenient().when(transactionRepository.findTransactionById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TransactionID)) {
				Transaction transaction = new Transaction();
				transaction.setId(TransactionID);
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
			if (invocation.getArgument(0).equals(ArtGalleryId)) {
				ArtGallery artGallery = new ArtGallery();
				artGallery.setId(ArtGalleryId);
				return artGallery;
			} else {
				return null;
			}
		});

		// artist invocation on mock
		lenient().when(artistRepository.findArtistById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtistId)) {
				Artist artist = new Artist();
				artist.setId(ArtistId);
				return artist;
			} else {
				return null;
			}
		});

		// artwork invocation on mock
		lenient().when(artworkRepository.findArtworkById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtworkId)) {
				Artwork artwork = new Artwork();
				artwork.setId(ArtworkId);
				return artwork;
			} else {
				return null;
			}
		});

		// customer invocation on mock
		lenient().when(customerRepository.findCustomerById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CustomerId)) {
				Customer customer = new Customer();
				customer.setId(CustomerId);
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
			transactionSaved = transactionService.saveTransaction(CustomerId, ArtistId, ArtworkId,
					ArtGalleryId);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, transactionSaved);
	}
	// test public boolean saveTransaction(int customerId, int artistId, int
	// artworkId, int artGalleryId) with a bad artGalleryID
	@Test
	public void testSaveTransactionBadArtGalleryId() {

		Boolean transactionSaved = false;

		try {
			transactionSaved = transactionService.saveTransaction(CustomerId, ArtistId, ArtworkId,
					NonExistant_MultiPurpose);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, transactionSaved);
	}
	// test public boolean saveTransaction(int customerId, int artistId, int
	// artworkId, int artGalleryId) with a bad CustomerID
	@Test
	public void testSaveTransactionBadCustomerId() {
		Boolean transactionSaved = false;

		try {
			transactionSaved = transactionService.saveTransaction(NonExistant_MultiPurpose, ArtistId, ArtworkId,
					ArtGalleryId);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, transactionSaved);
	}
	// test public boolean saveTransaction(int customerId, int artistId, int
	// artworkId, int artGalleryId) with a bad ArtistId
	@Test
	public void testSaveTransactionBadArtistId() {

		Boolean transactionSaved = false;

		try {
			transactionSaved = transactionService.saveTransaction(CustomerId, NonExistant_MultiPurpose, ArtworkId,
					ArtGalleryId);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, transactionSaved);
	}
	// test public boolean saveTransaction(int customerId, int artistId, int
	// artworkId, int artGalleryId) with a bad ArtworkID
	@Test
	public void testSaveTransactionBadArtworkId() {

		Boolean transactionSaved = false;

		try {
			transactionSaved = transactionService.saveTransaction(CustomerId, ArtistId, NonExistant_MultiPurpose,
					ArtGalleryId);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, transactionSaved);
	}

	// test public boolean updateTransaction(Transaction transaction)
	@Test
	public void testUpdateTransaction() {
		
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setId(TransactionID);
		transactionDto.setArtGalleryId(ArtGalleryId);
		transactionDto.setArtistId(ArtistId);
		transactionDto.setArtworkId(ArtworkId);
		transactionDto.setCustomerId(CustomerId);

		Boolean transactionUpdated = false;
		try {
			transactionUpdated = transactionService.updateTransaction(transactionDto);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, transactionUpdated);
	}
	// test public boolean updateTransaction(Transaction transaction) with a bad customer Id
	@Test
	public void testUpdateTransactionBadCustomerId() {
		
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setId(TransactionID);
		transactionDto.setArtGalleryId(ArtGalleryId);
		transactionDto.setArtistId(ArtistId);
		transactionDto.setArtworkId(ArtworkId);
		transactionDto.setCustomerId(NonExistant_MultiPurpose);

		Boolean transactionUpdated = false;
		try {
			transactionUpdated = transactionService.updateTransaction(transactionDto);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, transactionUpdated);
	}
	// test public boolean updateTransaction(Transaction transaction) with a bad Artwork Id
	@Test
	public void testUpdateTransactionBadArtworkId() {
		
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setId(TransactionID);
		transactionDto.setArtGalleryId(ArtGalleryId);
		transactionDto.setArtistId(ArtistId);
		transactionDto.setArtworkId(NonExistant_MultiPurpose);
		transactionDto.setCustomerId(CustomerId);

		Boolean transactionUpdated = false;
		try {
			transactionUpdated = transactionService.updateTransaction(transactionDto);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, transactionUpdated);
	}
	// test public boolean updateTransaction(Transaction transaction) with a bad Artist Id
	@Test
	public void testUpdateTransactionBadArtistId() {
		
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setId(TransactionID);
		transactionDto.setArtGalleryId(ArtGalleryId);
		transactionDto.setArtistId(NonExistant_MultiPurpose);
		transactionDto.setArtworkId(ArtworkId);
		transactionDto.setCustomerId(CustomerId);

		Boolean transactionUpdated = false;
		try {
			transactionUpdated = transactionService.updateTransaction(transactionDto);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, transactionUpdated);
	}
	// test public boolean updateTransaction(Transaction transaction) with a bad ArtGallery Id
	@Test
	public void testUpdateTransactionBadArtGalleryId() {
		
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setId(TransactionID);
		transactionDto.setArtGalleryId(NonExistant_MultiPurpose);
		transactionDto.setArtistId(ArtistId);
		transactionDto.setArtworkId(ArtworkId);
		transactionDto.setCustomerId(CustomerId);

		Boolean transactionUpdated = false;
		try {
			transactionUpdated = transactionService.updateTransaction(transactionDto);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, transactionUpdated);
	}
	// test public boolean updateTransaction(Transaction transaction) with a bad Transaction Id
	@Test
	public void testUpdateTransactionBadTransactionId() {
		
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setId(NonExistant_MultiPurpose);
		transactionDto.setArtGalleryId(ArtGalleryId);
		transactionDto.setArtistId(ArtistId);
		transactionDto.setArtworkId(ArtworkId);
		transactionDto.setCustomerId(CustomerId);

		Boolean transactionUpdated = false;
		try {
			transactionUpdated = transactionService.updateTransaction(transactionDto);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, transactionUpdated);
	}

	// test public boolean removeTransaction(int transactionId)
	@Test
	public void testRemoveTransaction() {
		Boolean transactionDeleted = false;
		try {
			transactionDeleted = transactionService.removeTransaction(TransactionID);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, transactionDeleted);
	}
	// test public boolean removeTransaction(int transactionId) with a bad transaction key
	@Test
	public void testRemoveTransactionBadKey() {
		Boolean transactionDeleted = false;
		try {
			transactionDeleted = transactionService.removeTransaction(NonExistant_MultiPurpose);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, transactionDeleted);
	}

	// test public Transaction getTransactionById(int id)

	@Test
	public void testgetTransaction() {
		Transaction existingTransaction = new Transaction();
		try {
			existingTransaction = transactionService.getTransactionById(TransactionID);
		} catch (Exception e) {
			fail();
		}

		assertNotNull(existingTransaction);
		assertEquals(TransactionID, existingTransaction.getId());
	}
	// test public Transaction getTransactionById(int id) with a bad transaction key
	@Test
	public void testgetTransactionBadKey() {
		Transaction existingTransaction = new Transaction();
		try {
			existingTransaction = transactionService.getTransactionById(NonExistant_MultiPurpose);
		} catch (Exception e) {
			fail();
		}

		assertNull(existingTransaction);
	}

}
