package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;

/**
 * <p>
 * ArtistService: Methods used by artists (upload, remove artworks, artist
 * transaction history)
 * </p>
 */
@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Creates a new artist service method
     * 
     * @param artist
     * @return Boolean if the artist is created
     * @author Sen Wang
     */
    public Boolean saveArtist(Artist artist) {
        // a user/customer/artist with username already exist
        if (userRepository.findUserByUsername(artist.getUsername()) != null) {
            return false;
        } else {
            artistRepository.save(artist);
            return true;
        }
    }

    /**
     * This methods finds an artist by username
     * 
     * @param username
     * @return Artist object
     */
    public Artist getArtistByUsername(String username) {
        if (artistRepository.findArtistByUsername(username) == null) {
            return null;
        } else {
            return artistRepository.findArtistByUsername(username);
        }
    }

    // update artist info method

}
