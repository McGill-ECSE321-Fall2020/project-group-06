package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.User;

/**
 * @author Sen Wang
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserById(int id);

}
