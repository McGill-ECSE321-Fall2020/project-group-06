package ca.mcgill.ecse321.artgallery.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;

import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;

import ca.mcgill.ecse321.artgallery.model.ArtGallery;

import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Transaction;

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
	@Autowired
	TransactionRepository transactionRepository;

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
	public Artwork removeArtwork(int artworkID) {
		Artwork artwork = artworkRepository.findArtworkById(artworkID);
		artwork.setForSale(false);
		artworkRepository.save(artwork);
		return artwork;
	}

	
	/**
	   * REQ4.3 The art gallery system shall allow the art gallery to take a commission on each transaction.
	   * 
	   * @param int The transaction ID
	   * @return double The art gallery<s commission on a sold piece of art
	   * @author Olivier Normandin
	   */
	  
	  @Transactional
	  public double takeCommission(int transactionID) {
		Transaction transaction = transactionRepository.findTransactionById(transactionID);
		if(transaction == null) {
			return 0;
		}
		else {
			double artGalleryCommission = transaction.getCommisionCut()*transaction.getArtwork().getPrice();
			return artGalleryCommission;
		}
	  }


	public ArtGallery saveArtGallery(ArtGallery artGallery){
		if (artGalleryRepository.findArtGalleryByName(artGallery.getName()) != null) {
            return null;
        } else {
            artGalleryRepository.save(artGallery);
            return artGalleryRepository.findArtGalleryByName(artGallery.getName());
        }
	}

	public ArtGallery getArtGalleryByName(String name) {
        if (artGalleryRepository.findArtGalleryByName(name) == null) {
            return null;
        } else {
            return artGalleryRepository.findArtGalleryByName(name);
        }
    }

}
