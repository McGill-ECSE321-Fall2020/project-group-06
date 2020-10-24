package ca.mcgill.ecse321.artgallery.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;
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
  @Transactional
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
  @Transactional
  public boolean removeArtwork(int artworkID) {
      Artwork artwork = artworkRepository.findArtworkById(artworkID);
      
      if (artwork == null) {
        return false;
      }
      else {
        artworkRepository.delete(artwork);
        return true;
      }
        
  }
  
  @Transactional
  public List<Artwork> getArtworkUploadedByArtist(Artist artist) {
      List<Artwork> artworksUploadedByArtist = new ArrayList<>();
      artworksUploadedByArtist = (List<Artwork>) artist.getArtwork();
      return artworksUploadedByArtist;
  }
  
}
