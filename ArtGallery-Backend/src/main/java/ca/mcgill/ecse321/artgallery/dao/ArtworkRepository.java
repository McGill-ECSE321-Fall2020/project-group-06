package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Artwork;

/**
 * @author Sen Wang
 */
@Repository
public interface ArtworkRepository extends CrudRepository<Artwork, Integer> {

    Artwork findArtworkById(Integer artWorkId);

}
