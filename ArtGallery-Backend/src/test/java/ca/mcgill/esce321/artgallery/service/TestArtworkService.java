package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.HashSet;

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
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.services.ArtworkService;

@ExtendWith(MockitoExtension.class)
public class TestArtworkService {

    @Mock
    private ArtworkRepository artworkRepository;
    @Mock
    private ArtGalleryRepository artGalleryRepository;
    @Mock
    private ArtistRepository artistRepository;
    @InjectMocks
    private ArtworkService artworkService;
    private static final String ARTWORK_KEY = "TestArtwork";
    private static final String NONEXISTING_KEY = "NotAArtwork";
    private static final int ARTWORK_ID=100;
    private static final int Non_Existant_Artwork_ID = 50;
	private static final int ART_GALLERY_KEY = 15;
	private static final int Non_Existing_ART_GALLERY_KEY = 30;
    private static final String testArtGalleryName="testArtGalleryName";
    private static final String NonExistingArtGalleryName="NotArtGalleryName";
    private static final String ARTIST_USERNAME="artistName";
    private static final String Non_Existant_ARTIST_USERNAME="NotArtistName";
    private static final int ARTIST_ID=20;
    private static final int Non_Existant_ARTIST_ID=10;
    private Artist artist=new Artist();
    private ArtGallery artGallery= new ArtGallery();



