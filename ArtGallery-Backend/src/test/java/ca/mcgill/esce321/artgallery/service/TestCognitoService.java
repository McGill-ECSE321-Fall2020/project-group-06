package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
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
import ca.mcgill.ecse321.artgallery.services.CognitoService;

@ExtendWith(MockitoExtension.class)
public class TestCognitoService {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CognitoService cognitoService;

    private static final String USER_KEY = "TestUser";
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
    }

    // test public Boolean changePassword(String username, String newPassword)
    @Test
    public void testChangePassword() {
        Boolean changedPassword = false;
        try {
            changedPassword = cognitoService.changePassword(USER_KEY, "newPassword");
        } catch (Exception e) {
            fail();
        }

        assertEquals(true, changedPassword);
    }

    // test public Boolean changePassword(String username, String newPassword) : non
    // existant user
    @Test
    public void testChangePasswordOnNonExistantUser() {
        Boolean changedPassword = false;
        try {
            changedPassword = cognitoService.changePassword(NONEXISTING_KEY, "newPassword");
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, changedPassword);

    }
}
