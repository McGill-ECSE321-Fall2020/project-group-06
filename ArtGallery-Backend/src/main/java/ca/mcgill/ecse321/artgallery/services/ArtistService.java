package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
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
  @Autowired
  ArtistRepository artistRepository;
  @Autowired
  ArtGalleryRepository artGalleryRepository;
  
  /**
   * REQ2.1 The art gallery system shall allow the artist to upload an
   * artwork.
   * 
   * @param artwork the artwork to be added
   * @return artwork the artwork that was added
   * @author Andre-Walter Panzini
   */
  @Transactional
  public boolean uploadArtwork(Artwork artwork) {
      Artist artist = artistRepository.findArtistById(artwork.getArtist().getId());
      
      if (artist == null) {
        return false;
      }
      
      ArtGallery artGallery = artGalleryRepository.findArtGalleryById(artwork.getArtGallery().getId());
      
      if (artGallery == null)
      {
        return false;
      }
      
      artworkRepository.save(artwork);
  
      return true;
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
  
}
