package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.artgallery.model.User;

public interface UserRepository extends CrudRepository <User, String>{
	
//	User findUserByUsername(String username);

}
