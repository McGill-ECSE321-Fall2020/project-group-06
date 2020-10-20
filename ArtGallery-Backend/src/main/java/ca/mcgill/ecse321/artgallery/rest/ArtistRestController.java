package ca.mcgill.ecse321.artgallery.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.services.ArtistService;

@RestController
@RequestMapping("/api/artist")
public class ArtistRestController {
  
  private static final Logger logger = LoggerFactory.getLogger(ArtistRestController.class);

  @Autowired
  ArtistService artistService;

  /**
   * REQ2.1: The art gallery system shall allow an artist to upload an artwork.
   * 
   * @author Andre-Walter Panzini
   */
  @GetMapping("/uploadArtwork")
  public ResponseEntity<Void> uploadArtwork(@RequestParam Artwork artwork) {
    
    if(artwork.getId() == 0)
    {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    if(artwork.getArtist() == null)
    {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    if(artwork.getName() == null)
    {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    if(artwork.getPicture() == null)
    {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    try {
      artistService.uploadArtwork(artwork);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (Exception e) {
      logger.error("Exception when uploading artwork from artist");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  /**
   * REQ2.2: The art gallery system shall allow an artist to remove an artwork.
   *  
   * @author Andre-Walter Panzini
   */
  @PostMapping("/removeArtwork")
  public ResponseEntity<Artwork> removeArtwork(@RequestParam int artworkID) {
    try {
          return ResponseEntity.ok(artistService.removeArtwork(artworkID));
      } catch (Exception e) {
          logger.error("Exception when removing artwork from artist");
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
  }
}
