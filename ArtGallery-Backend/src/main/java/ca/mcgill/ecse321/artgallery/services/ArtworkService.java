package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
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

	public Artwork saveArtwork(Artwork artwork){
        Artwork newArtwork = new Artwork();
		if (artworkRepository.findArtworkByName(artwork.getName()) != null) {
            return null;
        }
        if(artistRepository.findArtistByUsername(artwork.getArtist().getUsername()) == null){
            return null;
        }
        if(artGalleryRepository.findArtGalleryByName(artwork.getArtGallery().getName()) == null){
            return null;
        }
        newArtwork.setName(artwork.getName());
        newArtwork.setArtist(artistRepository.findArtistByUsername(artwork.getArtist().getUsername()));
        newArtwork.setArtGallery(artGalleryRepository.findArtGalleryByName(artwork.getArtGallery().getName()));
        artworkRepository.save(newArtwork);
        return artworkRepository.findArtworkByName(newArtwork.getName());
	}

	public Artwork getArtworkByName(String name) {
        if (artworkRepository.findArtworkByName(name) == null) {
            return null;
        } else {
            return artworkRepository.findArtworkByName(name);
        }
    }
}
