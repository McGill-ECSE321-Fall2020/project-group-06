package ca.mcgill.ecse321.artgallery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.dao.CustomerRepository;
import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.dto.TransactionDto;
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

    /**
     * Save transaction
     * 
     * @param customerId
     * @param artistId
     * @param artworkId
     * @param artGalleryId
     * @return boolean
     */
    @Transactional
    public boolean saveTransaction(int customerId, int artistId, int artworkId, int artGalleryId) {
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
        // set for sale to false
        transactionRepository.save(transaction);
        return true;
    }

    /**
     * Updates transaction
     * 
     * @param transaction
     * @return boolean
     */
    @Transactional
    public boolean updateTransaction(TransactionDto transactionDto) {
        if (transactionRepository.findTransactionById(transactionDto.getId()) == null) {
            return false;
        }
        if (artGalleryRepository.findArtGalleryById(transactionDto.getArtGalleryId()) == null) {
            return false;
        }
        if (artistRepository.findArtistById(transactionDto.getArtistId()) == null) {
            return false;
        }
        if (artworkRepository.findArtworkById(transactionDto.getArtworkId()) == null) {
            return false;
        }
        if (customerRepository.findCustomerById(transactionDto.getCustomerId()) == null) {
            return false;
        }
        Transaction newTransaction = transactionRepository.findTransactionById(transactionDto.getId());
        newTransaction.setArtGallery(artGalleryRepository.findArtGalleryById(transactionDto.getArtGalleryId()));
        newTransaction.setArtist(artistRepository.findArtistById(transactionDto.getArtistId()));
        newTransaction.setArtwork(artworkRepository.findArtworkById(transactionDto.getArtworkId()));
        newTransaction.setCommisionCut(transactionDto.getCommisionCut());
        newTransaction.setCustomer(customerRepository.findCustomerById(transactionDto.getCustomerId()));
        newTransaction.setDateOfTransaction(transactionDto.getDateOfTransaction());
        newTransaction.setDeliveryType(transactionDto.getDeliveryType());
        transactionRepository.save(newTransaction);
        return true;
    }

    /**
     * Remove transaction
     * 
     * @param transactionId
     * @return
     */
    @Transactional
    public boolean removeTransaction(int transactionId) {
        if (transactionRepository.findTransactionById(transactionId) == null) {
            return false;
        }

        transactionRepository.delete(transactionRepository.findTransactionById(transactionId));

        return true;
    }

    /**
     * Get transaction by id
     * 
     * @param id
     * @return Transaction
     */
    @Transactional
    public Transaction getTransactionById(int id) {
        if (transactionRepository.findTransactionById(id) == null) {
            return null;
        } else {
            return transactionRepository.findTransactionById(id);
        }
    }
}
