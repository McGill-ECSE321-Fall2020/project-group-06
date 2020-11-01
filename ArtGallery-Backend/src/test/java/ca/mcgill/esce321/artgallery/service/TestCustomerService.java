package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;
import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.dto.CustomerDto;
import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.Picture;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;
import ca.mcgill.ecse321.artgallery.services.ArtGalleryService;
import ca.mcgill.ecse321.artgallery.services.ArtistService;
import ca.mcgill.ecse321.artgallery.services.ArtworkService;
import ca.mcgill.ecse321.artgallery.services.CustomerService;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ArtworkRepository artworkRepository;

    @Mock
    private ArtGalleryRepository artGalleryRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private CustomerService customerService;

    @InjectMocks
    private ArtGalleryService artGalleryService;

    @InjectMocks
    private ArtistService artistService;

    @InjectMocks
    private ArtworkService artworkService;

    private static final String CUSTOMER_KEY = "TestCustomer";
    private static final String NOT_A_CUSTOMER_KEY = "NotACustomer";
    private static final String USER_KEY = "TestUser";

    private static final int testArtGalleryId = 1;
    private static final int testNonExistantArtGalleryId = 11;
    private static final int testArtistId = 2;
    private static final int testNonExisantArtistId = 10;
    private static final int testArtworkId = 3;
    private static final int testNonExistantArtworkId = 8;
    private static final int testCustomerId = 4;
    private static final int testNonExistantCustomerId = 7;
    private static final int testTransactionId = 5;
    private static final int testNonExisantTransactionId = 9;
    private static final int testUserId = 6;

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

        lenient().when(artworkRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            ArrayList<Artwork> array = new ArrayList<Artwork>();
            Artwork artwork = new Artwork();
            artwork.setId(testArtworkId);
            artwork.setForSale(true);
            array.add(artwork);
            return array;
        });

        // art gallery invocation on mock
        lenient().when(artGalleryRepository.findArtGalleryById(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(testArtGalleryId)) {
                ArtGallery artGallery = new ArtGallery();
                artGallery.setId(testArtGalleryId);
                return artGallery;
            } else {
                return null;
            }
        });

        // artist invocation on mock
        lenient().when(artistRepository.findArtistById(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(testArtistId)) {
                Artist artist = new Artist();
                artist.setId(testArtistId);
                return artist;
            } else {
                return null;
            }
        });

        // customer invocation on mock
        lenient().when(customerRepository.findCustomerById(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(testCustomerId)) {
                Customer customer = new Customer();
                customer.setId(testCustomerId);
                customer.setArtwork(new HashSet<Artwork>());
                return customer;
            } else {
                return null;
            }
        });

        // customer invocation on mock
        lenient().when(customerRepository.findCustomerByUsername(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals("customer")) {
                Customer customer = new Customer();
                customer.setId(testCustomerId);
                customer.setArtwork(new HashSet<Artwork>());
                customer.setUsername("customer");
                return customer;
            } else {
                return null;
            }
        });

        // artwork invocation on mock
        lenient().when(artworkRepository.findArtworkById(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(testArtworkId)) {
                Artwork artwork = new Artwork();
                artwork.setId(testArtworkId);
                return artwork;
            } else {
                return null;
            }
        });

        // transaction invocation on mock
        lenient().when(transactionRepository.findTransactionById(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(testTransactionId)) {
                Transaction transaction = new Transaction();
                transaction.setId(testTransactionId);
                return transaction;
            } else {
                return null;
            }
        });

        // user invocation on mock
        lenient().when(userRepository.findUserByUsername(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USER_KEY)) {
                User user = new User();
                user.setId(testUserId);
                user.setUsername(USER_KEY);
                return user;
            } else {
                return null;
            }
        });
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

    @Test
    public void testCreateExistingCustomer() {
        boolean saveWorked = false;
        Customer customer = new Customer();
        customer.setUsername(USER_KEY);
        try {
            saveWorked = customerService.saveCustomer(customer);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, saveWorked);
    }

    @Test
    public void testGetAllArtworksForSale() {
        ArrayList<Artwork> array = new ArrayList<Artwork>();
        try {
            array = customerService.getAllArtworksForSale();
        } catch (Exception e) {
            fail();
        }
        assertEquals(1, array.size());
    }

    @Test
    public void testAddArtwork() {
        boolean valid = false;
        try {
            valid = customerService.addArtwork(testCustomerId, testArtworkId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(true, valid);
    }

    @Test
    public void testAddArtworkWithNonExistantCustomerId() {
        boolean valid = false;
        try {
            valid = customerService.addArtwork(testNonExistantCustomerId, testArtworkId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testAddArtworkWithNonExistantArtworkId() {
        boolean valid = false;
        try {
            valid = customerService.addArtwork(testCustomerId, testNonExistantArtworkId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testRemoveArtwork() {
        boolean valid = false;
        try {
            valid = customerService.removeArtwork(testCustomerId, testArtworkId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(true, valid);
    }

    @Test
    public void testRemoveArtworkWithNonExistantCustomerId() {
        boolean valid = false;
        try {
            valid = customerService.removeArtwork(testNonExistantCustomerId, testArtworkId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testRemoveArtworkWithNonExistantArtworkId() {
        boolean valid = false;
        try {
            valid = customerService.removeArtwork(testCustomerId, testNonExistantArtworkId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testSetMeanOfDelivery() {
        boolean valid = false;
        DeliveryType deliveryType = DeliveryType.Delivered;
        try {
            valid = customerService.setMeanOfDelivery(testTransactionId, deliveryType);
        } catch (Exception e) {
            fail();
        }
        assertEquals(true, valid);
    }

    @Test
    public void testSetMeanOfDeliveryWithNonExistantTransactionId() {
        boolean valid = false;
        DeliveryType deliveryType = DeliveryType.Delivered;
        try {
            valid = customerService.setMeanOfDelivery(testNonExisantTransactionId, deliveryType);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testBuyArtwork() {
        boolean valid = false;
        try {
            valid = customerService.buyArtwork(testCustomerId, testArtistId, testArtworkId, testArtGalleryId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(true, valid);
    }

    @Test
    public void testBuyArtworkWithNonExistantCustomerId() {
        boolean valid = false;
        try {
            valid = customerService.buyArtwork(testNonExistantCustomerId, testArtistId, testArtworkId,
                    testArtGalleryId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);

    }

    @Test
    public void testBuyArtworkWithNonExistantArtistId() {
        boolean valid = false;
        try {
            valid = customerService.buyArtwork(testCustomerId, testNonExisantArtistId, testArtworkId, testArtGalleryId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testBuyArtworkWithNonExistantArtworkId() {
        boolean valid = false;
        try {
            valid = customerService.buyArtwork(testCustomerId, testArtistId, testNonExistantArtworkId,
                    testArtGalleryId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testBuyArtworkWithNonExistantArtGalleryId() {
        boolean valid = false;
        try {
            valid = customerService.buyArtwork(testCustomerId, testArtistId, testArtworkId,
                    testNonExistantArtGalleryId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testSaveCustomer() {
        boolean valid = false;
        Customer customer = new Customer();
        customer.setUsername("customer");
        try {
            valid = customerService.saveCustomer(customer);
        } catch (Exception e) {
            fail();
        }
        assertEquals(true, valid);
    }

    @Test
    public void testSaveExistingCustomer() {
        boolean valid = false;
        Customer customer = new Customer();
        customer.setUsername(USER_KEY);
        try {
            valid = customerService.saveCustomer(customer);
        } catch (Exception e) {
            fail();
        }
        assertEquals(false, valid);
    }

    @Test
    public void testGetCustomerByUsername() {
        Customer customer = new Customer();
        try {
            customer = customerService.getCustomerByUsername("customer");
        } catch (Exception e) {
            fail();
        }
        assertEquals("customer", customer.getUsername());
    }

    @Test
    public void testGetNonExistantCustomerByUsername() {
        Customer customer = new Customer();
        try {
            customer = customerService.getCustomerByUsername(NOT_A_CUSTOMER_KEY);
        } catch (Exception e) {
            fail();
        }
        assertNull(customer);
    }

    @Test
    public void testGetTransactionReceipt() {
        Transaction transaction = new Transaction();
        try {
            transaction = customerService.getTransactionReceipt(testTransactionId);
        } catch (Exception e) {
            fail();
        }
        assertEquals(testTransactionId, transaction.getId());
    }

    @Test
    public void testGetNonExistantTransactionReceipt() {
        Transaction transaction = new Transaction();
        try {
            transaction = customerService.getTransactionReceipt(testNonExisantTransactionId);
        } catch (Exception e) {
            fail();
        }
        assertNull(transaction);
    }

    @Test
    public void testUpdateCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setArtwork(new HashSet<Artwork>());
        customerDto.setCreditCardNumber(1111222233334444L);
        customerDto.setDescription("description");
        customerDto.setEmail("email");
        customerDto.setFirstName("firstName");
        customerDto.setLastName("lastName");
        customerDto.setPhoneNumber("phoneNumber");
        customerDto.setPicture(new Picture());
        customerDto.setTransaction(new HashSet<Transaction>());
        customerDto.setUsername("customer");

        boolean valid = false;
        try {
            valid = customerService.updateCustomer(customerDto);
        } catch (Exception e) {
            fail();
        }

        assertEquals(true, valid);
    }

    @Test
    public void testUpdateNonExistantCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setArtwork(new HashSet<Artwork>());
        customerDto.setCreditCardNumber(1111222233334444L);
        customerDto.setDescription("description");
        customerDto.setEmail("email");
        customerDto.setFirstName("firstName");
        customerDto.setLastName("lastName");
        customerDto.setPhoneNumber("phoneNumber");
        customerDto.setPicture(new Picture());
        customerDto.setTransaction(new HashSet<Transaction>());
        customerDto.setUsername(NOT_A_CUSTOMER_KEY);

        boolean valid = false;
        try {
            valid = customerService.updateCustomer(customerDto);
        } catch (Exception e) {
            fail();
        }

        assertEquals(false, valid);
    }

}
