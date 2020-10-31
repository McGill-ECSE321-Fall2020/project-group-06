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
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.services.UsersService;

@ExtendWith(MockitoExtension.class)
public class TestUsersService {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersService usersService;

    private static final String USER_KEY = "TestUser";
    private static final int testUserId = 3;
    private static final String NONEXISTING_KEY = "NotAUser";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(userRepository.findUserByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USER_KEY)) {
                User user = new User();
                user.setUsername(USER_KEY);
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
            if (invocation.getArgument(0).equals(testUserId)) {
                User user = new User();
                user.setId(testUserId);
                return user;
            } else {
                return null;
            }
        });
    }

    // test public Boolean saveUser(User user)
    @Test
    public void testCreateUser() {
        String username = NONEXISTING_KEY;
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

    // public Boolean updateUser(User user) TODO fix for tests
    @Test
    public void testUpdateUser() {
        // create a user with name TestUser;
        User user = new User();
        user.setUsername(USER_KEY);
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

    @Test
    public void testGetUserById() {
        User existingUser = new User();
        try {
            existingUser = usersService.getUserById(testUserId);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(existingUser);
        assertEquals(testUserId, existingUser.getId());
    }

    @Test
    public void testDeleteUserById() {
        Boolean userDeleted = false;
        try {
            userDeleted = usersService.deleteUserById(testUserId);
        } catch (Exception e) {
            fail();
        }

        assertEquals(true, userDeleted);
    }

    // ADD MORE TESTS

}
