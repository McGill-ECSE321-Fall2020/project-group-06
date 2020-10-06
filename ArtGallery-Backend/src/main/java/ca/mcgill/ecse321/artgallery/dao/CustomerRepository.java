package ca.mcgill.ecse321.artgallery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findCustomerById(Integer cutomerId);

}
