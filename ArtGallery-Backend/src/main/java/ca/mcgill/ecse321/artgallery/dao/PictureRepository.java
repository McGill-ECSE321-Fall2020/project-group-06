package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Picture;

/**
 * @author Sen Wang
 */
@Repository
public interface PictureRepository extends CrudRepository<Picture, Integer> {

    Picture findPictureById(Integer pictureId);

}
