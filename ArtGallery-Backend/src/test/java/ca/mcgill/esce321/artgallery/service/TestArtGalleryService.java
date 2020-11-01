package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.services.ArtGalleryService;

@ExtendWith(MockitoExtension.class)
public class TestArtGalleryService {

	@Mock
	private ArtGalleryRepository artGalleryRepository;

	@Mock
	private ArtworkRepository artworkRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private ArtGalleryService artGalleryService;

	private static final int ART_GALLERY_KEY = 69696969;

	private static final String testArtGalleryName = "art gallery";

	private static final String testNonExistingArtGalleryName = "Not a gallery";

	private static final int testArtworkId = 1;

	private static final int testNonExistantArtworkId = 3;

	private static final int testTransactionId = 2;

	private static final int testNonExistantTransactionId = 4;

	@BeforeEach
	public void setMockOutput() {

		// art gallery find by id invocation mock
		lenient().when(artGalleryRepository.findArtGalleryById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ART_GALLERY_KEY)) {
				ArtGallery artGallery = new ArtGallery();
				artGallery.setId(ART_GALLERY_KEY);
				return artGallery;
			} else {
				return null;
			}
		});

		// art gallery find by name invocation mock
		lenient().when(artGalleryRepository.findArtGalleryByName(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(testArtGalleryName)) {
						ArtGallery artGallery = new ArtGallery();
						artGallery.setId(ART_GALLERY_KEY);
						artGallery.setName(testArtGalleryName);
						return artGallery;
					} else {
						return null;
					}
				});

		// art gallery find all invocation mock
		lenient().when(artworkRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			ArrayList<Artwork> artworkList = new ArrayList<Artwork>();
			Artwork artwork = new Artwork();
			artwork.setId(testArtworkId);
			artworkList.add(artwork);
			return artworkList;
		});

		// transaction find by id invocation mock
		lenient().when(transactionRepository.findTransactionById(anyInt()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(testTransactionId)) {
						Transaction transaction = new Transaction();
						transaction.setId(testTransactionId);
						transaction.setCommisionCut(0.15);
						Artwork artwork = new Artwork();
						artwork.setId(testArtworkId);
						artwork.setPrice(500);
						transaction.setArtwork(artwork);
						return transaction;
					} else {
						return null;
					}
				});

		// transaction find all invocation mock
		lenient().when(transactionRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
			Transaction transaction = new Transaction();
			transaction.setId(testTransactionId);
			transactionList.add(transaction);
			return transactionList;
		});

		// artwork find by id invocation mock
		lenient().when(artworkRepository.findArtworkById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(testArtworkId)) {
				Artwork artwork = new Artwork();
				artwork.setId(testArtworkId);
				return artwork;
			} else {
				return null;
			}
		});
	}

	// test for public ArrayList<Artwork> getAllArtworks().
	@Test
	public void testGetAllArtworks() {
		ArrayList<Artwork> allArtworks = new ArrayList<Artwork>();
		try {
			allArtworks = artGalleryService.getAllArtworks();
		} catch (Exception e) {
			fail();
		}
		assertEquals(1, allArtworks.size());
	}

	// test for public boolean removeArtworkById(int artworkID) : existing artwork
	@Test
	public void testRemoveExistingArtworkById() {
		Boolean removedArtwork = false;
		try {
			removedArtwork = artGalleryService.removeArtworkById(testArtworkId);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, removedArtwork);
	}

	// test for public boolean removeArtworkById(int artworkID) : non existing
	// artwork
	@Test
	public void testRemoveNonExistingArtworkById() {
		Boolean removedArtwork = false;
		try {
			removedArtwork = artGalleryService.removeArtworkById(testNonExistantArtworkId);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, removedArtwork);
	}

	// test for public double takeCommission(int transactionID) : existing
	// transaction
	@Test
	public void testTakeCommissionOnExistingTransaction() {
		double commission = 0;
		try {
			commission = artGalleryService.takeCommission(testTransactionId);
		} catch (Exception e) {
			fail();
		}

		assertEquals(75, commission);
	}

	// test for public double takeCommission(int transactionID) : non existing
	// transaction
	@Test
	public void testTakeCommissionOnNonExistingTransaction() {
		double commission = 0;
		try {
			commission = artGalleryService.takeCommission(testNonExistantTransactionId);
		} catch (Exception e) {
			fail();
		}

		assertEquals(0, commission);
	}

	// test public ArtGallery saveArtGallery(ArtGallery artGallery) : existing
	// artgallery
	@Test
	public void testSaveExistingArtGallery() {
		Boolean artGallerySaved = false;
		ArtGallery artGallery = new ArtGallery();
		artGallery.setId(ART_GALLERY_KEY);
		artGallery.setName(testArtGalleryName);
		try {
			artGallerySaved = artGalleryService.saveArtGallery(artGallery);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, artGallerySaved);
	}

	// test public ArtGallery saveArtGallery(ArtGallery artGallery) : non existing
	// artgallery
	@Test
	public void testSaveNonExistingArtGallery() {
		Boolean artGallerySaved = false;
		ArtGallery artGallery = new ArtGallery();
		artGallery.setId(1);
		artGallery.setName(testNonExistingArtGalleryName);
		try {
			artGallerySaved = artGalleryService.saveArtGallery(artGallery);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, artGallerySaved);
	}

	// public boolean updateArtGallery(ArtGallery artGallery) : existing gallery
	@Test
	public void testUpdateArtGallery() {
		Boolean artGalleryUpdated = false;
		ArtGallery updatedArtGallery = new ArtGallery();
		updatedArtGallery.setId(ART_GALLERY_KEY);
		updatedArtGallery.setName(testArtGalleryName);

		try {
			artGalleryUpdated = artGalleryService.updateArtGallery(updatedArtGallery);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, artGalleryUpdated);
	}

	// public boolean updateArtGallery(ArtGallery artGallery) : non existing gallery
	@Test
	public void testUpdateNonExistingArtGallery() {
		Boolean artGalleryUpdated = false;
		ArtGallery updatedArtGallery = new ArtGallery();
		updatedArtGallery.setId(1);
		updatedArtGallery.setName(testNonExistingArtGalleryName);

		try {
			artGalleryUpdated = artGalleryService.updateArtGallery(updatedArtGallery);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, artGalleryUpdated);
	}

	// public ArtGallery getArtGalleryByName(String name) : existing art gallery
	@Test
	public void testGetArtGalleryByName() {
		ArtGallery existingArtGallery = new ArtGallery();
		try {
			existingArtGallery = artGalleryService.getArtGalleryByName(testArtGalleryName);
		} catch (Exception e) {
			fail();
		}

		assertNotNull(existingArtGallery);
		assertEquals(testArtGalleryName, existingArtGallery.getName());
		assertEquals(ART_GALLERY_KEY, existingArtGallery.getId());
	}

	// public ArtGallery getArtGalleryByName(String name) : non existing art gallery
	@Test
	public void testGetNonExistingArtGalleryByName() {
		ArtGallery nonExistingArtGallery = new ArtGallery();
		try {
			nonExistingArtGallery = artGalleryService.getArtGalleryByName(testNonExistingArtGalleryName);
		} catch (Exception e) {
			fail();
		}
		assertNull(nonExistingArtGallery);
	}

	// test public ArrayList<Transaction> getAllTransactions()
	@Test
	public void testGetAllTransactions() {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

		try {
			transactionList = artGalleryService.getAllTransactions();
		} catch (Exception e) {
			fail();
		}

		assertEquals(1, transactionList.size());
	}

	// test public Boolean deleteArtGalleyById(int artGalleryId) : existing art
	// gallery
	@Test
	public void testDeleteArtGalleryById() {
		Boolean deletedArtGallery = false;
		try {
			deletedArtGallery = artGalleryService.deleteArtGalleyById(ART_GALLERY_KEY);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, deletedArtGallery);
	}

	// test public Boolean deleteArtGalleyById(int artGalleryId) : non existing art
	// gallery
	@Test
	public void testDeleteNonExistingArtGalleryById() {
		Boolean deletedArtGallery = false;
		try {
			deletedArtGallery = artGalleryService.deleteArtGalleyById(1);
		} catch (Exception e) {
			fail();
		}

		assertEquals(false, deletedArtGallery);
	}
}
