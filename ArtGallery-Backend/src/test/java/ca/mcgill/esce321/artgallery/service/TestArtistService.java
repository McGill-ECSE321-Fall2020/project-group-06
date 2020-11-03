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
import ca.mcgill.ecse321.artgallery.dto.ArtistDto;
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

	private static final String ArtistUsername = "my Artist username";
	private static final String NonExistant_ArtistUsername = "not my Artist username";

	private static final int ArtistID = 20;
	private static final int NonExistant_ArtistID = 90;

	private static final String ArtworkName = "my Artwork name";
	private static final String NonExistant_ArtworkName = "not my Artwork name";

	private static final int ArtworkID = 30;
	private static final int NonExistant_ArtworkID = 31;

	private static final String ArtGalleryName = "my Art_Gallery name";
	private static final String NonExistant_ArtGalleryName = "not my Art_Gallery name";

	private static final int ArtGalleryID = 40;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(artistRepository.findArtistByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtistUsername)) {
				Artist artist = new Artist();
				artist.setUsername(ArtistUsername);
				HashSet<Artwork> artistArtworks = new HashSet<Artwork>();
				Artwork artwork1 = new Artwork();
				Artwork artwork2 = new Artwork();
				artistArtworks.add(artwork1);
				artistArtworks.add(artwork2);
				artist.setArtwork(artistArtworks);
				return artist;
			} else {
				return null;
			}
		});
		// artist by ID
		lenient().when(artistRepository.findArtistById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtistID)) {
				Artist artist = new Artist();
				artist.setId(ArtistID);
				artist.setArtwork(new HashSet<Artwork>());
				artist.setUsername(ArtistUsername);
				Transaction transaction = new Transaction();
				HashSet<Transaction> transactionHistory = new HashSet<Transaction>();
				transactionHistory.add(transaction);
				artist.setTransaction(transactionHistory);
				return artist;
			} else {
				return null;
			}
		});
		//// find Artwork by Name
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
		// find Artwork By ID
		lenient().when(artworkRepository.findArtworkById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtworkID)) {
				Artwork artwork = new Artwork();
				artwork.setId(ArtworkID);
				return artwork;
			} else {
				return null;
			}
		});
		lenient().when(userRepository.findUserByUsername(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ArtistUsername)) {
				User user = new User();
				user.setId(ArtistID);
				user.setUsername(ArtistUsername);
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
		ArrayList<Transaction> transactionHistory2 = new ArrayList<Transaction>();
		try {
			transactionHistory2 = artistService.viewTransactionHistory(ArtistID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(transactionHistory2.size(), 1);
		;
	}
	
	// test public List<Transaction> viewTransactionHistory(int artistID) with a bad artistID
	@Test
	public void testViewTransactionHistoryBadKey() {
		ArrayList<Transaction> transactionHistory2 = new ArrayList<Transaction>();
		try {
			transactionHistory2 = artistService.viewTransactionHistory(NonExistant_ArtistID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(transactionHistory2.size(), 0);
		;
	}

	// test public Artwork uploadArtwork(Artwork artwork) : existing
	// artist/artgallery + non existing artwork
	@Test
	public void testUploadArtwork() {
		boolean uploadWorked = true;
		Artist artist = new Artist();
		artist.setUsername(ArtistUsername);
		ArtGallery artGallery = new ArtGallery();
		artGallery.setName(ArtGalleryName);
		Artwork artwork = new Artwork();
		artwork.setName(NonExistant_ArtworkName);
		artwork.setArtGallery(artGallery);
		artwork.setArtist(artist);
		try {
			uploadWorked = artistService.uploadArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, uploadWorked);
	}

	// test public Artwork uploadArtwork(Artwork artwork) : existing
	// artist/artgallery + existing artwork
	@Test
	public void testUploadExistingArtwork() {
		boolean uploadWorked = true;
		Artist artist = new Artist();
		artist.setUsername(ArtistUsername);
		ArtGallery artGallery = new ArtGallery();
		artGallery.setName(ArtGalleryName);
		Artwork artwork = new Artwork();
		artwork.setName(ArtworkName);
		artwork.setArtGallery(artGallery);
		artwork.setArtist(artist);
		try {
			uploadWorked = artistService.uploadArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, uploadWorked);
	}

	// test public Artwork uploadArtwork(Artwork artwork) : non existing art gallery
	@Test
	public void testUploadArtworkWithNonExistingArtGallery() {
		boolean uploadWorked = true;
		Artist artist = new Artist();
		artist.setUsername(ArtistUsername);
		ArtGallery artGallery = new ArtGallery();
		artGallery.setName(NonExistant_ArtGalleryName);
		Artwork artwork = new Artwork();
		artwork.setName(NonExistant_ArtworkName);
		artwork.setArtGallery(artGallery);
		artwork.setArtist(artist);
		try {
			uploadWorked = artistService.uploadArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, uploadWorked);
	}

	// test public Artwork uploadArtwork(Artwork artwork) : non existing artist
	@Test
	public void testUploadArtworkWithNonExistingArtist() {
		boolean uploadWorked = true;
		Artist artist = new Artist();
		artist.setUsername(NonExistant_ArtistUsername);
		ArtGallery artGallery = new ArtGallery();
		artGallery.setName(ArtGalleryName);
		Artwork artwork = new Artwork();
		artwork.setName(NonExistant_ArtworkName);
		artwork.setArtGallery(artGallery);
		artwork.setArtist(artist);
		try {
			uploadWorked = artistService.uploadArtwork(artwork);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, uploadWorked);
	}

	// test public boolean removeArtwork(int artworkID) : existing artwork
	@Test
	public void testRemoveArtwork() {
		boolean removeWorked = false;
		try {
			removeWorked = artistService.removeArtwork(ArtworkID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, removeWorked);
	}

	// test public boolean removeArtwork(int artworkID) : non existing artwork
	@Test
	public void testRemoveNonExistantArtwork() {
		boolean removeWorked = false;
		try {
			removeWorked = artistService.removeArtwork(NonExistant_ArtworkID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, removeWorked);
	}

	// test public List<Artwork> getArtworkUploadedByArtist(Artist artist) :
	// existant artist
	@Test
	public void testGetArtworkUploadedByArtist() {

		ArrayList<Artwork> updatedArworkList = new ArrayList<Artwork>();
		try {
			updatedArworkList = artistService.getArtworkUploadedByArtist(ArtistUsername);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(updatedArworkList);
		assertEquals(2, updatedArworkList.size());
	}

	// test public List<Artwork> getArtworkUploadedByArtist(Artist artist) :
	// non existant artist
	@Test
	public void testGetArtworkUploadedByNonExistantArtist() {
		ArrayList<Artwork> updatedArworkList = new ArrayList<Artwork>();
		try {
			updatedArworkList = artistService.getArtworkUploadedByArtist(NonExistant_ArtistUsername);
		} catch (Exception e) {
			fail();
		}
		assertNull(updatedArworkList);
	}

	// test public Boolean saveArtist(Artist artist) : non existant artist
	@Test
	public void testSaveArtist() {
		boolean saveWorked = false;
		Artist artist = new Artist();
		artist.setUsername(NonExistant_ArtistUsername);
		try {
			saveWorked = artistService.saveArtist(artist);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, saveWorked);
	}

	// test public Boolean saveArtist(Artist artist) : existant artist
	@Test
	public void testSaveExistantArtist() {
		boolean saveWorked = false;
		Artist artist = new Artist();
		artist.setUsername(ArtistUsername);
		try {
			saveWorked = artistService.saveArtist(artist);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, saveWorked);
	}

	// public Boolean updateArtist(Artist artist) : existant artist
	@Test
	public void testUpdateArtist() {
		boolean saveWorked = false;
		Artist artist = new Artist();
		artist.setUsername(ArtistUsername);
		try {
			saveWorked = artistService.updateArtist(artist);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, saveWorked);
	}
	// public Boolean updateArtist(ArtistDto artistdto) : existant artist
	@Test
	public void testUpdateArtistDto() {
		boolean saveWorked = false;
		ArtistDto artistdto=new ArtistDto();
		artistdto.setUsername(ArtistUsername);
		try {
			saveWorked = artistService.updateArtist(artistdto);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, saveWorked);
	}
	
	// public Boolean updateArtist(ArtistDto artistdto) : bad username, no existing artist
	@Test
	public void testUpdateArtistDtoBadUsername() {
		boolean saveWorked = false;
		ArtistDto artistdto=new ArtistDto();
		artistdto.setUsername(NonExistant_ArtistUsername);
		try {
			saveWorked = artistService.updateArtist(artistdto);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, saveWorked);
	}

	// public Boolean updateArtist(Artist artist) : non existant artist
	@Test
	public void updatedNonExsitantArtist() {
		boolean saveWorked = false;
		Artist artist = new Artist();
		artist.setUsername(NonExistant_ArtistUsername);
		try {
			saveWorked = artistService.updateArtist(artist);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, saveWorked);
	}

	// public Artist getArtistByUsername(String username) : existant artist
	@Test
	public void testGetArtistByUsername() {
		Artist artist = null;
		try {
			artist = artistService.getArtistByUsername(ArtistUsername);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(artist);
	}

	// public Artist getArtistByUsername(String username) : non existant artist
	@Test
	public void testGetNonExistantArtistByUsername() {
		Artist artist = null;
		try {
			artist = artistService.getArtistByUsername(NonExistant_ArtistUsername);
		} catch (Exception e) {
			fail();
		}
		assertNull(artist);
	}

	// test public Boolean updateArtist(ArtistDto artistDto) : existant artist
	@Test
	public void testupdateArtist() {
		boolean saveWorked = false;
		Artist artist = new Artist();
		artist.setUsername(ArtistUsername);
		try {
			saveWorked = artistService.updateArtist(artist);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, saveWorked);
	}

	// test public Boolean updateArtist(ArtistDto artistDto) : non existant artist
	@Test
	public void testUpdateNonExistantArtist() {
		boolean saveWorked = true;
		Artist artist = new Artist();
		artist.setUsername(NonExistant_ArtistUsername);
		try {
			saveWorked = artistService.updateArtist(artist);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, saveWorked);
	}

	// test remove artist : existant artist
	@Test
	public void testRemoveArtist() {
		boolean removed = false;
		try {
			removed = artistService.removeArtist(ArtistID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(true, removed);

	}

	// test remove artist : non existant artist
	@Test
	public void testRemoveNonExistantArtist() {
		boolean removed = false;
		try {
			removed = artistService.removeArtist(NonExistant_ArtistID);
		} catch (Exception e) {
			fail();
		}
		assertEquals(false, removed);

	}

}
