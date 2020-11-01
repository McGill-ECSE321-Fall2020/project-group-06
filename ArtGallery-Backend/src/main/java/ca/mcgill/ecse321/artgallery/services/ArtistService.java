package ca.mcgill.ecse321.artgallery.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Transaction;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.dto.ArtistDto;

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

	@Autowired
	UserRepository userRepository;

	/**
	 * REQ2.3 The art gallery system shall allow an artist to keep track of its
	 * transaction history.
	 * 
	 * @param int The artist ID
	 * @return List<Transaction> The transaction history
	 * @author Olivier Normandin
	 */

	@Transactional
	public ArrayList<Transaction> viewTransactionHistory(int artistID) {
		ArrayList<Transaction> transactionHistory = new ArrayList<>();
		Artist artist = artistRepository.findArtistById(artistID);
		if (artist == null) {
			return transactionHistory;
		} else {
			artist.getTransaction().forEach(transaction -> {
				transactionHistory.add(transaction);
			});
		}
		return transactionHistory;
	}

	/**
	 * REQ2.1 The art gallery system shall allow the artist to upload an artwork.
	 * 
	 * @param artwork the artwork to be added
	 * @return artwork the artwork that was added
	 * @author Andre-Walter Panzini
	 */
	@Transactional
	public boolean uploadArtwork(Artwork artwork) {
		Artwork newArtwork = new Artwork();

		if (artworkRepository.findArtworkByName(artwork.getName()) != null) {
			return false;
		}
		if (artistRepository.findArtistByUsername(artwork.getArtist().getUsername()) == null) {
			return false;
		}
		if (artGalleryRepository.findArtGalleryByName(artwork.getArtGallery().getName()) == null) {
			return false;
		}

		newArtwork.setName(artwork.getName());
		newArtwork.setArtist(artistRepository.findArtistByUsername(artwork.getArtist().getUsername()));
		newArtwork.setArtGallery(artGalleryRepository.findArtGalleryByName(artwork.getArtGallery().getName()));
		artworkRepository.save(newArtwork);

		return true;
	}

	/**
	 * REQ2.2 The art gallery system shall allow the artist to remove an artwork.
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
		} else {
			artworkRepository.delete(artwork);
			return true;
		}

	}

	/**
	 * get artwork uploaded by artist
	 * 
	 * @param artist
	 * @return array list of artwork
	 */
	@Transactional
	public ArrayList<Artwork> getArtworkUploadedByArtist(String username) {
		Artist artist = new Artist();
		if (getArtistByUsername(username) == null) {
			return null;
		}
		artist = getArtistByUsername(username);
		ArrayList<Artwork> artworksUploadedByArtist = new ArrayList<>(artist.getArtwork());
		return artworksUploadedByArtist;
	}

	/**
	 * Creates a new artist service method
	 * 
	 * @param artist
	 * @return Boolean if the artist is created
	 * @author Sen Wang
	 */
	@Transactional
	public Boolean saveArtist(Artist artist) {
		// a user/customer/artist with username already exist
		if (userRepository.findUserByUsername(artist.getUsername()) != null) {
			return false;
		} else {
			artistRepository.save(artist);
			return true;
		}
	}

	/**
	 * update artist
	 * 
	 * @param artist
	 * @return boolean
	 */
	@Transactional
	public Boolean updateArtist(Artist artist) {
		// check that the artist exists
		if (userRepository.findUserByUsername(artist.getUsername()) == null) {
			return false;
		} else {
			Artist newArtist;
			newArtist = artistRepository.findArtistByUsername(artist.getUsername());
			newArtist.setArtwork(artist.getArtwork());
			newArtist.setBankAccountNumber(artist.getBankAccountNumber());
			newArtist.setDescription(artist.getDescription());
			newArtist.setEmail(artist.getEmail());
			newArtist.setFirstName(artist.getFirstName());
			newArtist.setLastName(artist.getLastName());
			newArtist.setPassword(artist.getPassword());
			newArtist.setPhoneNumber(artist.getPhoneNumber());
			newArtist.setPicture(artist.getPicture());
			newArtist.setTransaction(artist.getTransaction());
			newArtist.setUsername(artist.getUsername());
			artistRepository.save(newArtist);
			return true;
		}
	}

	/**
	 * This methods finds an artist by username
	 * 
	 * @param username
	 * @return Artist object
	 */
	@Transactional
	public Artist getArtistByUsername(String username) {
		if (artistRepository.findArtistByUsername(username) == null) {
			return null;
		} else {
			return artistRepository.findArtistByUsername(username);
		}
	}

	/**
	 * This service method updates an artist info
	 * 
	 * @param artistDto
	 * @return boolean
	 * @author Sen Wang
	 */
	public Boolean updateArtist(ArtistDto artistDto) {
		if (artistRepository.findArtistByUsername(artistDto.getUsername()) == null) {
			return false;
		} else {
			Artist updatedArtist = new Artist();
			updatedArtist = artistRepository.findArtistByUsername(artistDto.getUsername());
			updatedArtist.setArtwork(artistDto.getArtwork());
			updatedArtist.setBankAccountNumber(artistDto.getBankAccountNumber());
			updatedArtist.setDescription(artistDto.getDescription());
			updatedArtist.setEmail(artistDto.getEmail());
			updatedArtist.setFirstName(artistDto.getFirstName());
			updatedArtist.setLastName(artistDto.getLastName());
			updatedArtist.setPhoneNumber(artistDto.getPhoneNumber());
			updatedArtist.setPicture(artistDto.getPicture());
			updatedArtist.setTransaction(artistDto.getTransaction());
			artistRepository.save(updatedArtist);
			return true;
		}
	}

}
