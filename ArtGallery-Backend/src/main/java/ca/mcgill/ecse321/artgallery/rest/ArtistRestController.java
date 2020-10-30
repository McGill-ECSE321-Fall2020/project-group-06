package ca.mcgill.ecse321.artgallery.rest;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.services.ArtistService;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.services.ArtistService;

@RestController
@RequestMapping("/api/artist")
public class ArtistRestController {

	private static final Logger logger = LoggerFactory.getLogger(ArtistRestController.class);

	@Autowired
	ArtistService artistService;

	/**
	 * TESTED WITH POSTMAN
	 * 
	 * @param artist
	 * @return
	 */
	@PostMapping("/createArtist")
	public ResponseEntity<Void> createArtist(@Valid @RequestBody Artist artist) {

		logger.info("creating artist profile");

		if (artist.getUsername() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (artist.getPassword() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
			if (artistService.saveArtist(artist) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		} catch (Exception e) {
			logger.error("Exception when creating a new artist" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	/**
	 * Tested with Postman
	 * @param artist
	 * @return
	 */
	@PutMapping("/updateArtist")
	    public ResponseEntity<Void> updateArtist(@Valid @RequestBody Artist artist) {

	        logger.info("updating artist profile");

	        if (artist.getUsername() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	        if (artist.getPassword() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	          }

	        try {
	            if (artistService.updateArtist(artist) == false) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	            } else {
	                return ResponseEntity.status(HttpStatus.OK).build();
	            }
	        } catch (Exception e) {
	            logger.error("Exception when updating Artist"+e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	/**
	 * Http endpoint to get artist by username
	 * Tested with Postman
	 * @param username
	 * @return Arist object
	 */
	@GetMapping("/getArtist/{username}")
	public ResponseEntity<Artist> getArtistByUsername(@PathVariable("username") String username) {

		logger.info("get artist by username");

		if (username == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
			if (artistService.getArtistByUsername(username) == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				return ResponseEntity.ok(artistService.getArtistByUsername(username));
			}
		} catch (Exception e) {
			logger.error("Exception when getting artist by username" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * REQ2.1: The art gallery system shall allow an artist to upload an artwork.
	 * 
	 * @author Andre-Walter Panzini
	 */
	@PostMapping("/uploadArtwork")
	public ResponseEntity<Void> uploadArtwork(@RequestBody Artwork artwork) {

		if (artwork.getArtist() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (artwork.getName() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (artwork.getArtGallery() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		try {
			artistService.uploadArtwork(artwork);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			logger.error("Exception when uploading artwork from artist"+e);
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
			boolean isRemoved = artistService.removeArtwork(artworkID);

			if (isRemoved) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

		} catch (Exception e) {
			logger.error("Exception when removing artwork from artist"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * REQ2.3 The art gallery system shall allow an artist to keep track of its
	 * transaction history.
	 * 
	 * TESTED WITH POSTMAN
	 * 
	 * @param String username
	 * @return List<Transaction> The transaction history
	 * @author Olivier Normandin
	 */

	@GetMapping("/trackTransactionHistory/{username}")
	public ResponseEntity<ArrayList<Transaction>> trackTransactionHistory(@PathVariable("username") String username) {

		logger.info("get artist by username");

		if (username == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
			if (artistService.getArtistByUsername(username) == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
				artistService.getArtistByUsername(username).getTransaction().forEach(transaction -> {
					transactionList.add(transaction);
				});
				return ResponseEntity.ok(transactionList);
			}
		} catch (Exception e) {
			logger.error("Exception when getting artist by username" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
