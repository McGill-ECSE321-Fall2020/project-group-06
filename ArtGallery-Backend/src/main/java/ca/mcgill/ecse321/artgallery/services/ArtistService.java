package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.model.Artwork;

/**
 * <p>
 * ArtistService: Methods used by artists (upload, remove artworks, artist
 * transaction history)
 * </p>
 */
@Service
public class ArtistService {
  
  @Autowired
  ArtworkRepository artworkRepository;
  
  /**
   * REQ2.1 The art gallery system shall allow the artist to upload an
   * artwork.
   * 
   * @param artwork the artwork to be added
   * @return artwork the artwork that was added
   * @author Andre-Walter Panzini
   */
  public Artwork uploadArtwork(Artwork artwork) {
      artwork.setForSale(true);
      artworkRepository.save(artwork);
      
      return artwork;
  }
  
  /**
   * REQ2.2 The art gallery system shall allow the artist to remove an
   * artwork.
   * 
   * @param artworkId the artwork ID from the database
   * @return artwork the artwork that was removed
   * @author Andre-Walter Panzini
   */
  public Artwork removeArtwork(int artworkID) {
      Artwork artwork = artworkRepository.findArtworkById(artworkID);
      artworkRepository.delete(artwork);
      return artwork;
  }
}
