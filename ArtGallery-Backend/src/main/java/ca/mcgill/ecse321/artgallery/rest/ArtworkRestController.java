package ca.mcgill.ecse321.artgallery.rest;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.services.ArtworkService;

@RestController
@RequestMapping("/api/artwork")
public class ArtworkRestController {
	private static final Logger logger = LoggerFactory.getLogger(ArtGalleryRestController.class);

    @Autowired
    ArtworkService artworkService;

    /**
     * TESTED WITH POSTMAN
     * @param artwork
     * @return
     */
    @PostMapping("/createArtwork")
	public ResponseEntity<Void> createArtwork(@Valid @RequestBody Artwork artwork){
		logger.info("creating artwork");

		if(artwork.getName() == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
            if (artworkService.saveArtwork(artwork) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            logger.error("Exception when creating a new artwork " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}


	@GetMapping("/getArtwork/{name}")
    public ResponseEntity<Artwork> getArtworkByName(@PathVariable("name") String name) {

        logger.info("get artwork by name");

        if (name == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (artworkService.getArtworkByName(name) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.ok(artworkService.getArtworkByName(name));
            }
        } catch (Exception e) {
            logger.error("Exception when getting artwork by name" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	/**
	 * Tested with postman
	 * @param artwork
	 * @return
	 */
	 @PutMapping("/updateArtwork")
	    public ResponseEntity<Void> updateArtwork(@Valid @RequestBody Artwork artwork) {

	        logger.info("updating artwork profile");

	        if (artwork.getName() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	        if (artwork.getArtist() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	        if (artwork.getArtGallery() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	        try {
	            if (artworkService.updateArtwork(artwork) == false) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	            } else {
	                return ResponseEntity.status(HttpStatus.OK).build();
	            }
	        } catch (Exception e) {
	            System.out.println("in catch");
	            logger.error("Exception when updating Artwork"+e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
}
