package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.ArtGallery;

/**
 * @author Sen Wang
 */
@Repository
public interface ArtGalleryRepository extends CrudRepository<ArtGallery, Integer> {

    ArtGallery findArtGalleryById(Integer artGalleryId);

    ArtGallery findArtGalleryByName(String name);

}
