package ca.mcgill.ecse321.artgallery.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;

import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;

import ca.mcgill.ecse321.artgallery.dao.PictureRepository;
import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;

/**
 * <p>
 * CustomerService: Methods used by customer (browse, add/remove favorites,
 * transaction history etc)
 * </p>
 */
@Service
public class CustomerService {

    // Dependency Injections
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ArtGalleryRepository artGalleryRepository;

    /**
     * REQ3.1: The art gallery system shall allow a customer to browse all the
     * artworks for sale Implement the service method for this requirement
     * 
     * @return List of Artworks for sale
     * @author Sen Wang
     */

    public ArrayList<Artwork> getAllArtworksForSale() {

        // create new artwork for sale array list
        ArrayList<Artwork> artworksForSale = new ArrayList<Artwork>();

        // iterate through all artworks
        for (Artwork artwork : artworkRepository.findAll()) {
            if (artwork.isForSale()) {
                artworksForSale.add(artwork);
            }
        }

        return artworksForSale;
    }

    /**
     * REQ3.2: The art gallery system shall allow a customer to add artwork into
     * their own favorite list.
     * 
     * @author Noah Chamberland
     */
    public boolean addArtwork(Artwork artwork, Customer customer) {
        if (artworkRepository.findArtworkById(artwork.getId()) == null)
            return false;
        if (customerRepository.findCustomerById(customer.getId()) == null)
            return false;

        customer.getArtwork().add(artwork);
        customerRepository.save(customer);

        return true;
    }

    /**
     * REQ3.2: The art gallery system shall allow a customer to remove artwork into
     * their own favorite list.
     * 
     * @author Noah Chamberland
     */
    public boolean removeArtwork(Artwork artwork, Customer customer) {
        if (artworkRepository.findArtworkById(artwork.getId()) == null)
            return false;
        if (customerRepository.findCustomerById(customer.getId()) == null)
            return false;

        customer.getArtwork().remove(artwork);
        customerRepository.save(customer);

        return true;
    }

    /**
     * REQ3.4: The art gallery system shall allow a customer to decide the mean of
     * delivery of their artwork
     * 
     * @author Noah Chamberland
     */
    public boolean setMeanOfDelivery(Transaction transaction, DeliveryType deliveryType) {
        if (transactionRepository.findTransactionById(transaction.getId()) == null)
            return false;

        transaction.setDeliveryType(deliveryType);
        transactionRepository.save(transaction);

        return true;
    }

    /**
     * REQ3.5: The art gallery system shall allow a customer to buy a chosen artwork
     * 
     * @author Noah Chamberland
     */
    public boolean buyArtwork(int customerId, int artistId, int artworkId, int artGalleryId) {
        if (artGalleryRepository.findArtGalleryById(artGalleryId) == null) {
            return false;
        }
        if (artistRepository.findArtistById(artistId) == null) {
            return false;
        }
        if (artworkRepository.findArtworkById(artworkId) == null) {
            return false;
        }
        if (customerRepository.findCustomerById(customerId) == null) {
            return false;
        }
        Transaction transaction = new Transaction();
        transaction.setArtGallery(artGalleryRepository.findArtGalleryById(artGalleryId));
        transaction.setArtist(artistRepository.findArtistById(artistId));
        transaction.setArtwork(artworkRepository.findArtworkById(artworkId));
        transaction.setCustomer(customerRepository.findCustomerById(customerId));
        transactionRepository.save(transaction);

        // TODO SET ARTWORK FOR SALE = FALSE
        return true;
    }

    /**
     * Creates a new customer service method
     * 
     * @param customer
     * @return Boolean if the customer is created
     * @author Sen Wang
     */

    public Boolean saveCustomer(Customer customer) {
        // a user/customer/artist with username already exist
        if (userRepository.findUserByUsername(customer.getUsername()) != null) {
            return false;
        } else {
            customerRepository.save(customer);
            return true;
        }
    }

    /**
     * This methods finds a customer by username
     * 
     * @param username
     * @return Customer object
     */
    public Customer getCustomerByUsername(String username) {
        if (customerRepository.findCustomerByUsername(username) == null) {
            return null;
        } else {
            return customerRepository.findCustomerByUsername(username);
        }
    }

    /**
     * REQ3.3 The art gallery system shall provide the customer with a receipt of
     * the transaction.
     * 
     * @param int The transaction ID
     * @return Transaction The receipt
     * @author Olivier Normandin
     */

    @Transactional
    public Transaction getTransactionReceipt(int transactionID) {
        Transaction receipt = transactionRepository.findTransactionById(transactionID);
        if (receipt == null) {
            return null;
        } else {
            return receipt;
        }
    }

}
