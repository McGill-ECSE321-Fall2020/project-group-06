package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;
import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.model.Transaction;

/**
 * <p>
 * TransactionService: Methods used to crud transactions
 */
@Service
public class TransactionService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    ArtGalleryRepository artGalleryRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public boolean saveTransaction(int customerId, int artistId, int artworkId, int artGalleryId){
		if (artGalleryRepository.findArtGalleryById(artGalleryId) == null) {
            return false;
        }
        if(artistRepository.findArtistById(artistId) == null){
            return false;
        } 
        if(artworkRepository.findArtworkById(artworkId) == null){
            return false;
        }
        if(customerRepository.findCustomerById(customerId) == null){
            return false;
        }
        Transaction transaction = new Transaction();
        transaction.setArtGallery(artGalleryRepository.findArtGalleryById(artGalleryId));
        transaction.setArtist(artistRepository.findArtistById(artistId));
        transaction.setArtwork(artworkRepository.findArtworkById(artworkId));
        transaction.setCustomer(customerRepository.findCustomerById(customerId));
        //set for sale to false
        transactionRepository.save(transaction);
        return true;
	}

    public boolean removeTransaction(int transactionId){
        if(transactionRepository.findTransactionById(transactionId) == null){
            return false;
        }
        
        transactionRepository.delete(transactionRepository.findTransactionById(transactionId));

        return true;
    }

	public Transaction getTransactionById(int id) {
        if (transactionRepository.findTransactionById(id) == null) {
            return null;
        } else {
            return transactionRepository.findTransactionById(id);
        }
    }
}
