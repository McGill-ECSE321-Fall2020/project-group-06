package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
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
import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.services.ArtistService;

@ExtendWith(MockitoExtension.class)
public class TestArtistService {

    @Mock
    private ArtistRepository artistRepository;
    @Mock
    private ArtworkRepository artworkRepository;
    @Mock
    private ArtGalleryRepository artGalleryRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ArtistService artistService;
    private static final String ARTIST_KEY = "TestArtist";
    private static final String NONEXISTING_KEY = "NotArtist";
    private static final int ARTIST_ID=20;
    private static final String ARTWORK_KEY="ArtworkName";
    private static final String BAD_ARTWORK_KEY="BadArtworkName";
    private static final int ARTWORK_ID=30;
    private static final String testArtGalleryName="ArtGalleryName";
    private static final int ART_GALLERY_KEY=40;
    private static final int testUserId=50;
    private static final String USER_KEY="userKey";
    

    

    @BeforeEach
    public void setMockOutput() {
        lenient().when(artistRepository.findArtistByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ARTIST_KEY)) {
                Artist artist = new Artist();
                artist.setUsername(ARTIST_KEY);
                HashSet<Artwork> artistArtworks=new HashSet<Artwork>();
            	Artwork artwork1=new Artwork();
            	Artwork artwork2=new Artwork();
            	artistArtworks.add(artwork1);
            	artistArtworks.add(artwork2);
            	artist.setArtwork(artistArtworks);
                return artist;
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
                artist.setUsername(ARTIST_KEY);
                Transaction transaction = new Transaction();
                HashSet<Transaction> transactionHistory=new HashSet<Transaction>();
            	transactionHistory.add(transaction);
            	artist.setTransaction(transactionHistory);
				return artist;
			} else {
				return null;
			}
		});
		////find Artwork by Name
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
		lenient().when(userRepository.findUserByUsername(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ARTIST_KEY)) {
				User user = new User();
				user.setId(ARTIST_ID);
				user.setUsername(ARTIST_KEY);
				return user;
			} else {
				return null;
			}
		});
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(artistRepository.save(any(Artist.class))).thenAnswer(returnParameterAsAnswer);
    }

    // test public List<Transaction> viewTransactionHistory(int artistID)
    @Test
    public void testViewTransactionHistory() {
    	Artist artist=new Artist();

    	artist.setId(ARTIST_ID);
    	ArrayList<Transaction> transactionHistory2=new ArrayList<Transaction>();
    	try {
			transactionHistory2=artistService.viewTransactionHistory(ARTIST_ID);
		} catch (Exception e) {
			fail();
		}
    	assertEquals(transactionHistory2.size(),1);;
    }

    // test public Artwork uploadArtwork(Artwork artwork)
    @Test
    public void testUploadArtwork() {
    	boolean uploadWorked=true;
    	Artist artist=new Artist();
    	artist.setUsername(ARTIST_KEY);
    	ArtGallery artGallery=new ArtGallery();
    	artGallery.setName(testArtGalleryName);
    	Artwork artwork=new Artwork();
    	artwork.setName(BAD_ARTWORK_KEY);
    	artwork.setArtGallery(artGallery);
    	artwork.setArtist(artist);
    	try {
			uploadWorked=artistService.uploadArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
    	assertEquals(true, uploadWorked);
    }
    // test public boolean removeArtwork(int artworkID)
    @Test
    public void testRemoveArtwork() {
    	boolean removeWorked=true;
    	try {
			removeWorked=artistService.removeArtwork(ARTWORK_ID);
		} catch (Exception e) {
			fail();
		}
    	assertEquals(true, removeWorked);
    }
    // test public List<Artwork> getArtworkUploadedByArtist(Artist artist)
    @Test
    public void testGetArtworkUploadedByArtist() {
    	
    	ArrayList<Artwork> updatedArworkList=new ArrayList<Artwork>();
    	try {
			updatedArworkList=artistService.getArtworkUploadedByArtist(ARTIST_KEY);
		} catch (Exception e) {
			fail();
		}
    	assertNotNull(updatedArworkList);
    	assertEquals(updatedArworkList.size(), 2);
    }
    // test public Boolean saveArtist(Artist artist)
    @Test
    public void testSaveArtist() {
    	boolean saveWorked=true;
    	Artist artist=new Artist();
    	artist.setUsername(NONEXISTING_KEY);
    	try {
			saveWorked=artistService.saveArtist(artist);
		} catch (Exception e) {
			fail();
		}
    	assertEquals(true, saveWorked);
    }
    // public Boolean updateArtist(Artist artist)
    @Test
    public void updateArtist() {
    	boolean saveWorked=true;
    	Artist artist=new Artist();
    	artist.setUsername(ARTIST_KEY);
    	try {
			saveWorked=artistService.updateArtist(artist);
		} catch (Exception e) {
			fail();
		}
    	assertEquals(true, saveWorked);
    }

    // public Artist getArtistByUsername(String username)
    @Test
    public void testGetArtistByUsername() {
    	Artist artist=null;
    	try {
			artist=artistService.getArtistByUsername(ARTIST_KEY);
		} catch (Exception e) {
			fail();
		}
    	assertNotNull(artist);
    }
    // test public Boolean updateArtist(ArtistDto artistDto)
    @Test
    public void testupdateArtist() {
    	boolean saveWorked=true;
    	Artist artist=new Artist();
    	artist.setUsername(ARTIST_KEY);
    	try {
			saveWorked=artistService.updateArtist(artist);
		} catch (Exception e) {
			fail();
		}
    	assertEquals(true, saveWorked);
    }
    // TODO ADD MORE TESTS IF NEEDED

}
