package ca.mcgill.ecse321.artgallery.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRepository;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;

/**
 * <p>
 * CustomerService: Methods used by customer (browse, add/remove favorites,
 * transaction history etc)
 * </p>
 */
@Service
public class CustomerService {

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

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

    // Update customer infos method

}
