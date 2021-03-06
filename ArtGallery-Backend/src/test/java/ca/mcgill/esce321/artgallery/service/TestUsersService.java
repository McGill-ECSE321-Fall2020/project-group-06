package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.services.UsersService;

@ExtendWith(MockitoExtension.class)
public class TestUsersService {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersService usersService;

    private static final int UserId = 3;
    private static final int NonExistingUserId = 5;
    
    private static final String UserUsername = "my User username";
    private static final String NonExistant_UserUsername = "not my User username";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(userRepository.findUserByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(UserUsername)) {
                User user = new User();
                user.setUsername(UserUsername);
                return user;
            } else {
                return null;
            }
        });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(userRepository.save(any(User.class))).thenAnswer(returnParameterAsAnswer);

        // user invocation on mock
        lenient().when(userRepository.findUserById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(UserId)) {
                User user = new User();
                user.setId(UserId);
                return user;
            } else {
                return null;
            }
        });
    }

    // test public Boolean saveUser(User user)
    @Test
    public void saveUser() {
        String username = NonExistant_UserUsername;
        User user = new User();
        user.setUsername(username);
        Boolean created = false;
        try {
            created = usersService.saveUser(user);
        } catch (Exception e) {
            fail();
        }

        assertEquals(true, created);
    }
    
    // test public Boolean saveUser(User user)
    @Test
    public void saveUserWhenExistingUser() {
        String username = UserUsername;
        User user = new User();
        user.setUsername(username);
        Boolean created = false;
        try {
            created = usersService.saveUser(user);
        } catch (Exception e) {
            fail();
        }

        assertEquals(false, created);
    }

    // public Boolean updateUser(User user)
    @Test
    public void testUpdateUser() {
        // create a user with name TestUser;
        User user = new User();
        user.setUsername(UserUsername);
        // save it to mock database
        usersService.saveUser(user);
        Boolean updated = false;
        try {
            user.setLastName("lastName");
            updated = usersService.updateUser(user);
        } catch (Exception e) {
            fail();
        }
        assertEquals(true, updated);
    }
    
    // public Boolean updateUser(User user)
    @Test
    public void testUpdateUserWhenNonExistingUser() {
        // create a user with name TestUser;
        User user = new User();
        user.setUsername(NonExistant_UserUsername);
        // save it to mock database
        usersService.saveUser(user);
        Boolean updated = false;
        try {
            user.setLastName("lastName");
            updated = usersService.updateUser(user);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, updated);
    }

    @Test
    public void testGetUserById() {
        User existingUser = new User();
        try {
            existingUser = usersService.getUserById(UserId);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(existingUser);
        assertEquals(UserId, existingUser.getId());
    }
    
    @Test
    public void testGetUserByNonExistingId() {
        User existingUser = new User();
        try {
            existingUser = usersService.getUserById(NonExistingUserId);
        } catch (Exception e) {
            fail();
        }

        assertNull(existingUser);
    }

    @Test
    public void testDeleteUserById() {
        Boolean userDeleted = false;
        try {
            userDeleted = usersService.deleteUserById(UserId);
        } catch (Exception e) {
            fail();
        }

        assertEquals(true, userDeleted);
    }
    
    @Test
    public void testDeleteUserByNonExistingId() {
        Boolean userDeleted = false;
        try {
            userDeleted = usersService.deleteUserById(NonExistingUserId);
        } catch (Exception e) {
            fail();
        }

        assertEquals(false, userDeleted);
    }

}
