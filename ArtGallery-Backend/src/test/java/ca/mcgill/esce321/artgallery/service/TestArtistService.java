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

import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;

@ExtendWith(MockitoExtension.class)
public class TestArtistService {

    @Mock
    private ArtistRepository artistRepository;

    private static final String ARTIST_KEY = "TestArtist";
    private static final String NONEXISTING_KEY = "NotArtist";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(artistRepository.findArtistByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ARTIST_KEY)) {
                Artist artist = new Artist();
                artist.setUsername(ARTIST_KEY);
                return artist;
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

    // test public Artwork uploadArtwork(Artwork artwork)

    // test public boolean removeArtwork(int artworkID)

    // test public List<Artwork> getArtworkUploadedByArtist(Artist artist)

    // test public Boolean saveArtist(Artist artist)

    // public Boolean updateArtist(Artist artist)

    // public Artist getArtistByUsername(String username)

    // test public Boolean updateArtist(ArtistDto artistDto)

    // TODO ADD MORE TESTS IF NEEDED

}
