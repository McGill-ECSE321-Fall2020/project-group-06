package ca.mcgill.ecse321.artgallery.rest;

import java.util.ArrayList;

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
import ca.mcgill.ecse321.artgallery.services.ArtGalleryService;

@RestController
@RequestMapping("/api/artgallery")
public class ArtGalleryRestController {

	private static final Logger logger = LoggerFactory.getLogger(ArtGalleryRestController.class);

	@Autowired
	ArtGalleryService artGalleryService;

	/**
	 * REQ5.3: The art gallery system should be able to browse the artworks. Http
	 * endpoit for this requirement
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
		
		if (artworkID == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		try {
			return ResponseEntity.ok(artGalleryService.removeArtwork(artworkID));
		} catch (Exception e) {
			logger.error("Exception when removing artwork from artGallery");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	   * REQ4.3 The art gallery system shall allow the art gallery to take a commission on each transaction.
	   * 
	   * @param int The transaction ID
	   * @return double The art gallery's commission on a sold piece of art
	   * @author Olivier Normandin
	   */
	@GetMapping("/artGalleryCommission")
	public ResponseEntity<Double> takeCommission(@RequestParam int transactionID) {
		if (transactionID == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		try {
			return ResponseEntity.ok(artGalleryService.takeCommission(transactionID));
		} catch (Exception e) {
			logger.error("Exception when taking commission from transaction");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
