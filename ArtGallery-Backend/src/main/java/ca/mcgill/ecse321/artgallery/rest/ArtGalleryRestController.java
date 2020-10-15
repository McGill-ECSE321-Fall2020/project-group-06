package ca.mcgill.ecse321.artgallery.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.services.ArtGalleryService;

@RestController
@RequestMapping("/api/artgallery")
public class ArtGalleryRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    ArtGalleryService artGalleryService;

    /**
     * REQ5.3: The art gallery system should be able to browse the artworks. Http
     * endpoit for this requirement
     * 
     * @return List of Artworks
     * @author Sen Wang
     */
    @GetMapping("/getAllArtworks")
    public ResponseEntity<ArrayList<Artwork>> getAllArtworks() {
        try {
            return ResponseEntity.ok(artGalleryService.getAllArtworks());
        } catch (Exception e) {
            logger.error("Exception when getting all artworks for art gallery");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
