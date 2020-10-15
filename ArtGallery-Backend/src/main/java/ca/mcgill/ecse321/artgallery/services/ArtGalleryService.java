package ca.mcgill.ecse321.artgallery.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.model.Artwork;

/**
 * <p>
 * ArtGalleryService: methods used by artgallery such as browse/remove artworks,
 * commissions
 * </p>
 */
@Service
public class ArtGalleryService {

    @Autowired
    ArtworkRepository artworkRepository;

    /**
     * REQ5.3: The art gallery system should be able to browse the artworks
     * Implement the service method for this requirement
     * 
     * @return List of Artworks
     * @author Sen Wang
     */
    public ArrayList<Artwork> getAllArtworks() {

        // create new artwork array list
        ArrayList<Artwork> artworks = new ArrayList<Artwork>();

        // iterate through the artworks
        for (Artwork artwork : artworkRepository.findAll()) {
            artworks.add(artwork);
        }

        return artworks;
    }
}
