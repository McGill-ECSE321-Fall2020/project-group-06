package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;

/**
 * <p>
 * Artwork services: methods used to crud artworks
 * </p>
 */
@Service
public class ArtworkService {

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ArtGalleryRepository artGalleryRepository;

    /**
     * Save artwork
     * 
     * @param artwork
     * @return Artwork
     */
    @Transactional
    public Artwork saveArtwork(Artwork artwork) {
        Artwork newArtwork = new Artwork();
        if (artworkRepository.findArtworkByName(artwork.getName()) != null) {
            return null;
        }
        if (artistRepository.findArtistByUsername(artwork.getArtist().getUsername()) == null) {
            return null;
        }
        if (artGalleryRepository.findArtGalleryByName(artwork.getArtGallery().getName()) == null) {
            return null;
        }
        newArtwork.setName(artwork.getName());
        newArtwork.setArtist(artistRepository.findArtistByUsername(artwork.getArtist().getUsername()));
        newArtwork.setArtGallery(artGalleryRepository.findArtGalleryByName(artwork.getArtGallery().getName()));
        artworkRepository.save(newArtwork);
        return newArtwork;
    }

    /**
     * update artwork
     * 
     * @param artwork
     * @return boolean
     */
    @Transactional
    public boolean updateArtwork(Artwork artwork) {
        Artwork newArtwork = new Artwork();
        Artist artist = artistRepository.findArtistById(artwork.getArtist().getId());
        ArtGallery artGallery = artGalleryRepository.findArtGalleryById(artwork.getArtGallery().getId());
        if (artworkRepository.findArtworkById(artwork.getId()) == null) {
            return false;
        }
        if (artist == null) {
            return false;
        }
        if (artGallery == null) {
            return false;
        }
        newArtwork = artworkRepository.findArtworkById(artwork.getId());
        newArtwork.setArtGallery(artGallery);
        newArtwork.setArtist(artist);
        newArtwork.setDescription(artwork.getDescription());
        newArtwork.setForSale(artwork.isForSale());
        newArtwork.setIsInStore(artwork.isIsInStore());
        newArtwork.setName(artwork.getName());
        newArtwork.setPicture(artwork.getPicture());
        newArtwork.setPrice(artwork.getPrice());
        newArtwork.setTransaction(artwork.getTransaction());
        newArtwork.setTypeOfArtwork(artwork.getTypeOfArtwork());
        artworkRepository.save(newArtwork);
        return true;
    }

    /**
     * Get artwork by name
     * 
     * @param name
     * @return Artwork
     */
    @Transactional
    public Artwork getArtworkByName(String name) {
        if (artworkRepository.findArtworkByName(name) == null) {
            return null;
        } else {
            return artworkRepository.findArtworkByName(name);
        }
    }
	/**
	 * Delete artwork by id
	 * 
	 * @param artwork ID
	 * @return boolean
	 */
	@Transactional
	public Boolean deleteArtworkById(int artworkId) {
		if (artworkRepository.findArtworkById(artworkId) == null) {
			return false;
		} else {
			artworkRepository.deleteById(artworkId);
			return true;
		}
	}
}
