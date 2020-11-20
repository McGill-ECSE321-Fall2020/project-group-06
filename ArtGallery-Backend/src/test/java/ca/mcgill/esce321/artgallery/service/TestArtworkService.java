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
    
    private static final String ArtworkName = "my Artwork name";
    private static final String NonExistant_ArtworkName = "not my Artwork name";
    
    private static final int ArtworkID = 100;
    private static final int NonExistant_ArtworkID = 50;
    
    private static final String ArtGalleryName = "my Art_Gallery name";
    private static final String NonExistant_ArtGalleryName = "not my Art_Gallery name";
    
	private static final int ArtGalleryID = 15;
	private static final int NonExistant_ArtGalleryID = 30;
    
    private static final String ArtistUsername = "my Artist username";
    private static final String NonExistant_ArtistUsername = "not my Artist username";
    
    private static final int ArtistID = 20;
    private static final int NonExistant_ArtistID = 10;
    
    private Artist artist = new Artist();
    private ArtGallery artGallery = new ArtGallery();



    @BeforeEach
    public void setMockOutput() {
        lenient().when(artworkRepository.findArtworkByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ArtworkName)) {
                Artwork artwork = new Artwork();
                artwork.setId(ArtworkID);
                artwork.setName(ArtworkName);
                return artwork;
            } else {
                return null;
            }
        });
        //Art Gallery by name
		lenient().when(artGalleryRepository.findArtGalleryByName(anyString()))
		.thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtGalleryName)) {
				ArtGallery artGallery = new ArtGallery();
				artGallery.setName(ArtGalleryName);
				artGallery.setId(ArtGalleryID);
				return artGallery;
			} else {
				return null;
			}
		});
        // artist invocation on mock
		lenient().when(artistRepository.findArtistByUsername(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtistUsername)) {
				Artist artist = new Artist();
                artist.setId(ArtistID);
                artist.setArtwork(new HashSet<Artwork>());
                artist.setUsername(ArtistUsername);
				return artist;
			} else {
				return null;
			}
		});
		//artGallery by ID
		lenient().when(artGalleryRepository.findArtGalleryById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtGalleryID)) {
				ArtGallery artGallery = new ArtGallery();
				artGallery.setId(ArtGalleryID);
				return artGallery;
			} else {
				return null;
			}
		});
		//artist by ID
		lenient().when(artistRepository.findArtistById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtistID)) {
				Artist artist = new Artist();
				artist.setId(ArtistID);
				artist.setArtwork(new HashSet<Artwork>());
                artist.setUsername(ArtistUsername);
				return artist;
			} else {
				return null;
			}
		});
		//find Artwork By ID
		lenient().when(artworkRepository.findArtworkById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtworkID)) {
				Artwork artwork = new Artwork();
				artwork.setId(ArtworkID);
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
		artwork.setId(ArtworkID);
		artwork.setName(NonExistant_ArtworkName);
		artist.setUsername(ArtistUsername);
		artist.setId(ArtistID);
		artwork.setArtist(artist);
		artGallery.setId(ArtGalleryID);
		artGallery.setName(ArtGalleryName);
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
		artwork.setId(ArtworkID);
		artwork.setName(ArtworkName);
		artist.setUsername(ArtistUsername);
		artist.setId(ArtistID);
		artwork.setArtist(artist);
		artGallery.setId(ArtGalleryID);
		artGallery.setName(ArtGalleryName);
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
			artwork.setId(ArtworkID);
			artwork.setName(NonExistant_ArtworkName);
			artist.setUsername(NonExistant_ArtistUsername);
			artist.setId(ArtistID);
			artwork.setArtist(artist);
			artGallery.setId(ArtGalleryID);
			artGallery.setName(ArtGalleryName);
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
		artwork.setId(ArtworkID);
		artwork.setName(NonExistant_ArtworkName);
		artist.setUsername(ArtistUsername);
		artist.setId(ArtistID);
		artwork.setArtist(artist);
		artGallery.setId(ArtGalleryID);
		artGallery.setName(NonExistant_ArtGalleryName);
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
		artwork.setId(ArtworkID);
		artwork.setName(ArtworkName);
		artist.setId(ArtistID);
		artGallery.setId(ArtGalleryID);
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
		artwork.setId(NonExistant_ArtworkID);
		artwork.setName(ArtworkName);
		artist.setId(ArtistID);
		artGallery.setId(ArtGalleryID);
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
		artwork.setId(ArtworkID);
		artwork.setName(ArtworkName);
		artist.setId(NonExistant_ArtistID);
		artGallery.setId(ArtGalleryID);
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
		artwork.setId(ArtworkID);
		artwork.setName(ArtworkName);
		artist.setId(ArtistID);
		artGallery.setId(NonExistant_ArtGalleryID);
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
		artwork.setId(ArtworkID);
		artwork.setName(ArtworkName);
		Artwork newArtwork=new Artwork();
		try {
			newArtwork=artworkService.getArtworkByName(ArtworkName);
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
		artwork.setId(ArtworkID);
		artwork.setName(NonExistant_ArtworkName);
		Artwork newArtwork=new Artwork();
		try {
			newArtwork=artworkService.getArtworkByName(NonExistant_ArtworkName);
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
			artworkDeleted=artworkService.deleteArtworkById(ArtworkID);
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
			artworkDeleted=artworkService.deleteArtworkById(NonExistant_ArtworkID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, artworkDeleted);
	}
}
