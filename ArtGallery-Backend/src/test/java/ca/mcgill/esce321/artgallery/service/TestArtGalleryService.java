package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	private static final int testArtworkId = 1;

	private static final int testTransactionId = 2;

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

	// test for public ArrayList<Artwork> getAllArtworks()
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

	// test for public boolean removeArtworkById(int artworkID)
	@Test
	public void testRemoveArtworkById() {
		Boolean removedArtwork = false;
		try {
			removedArtwork = artGalleryService.removeArtworkById(testArtworkId);
		} catch (Exception e) {
			fail();
		}

		assertEquals(true, removedArtwork);
	}

	// test for public double takeCommission(int transactionID)
	@Test
	public void testTakeCommission() {
		double commission = 0;
		try {
			commission = artGalleryService.takeCommission(testTransactionId);
		} catch (Exception e) {
			fail();
		}

		assertEquals(75, commission);
	}

	// test public ArtGallery saveArtGallery(ArtGallery artGallery)
	@Test
	public void testSaveArtGallery() {
		Boolean artGallerySaved = true;
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

	// public boolean updateArtGallery(ArtGallery artGallery)
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

	// public ArtGallery getArtGalleryByName(String name)
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

	// test public Boolean deleteArtGalleyById(int artGalleryId)
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

	// ADD MORE TESTS OR METHODS IF NEEDED
}
