package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Transaction;

/**
 * @author Sen Wang
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    Transaction findTransactionById(Integer transactionId);

}
