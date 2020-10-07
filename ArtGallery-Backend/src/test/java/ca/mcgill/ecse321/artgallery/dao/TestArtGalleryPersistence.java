package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.artgallery.model.User;


@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArtGalleryPersistence {
	
	// TESTS TODO
	// Here is a test example
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@AfterEach
	public void clearDatabase() {
		artistRepository.deleteAll();
	}	
	
	@Test
	public void testPersistenceAndLoadUser() {
		User user = new User();
		user.setUsername("chaggy");
		user.setId(1);
		if (userRepository == null) {
			System.out.println("UserRepo is null");
		}
		userRepository.save(user);
		user = null;
		user = userRepository.findUserByUsername("chaggy");
		assertNotNull(user);
		assertEquals("chaggy", user.getUsername());
	}

}
