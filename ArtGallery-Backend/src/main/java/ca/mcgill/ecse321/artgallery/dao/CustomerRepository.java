package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Customer;

/**
 * @author Sen Wang
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findCustomerById(Integer cutomerId);

    // find customer by its username
    Customer findCustomerByUsername(String username);

}
