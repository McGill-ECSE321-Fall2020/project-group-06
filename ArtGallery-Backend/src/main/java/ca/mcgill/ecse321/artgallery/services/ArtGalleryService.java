package ca.mcgill.ecse321.artgallery.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.User;

/**
 * <p>
 * ArtGalleryService: methods used by artgallery such as browse/remove artworks,
 * commissions
 * </p>
 */
@Service
public class ArtGalleryService {

	@Autowired
	ArtworkRepository artworkRepository;
	@Autowired
	ArtGalleryRepository artGalleryRepository;

	/**
	 * REQ5.3: The art gallery system should be able to browse the artworks
	 * Implement the service method for this requirement
	 * 
	 * @return List of Artworks
	 * @author Sen Wang
	 */
	public ArrayList<Artwork> getAllArtworks() {

		// create new artwork array list
		ArrayList<Artwork> artworks = new ArrayList<Artwork>();

		// iterate through the artworks
		for (Artwork artwork : artworkRepository.findAll()) {
			artworks.add(artwork);
		}

		return artworks;
	}

	/**
	 * REQ 4.2 The art gallery system shall allow the art gallery to remove an
	 * artwork.
	 */
	public boolean removeArtwork(int artworkID) {
		Artwork artwork = artworkRepository.findArtworkById(artworkID);
		if(artwork==null) {
			return false;
		}
		artwork.setForSale(false);
		artworkRepository.save(artwork);
		return true;
	}

	public boolean saveArtGallery(ArtGallery artGallery) {
		// this Art Gallery already exists
		if (artGalleryRepository.findArtGalleryById(artGallery.getId()) != null) {
			return false;
		} else {
			artGalleryRepository.save(artGallery);
			return true;
		}
	}
}