    @BeforeEach
    public void setMockOutput() {
        lenient().when(artworkRepository.findArtworkByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ARTWORK_KEY)) {
                Artwork artwork = new Artwork();
                artwork.setId(ARTWORK_ID);
                artwork.setName(ARTWORK_KEY);
                return artwork;
            } else {
                return null;
            }
        });
        //Art Gallery by name
		lenient().when(artGalleryRepository.findArtGalleryByName(anyString()))
		.thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(testArtGalleryName)) {
				ArtGallery artGallery = new ArtGallery();
				artGallery.setName(testArtGalleryName);
				artGallery.setId(ART_GALLERY_KEY);
				return artGallery;
			} else {
				return null;
			}
		});
        // artist invocation on mock
		lenient().when(artistRepository.findArtistByUsername(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ARTIST_USERNAME)) {
				Artist artist = new Artist();
                artist.setId(ARTIST_ID);
                artist.setArtwork(new HashSet<Artwork>());
                artist.setUsername(ARTIST_USERNAME);
				return artist;
			} else {
				return null;
			}
		});
		//artGallery by ID
		lenient().when(artGalleryRepository.findArtGalleryById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ART_GALLERY_KEY)) {
				ArtGallery artGallery = new ArtGallery();
				artGallery.setId(ART_GALLERY_KEY);
				return artGallery;
			} else {
				return null;
			}
		});
		//artist by ID
		lenient().when(artistRepository.findArtistById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ARTIST_ID)) {
				Artist artist = new Artist();
				artist.setId(ARTIST_ID);
				artist.setArtwork(new HashSet<Artwork>());
                artist.setUsername(ARTIST_USERNAME);
				return artist;
			} else {
				return null;
			}
		});
		//find Artwork By ID
		lenient().when(artworkRepository.findArtworkById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ARTWORK_ID)) {
				Artwork artwork = new Artwork();
				artwork.setId(ARTWORK_ID);
				return artwork;
			} else {
				return null;
			}
		});
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(artworkRepository.save(any(Artwork.class))).thenAnswer(returnParameterAsAnswer);
    }

    // test public Artwork saveArtwork(Artwork artwork)
	@Test
	public void testSaveArtwork() {
		Artwork artwork = new Artwork();
		artwork.setId(ARTWORK_ID);
		artwork.setName(NONEXISTING_KEY);
		artist.setUsername(ARTIST_USERNAME);
		artist.setId(ARTIST_ID);
		artwork.setArtist(artist);
		artGallery.setId(ART_GALLERY_KEY);
		artGallery.setName(testArtGalleryName);
		artwork.setArtGallery(artGallery);
		Artwork newArtwork=new Artwork();
		try {
			newArtwork = artworkService.saveArtwork(artwork);
		} catch (Exception e) {
			fail();
		}

		assertNotNull(newArtwork);
	}
	
	// test public Artwork saveArtwork(Artwork artwork)
	@Test
	public void testSaveArtworkWithExistingArtworkName() {
		Artwork artwork = new Artwork();
		artwork.setId(ARTWORK_ID);
		artwork.setName(ARTWORK_KEY);
		artist.setUsername(ARTIST_USERNAME);
		artist.setId(ARTIST_ID);
		artwork.setArtist(artist);
		artGallery.setId(ART_GALLERY_KEY);
		artGallery.setName(testArtGalleryName);
		artwork.setArtGallery(artGallery);
		Artwork newArtwork=new Artwork();
		try {
			newArtwork = artworkService.saveArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertNull(newArtwork);
	}
	
	// test public Artwork saveArtwork(Artwork artwork)
		@Test
		public void testSaveArtworkWithNonExistingArtistUsername() {
			Artwork artwork = new Artwork();
			artwork.setId(ARTWORK_ID);
			artwork.setName(NONEXISTING_KEY);
			artist.setUsername(Non_Existant_ARTIST_USERNAME);
			artist.setId(ARTIST_ID);
			artwork.setArtist(artist);
			artGallery.setId(ART_GALLERY_KEY);
			artGallery.setName(testArtGalleryName);
			artwork.setArtGallery(artGallery);
			Artwork newArtwork=new Artwork();
			try {
				newArtwork = artworkService.saveArtwork(artwork);
			} catch (Exception e) {
				fail();
			}
			assertNull(newArtwork);
		}
		
		// test public Artwork saveArtwork(Artwork artwork)
	@Test
	public void testSaveArtworkWithNonExistingArtGalleryName() {
		Artwork artwork = new Artwork();
		artwork.setId(ARTWORK_ID);
		artwork.setName(NONEXISTING_KEY);
		artist.setUsername(ARTIST_USERNAME);
		artist.setId(ARTIST_ID);
		artwork.setArtist(artist);
		artGallery.setId(ART_GALLERY_KEY);
		artGallery.setName(NonExistingArtGalleryName);
		artwork.setArtGallery(artGallery);
		Artwork newArtwork=new Artwork();
		try {
			newArtwork = artworkService.saveArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertNull(newArtwork);
	}
	
    // test public boolean updateArtwork(Artwork artwork)
	@Test
	public void testUpdateArtwork() {
		Boolean artworkSaved=true;
		Artwork artwork = new Artwork();
		artwork.setId(ARTWORK_ID);
		artwork.setName(ARTWORK_KEY);
		artist.setId(ARTIST_ID);
		artGallery.setId(ART_GALLERY_KEY);
		artwork.setArtist(artist);
		artwork.setArtGallery(artGallery);
		try {
			artworkSaved = artworkService.updateArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, artworkSaved);
	}
	
	// test public boolean updateArtwork(Artwork artwork) : non existing arwtorkID
	@Test
	public void testUpdateArtworkWithWrongArtworkID() {
		Boolean artworkSaved=true;
		Artwork artwork = new Artwork();
		artwork.setId(Non_Existant_Artwork_ID);
		artwork.setName(ARTWORK_KEY);
		artist.setId(ARTIST_ID);
		artGallery.setId(ART_GALLERY_KEY);
		artwork.setArtist(artist);
		artwork.setArtGallery(artGallery);
		try {
			artworkSaved = artworkService.updateArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, artworkSaved);
	}
	
	// test public boolean updateArtwork(Artwork artwork) : non existing artistID
	@Test
	public void testUpdateArtworkWithWrongArtistID() {
		Boolean artworkSaved=true;
		Artwork artwork = new Artwork();
		artwork.setId(ARTWORK_ID);
		artwork.setName(ARTWORK_KEY);
		artist.setId(Non_Existant_ARTIST_ID);
		artGallery.setId(ART_GALLERY_KEY);
		artwork.setArtist(artist);
		artwork.setArtGallery(artGallery);
		try {
			artworkSaved = artworkService.updateArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, artworkSaved);
	}
		
	// test public boolean updateArtwork(Artwork artwork) : non existing artGalleryID
	@Test
	public void testUpdateArtworkWithWrongGalleryID() {
		Boolean artworkSaved=true;
		Artwork artwork = new Artwork();
		artwork.setId(ARTWORK_ID);
		artwork.setName(ARTWORK_KEY);
		artist.setId(ARTIST_ID);
		artGallery.setId(Non_Existing_ART_GALLERY_KEY);
		artwork.setArtist(artist);
		artwork.setArtGallery(artGallery);
		try {
			artworkSaved = artworkService.updateArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, artworkSaved);
	}
		
    // test public Artwork getArtworkByName(String name)
	@Test
	public void testGetArtworkByName() {
		Artwork artwork = new Artwork();
		artwork.setId(ARTWORK_ID);
		artwork.setName(ARTWORK_KEY);
		Artwork newArtwork=new Artwork();
		try {
			newArtwork=artworkService.getArtworkByName(ARTWORK_KEY);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(newArtwork);
		assertEquals(newArtwork.getId(), artwork.getId());
		assertEquals(newArtwork.getName(), artwork.getName());
	}
	
	// test public Artwork getArtworkByName(String name)
	@Test
	public void testGetNonExistantArtworkByName() {
		Artwork artwork = new Artwork();
		artwork.setId(ARTWORK_ID);
		artwork.setName(NONEXISTING_KEY);
		Artwork newArtwork=new Artwork();
		try {
			newArtwork=artworkService.getArtworkByName(NONEXISTING_KEY);
		} catch (Exception e) {
			fail();
		}
		assertNull(newArtwork);
	}
	
    //test public bool deleteArtworkByID(int artworkId)
	@Test
	public void testDeleteArtworkByID() {
		Boolean artworkDeleted=false;
		try {
			artworkDeleted=artworkService.deleteArtworkById(ARTWORK_ID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, artworkDeleted);
				
	}
    
	//test public bool deleteArtworkByID(int artworkId) : non existant artworkID
	@Test
	public void testDeleteNonExistantArtworkByID() {
		Boolean artworkDeleted=false;
		try {
			artworkDeleted=artworkService.deleteArtworkById(Non_Existant_Artwork_ID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, artworkDeleted);
	}
}
