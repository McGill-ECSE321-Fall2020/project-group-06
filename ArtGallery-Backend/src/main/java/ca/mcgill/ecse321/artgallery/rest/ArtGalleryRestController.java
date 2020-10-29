package ca.mcgill.ecse321.artgallery.rest;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.services.ArtGalleryService;

@RestController
@RequestMapping("/api/artgallery")
public class ArtGalleryRestController {

	private static final Logger logger = LoggerFactory.getLogger(ArtGalleryRestController.class);

	@Autowired
	ArtGalleryService artGalleryService;

	/**
	 * REQ5.3: The art gallery system should be able to browse the artworks. Http
	 * endpoit for this requirement. TESTED WITH POSTMAN
	 * 
	 * @return List of Artworks
	 * @author Sen Wang
	 */
	@GetMapping("/allArtworks")
	public ResponseEntity<ArrayList<Artwork>> getAllArtworks() {
		try {
			return ResponseEntity.ok(artGalleryService.getAllArtworks());
		} catch (Exception e) {
			logger.error("Exception when getting all artworks for art gallery");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/removeArtwork")
	public ResponseEntity<Artwork> removeArtwork(@RequestParam int artworkID) {
		try {
			return ResponseEntity.ok(artGalleryService.removeArtwork(artworkID));
		} catch (Exception e) {
			logger.error("Exception when removing artwork from artGallery");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/createArtGallery")
	public ResponseEntity<Void> createArtGallery(@Valid @RequestBody ArtGallery artGallery){
		logger.info("creating art gallery");

		if(artGallery.getName() == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
            if (artGalleryService.saveArtGallery(artGallery) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            logger.error("Exception when creating a new art gallery " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}

	@GetMapping("/getArtGallery/{name}")
    public ResponseEntity<ArtGallery> getArtGalleryByName(@PathVariable("name") String name) {

        logger.info("get art gallery by name");

        if (name == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (artGalleryService.getArtGalleryByName(name) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.ok(artGalleryService.getArtGalleryByName(name));
            }
        } catch (Exception e) {
            logger.error("Exception when getting art gallery by name" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
