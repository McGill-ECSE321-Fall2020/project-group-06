package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    Artist findArtistById(Integer artistId);

}
