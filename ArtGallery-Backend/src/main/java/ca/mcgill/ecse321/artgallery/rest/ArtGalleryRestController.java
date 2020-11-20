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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.services.ArtGalleryService;

/**
 * Art Gallery REST controller class
 * @author Sen Wang
 * @author Noah Chamberland
 * @author Justin Legrand
 * @author Olivier Normandin
 * @author Andre-Walter Panzini
 */

@RestController
@RequestMapping("/api/artgallery")
public class ArtGalleryRestController {

	private static final Logger logger = LoggerFactory.getLogger(ArtGalleryRestController.class);

	@Autowired
	ArtGalleryService artGalleryService;

	/**
	 * REQ4.1: The art gallery system shall allow the art gallery to browse all the artwork transactions.
	 * 
	 * @return ArrayList<Transaction> - List of Transactions
	 */
	@GetMapping("/allTransactions")
	public ResponseEntity<ArrayList<Transaction>> getAllTransactions() {
		try {
			return ResponseEntity.ok(artGalleryService.getAllTransactions());
		} catch (Exception e) {
			logger.error("Exception when getting all transactions for art gallery");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * REQ4.2: The art gallery system shall allow the art gallery to remove an artwork.
	 * Post mapping
	 * 
	 * @param Integer artworkID - The artworkID
	 */
	@PostMapping("/removeArtwork/{artworkId}")
	public ResponseEntity<Void> removeArtwork(@PathVariable("artworkId") Integer artworkID) {
		try {
			if (artGalleryService.removeArtworkById(artworkID)) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			logger.error("Exception when removing artwork from artGallery" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * REQ4.3 The art gallery system shall allow the art gallery to take a
	 * commission on each transaction.
	 *
	 * @param Integer transactionID - The transaction ID
	 * @return double - The art gallery's commission on a sold piece of art
	 */
	@GetMapping("/artGalleryCommission/{transactionId}")
	public ResponseEntity<Double> takeCommission(@PathVariable("transactionId") Integer transactionID) {
		if (transactionID == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		try {
			return ResponseEntity.ok(artGalleryService.takeCommission(transactionID));
		} catch (Exception e) {
			logger.error("Exception when taking commission from transaction" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * REQ5.3: The art gallery system should be able to browse the artworks.
	 *
	 * @return ArrayList<Artwork> - List of Artworks
	 */
	@GetMapping("/allArtworks")
	public ResponseEntity<ArrayList<Artwork>> getAllArtworks() {
		try {
			return ResponseEntity.ok(artGalleryService.getAllArtworks());
		} catch (Exception e) {
			logger.error("Exception when getting all artworks for art gallery" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * It shall be possible to update the art gallery profile.
	 * 
	 * @param ArtGallery artGallery - The art gallery profile
	 */
	@PutMapping("/updateArtGallery")
	public ResponseEntity<Void> updateArtGallery(@Valid @RequestBody ArtGallery artGallery) {

		logger.info("updating artGallery profile");

		if (artGallery.getName() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
			if (artGalleryService.updateArtGallery(artGallery) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
		} catch (Exception e) {
			logger.error("Exception when updating Art Gallery" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * It shall be possible to create an art gallery profile
	 * 
	 * @param ArtGallery artGallery - The art gallery profile
	 */
	@PostMapping("/createArtGallery")
	public ResponseEntity<Void> createArtGallery(@Valid @RequestBody ArtGallery artGallery) {
		logger.info("creating art gallery");

		if (artGallery.getName() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
			if (artGalleryService.saveArtGallery(artGallery) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		} catch (Exception e) {
			logger.error("Exception when creating a new art gallery " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * It shall be possible to get an art gallery by name
	 * 
	 * @param String name - The art gallery's name
	 * @return ArtGallery - The art gallery
	 */
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
