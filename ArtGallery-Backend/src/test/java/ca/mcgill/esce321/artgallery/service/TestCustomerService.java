package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.services.CustomerService;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomerService customerService;

    private static final String CUSTOMER_KEY = "TestCustomer";
    private static final String NONEXISTING_KEY = "NotACustomer";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(customerRepository.findCustomerByUsername(anyString()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(CUSTOMER_KEY)) {
                        Customer customer = new Customer();
                        customer.setUsername(CUSTOMER_KEY);
                        return customer;
                    } else {
                        return null;
                    }
                });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateCustomer() {
        String name = "hello how are you im under the water";
        Customer customer = new Customer();
        customer.setUsername(name);
        boolean yo = true;
        try {
            yo = customerService.saveCustomer(customer);
        } catch (IllegalArgumentException e) {
            // Check that no error occured
            fail();
        }

        assertEquals(true, yo);
    }

    // test public ArrayList<Artwork> getAllArtworksForSale()

    // test public boolean addArtwork(int customerId, int artworkId)

    // test public boolean removeArtwork(int customerId, int artworkId)

    // test public boolean setMeanOfDelivery(int transactionId, DeliveryType
    // deliveryType)

    // public boolean buyArtwork(int customerId, int artistId, int artworkId, int
    // artGalleryId)

    // public Boolean saveCustomer(Customer customer)

    // public Customer getCustomerByUsername(String username)

    // public Transaction getTransactionReceipt(int transactionID)

    // public Boolean updateCustomer(CustomerDto customerDto)

    // TODO ADD MORE TESTS IF NEEDED
}
