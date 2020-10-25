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

    /**REQ3.2: The art gallery system shall allow a customer to add artwork into their own favorite list.
     * 
     * @author Noah Chamberland
     */
    public boolean addArtwork(Artwork artwork, Customer customer){
        if(artworkRepository.findArtworkById(artwork.getId()) == null)
            return false;
        if(customerRepository.findCustomerById(customer.getId()) == null)
            return false;

        customer.getArtwork().add(artwork);
        customerRepository.save(customer);

        return true;
    }

    /**REQ3.2: The art gallery system shall allow a customer to remove artwork into their own favorite list.
     * 
     * @author Noah Chamberland
     */
    public boolean removeArtwork(Artwork artwork, Customer customer){
        if(artworkRepository.findArtworkById(artwork.getId()) == null)
            return false;
        if(customerRepository.findCustomerById(customer.getId()) == null)
            return false;

        customer.getArtwork().remove(artwork);
        customerRepository.save(customer);

        return true;
    }

    /**REQ3.4: The art gallery system shall allow a customer to decide the mean of delivery of their artwork
     * 
     * @author Noah Chamberland
    */
    public boolean setMeanOfDelivery(Transaction transaction, DeliveryType deliveryType){
        if(transactionRepository.findTransactionById(transaction.getId()) == null)
            return false;

        transaction.setDeliveryType(deliveryType);
        transactionRepository.save(transaction);

        return true;
    }
    
    /**REQ3.5: The art gallery system shall allow a customer to buy a chosen artwork
     * 
     * @author Noah Chamberland
     */
    public boolean buyArtwork(Artwork artwork, double commissionCut, Customer customer, Date date, DeliveryType deliveryType){
        if(artworkRepository.findArtworkById(artwork.getId()) == null)
            return false;
        if(customerRepository.findCustomerById(customer.getId()) == null)
            return false;
        
        Transaction transaction = new Transaction();

        transaction.setArtGallery(artwork.getArtGallery());
        artwork.getArtGallery().getTransaction().add(transaction);
        artGalleryRepository.save(artwork.getArtGallery());

        transaction.setArtist(artwork.getArtist());
        artwork.getArtist().getTransaction().add(transaction);
        artistRepository.save(artwork.getArtist());

        transaction.setArtwork(artwork);
        artwork.getTransaction().add(transaction);
        artworkRepository.save(artwork);

        transaction.setCommisionCut(commissionCut);

        transaction.setCustomer(customer);
        customer.getTransaction().add(transaction);
        customerRepository.save(customer);

        transaction.setDateOfTransaction(date);

        transaction.setDeliveryType(deliveryType);

        transactionRepository.save(transaction);

        return true;
    }
    
    /**
     * REQ3.3 The art gallery system shall provide the customer with a receipt of the transaction.
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
  	}
  	else {
  		return receipt;
  		}
    }
    
}
