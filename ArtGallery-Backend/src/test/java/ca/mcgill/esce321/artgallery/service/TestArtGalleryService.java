package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.services.ArtGalleryService;

public class TestArtGalleryService {

	// Write this idk how@BeforeEach
	@Mock
	private ArtGalleryRepository artGalleryRepository;

	@InjectMocks
	private ArtGalleryService artGalleryService;

	private static final int ART_GALLERY_KEY = 69696969;

	@BeforeEach
	// I think this needs to be changed????
	public void setMockOutput() {
		lenient().when(artGalleryRepository.findArtGalleryById(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ART_GALLERY_KEY)) {
				ArtGallery artGallery = new ArtGallery();
				artGallery.setId(ART_GALLERY_KEY);
				return artGallery;
			} else {
				return null;
			}
		});
	}

	// this method might need rework
	public void testRemoveArtwork() {
		Artwork artwork = new Artwork();
		artwork.setForSale(true);

		try {
			artGalleryService.removeArtwork(artwork.getId());
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(artwork);
		assertEquals(artwork.isForSale(), false);
	}

	// test for public ArrayList<Artwork> getAllArtworks()

	// test for public double takeCommission(int transactionID)

	// test public ArtGallery saveArtGallery(ArtGallery artGallery)

	// public boolean updateArtGallery(ArtGallery artGallery)

	// public ArtGallery getArtGalleryByName(String name)

	// ADD MORE TESTS OR METHODS IF NEEDED
}
