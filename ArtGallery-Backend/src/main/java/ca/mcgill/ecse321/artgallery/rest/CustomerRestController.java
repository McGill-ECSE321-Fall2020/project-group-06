package ca.mcgill.ecse321.artgallery.rest;

import java.sql.Date;
import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.dto.ArtworkCustomerDto;
import ca.mcgill.ecse321.artgallery.dto.BuyArtworkDto;
import ca.mcgill.ecse321.artgallery.dto.TransactionDeliveryTypeDto;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;

import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;

import ca.mcgill.ecse321.artgallery.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class);

    @Autowired
    CustomerService customerService;

    /**
     * REQ3.1: The art gallery system shall allow a customer to browse all the
     * artworks for sale Http endpoint for this requirement. TESTED WITH POSTMAN
     * 
     * @return List of Artworks
     * @author Sen Wang
     */

    @GetMapping("/artworksForSale")
    public ResponseEntity<ArrayList<Artwork>> getAllArtworks() {
        try {
            return ResponseEntity.ok(customerService.getAllArtworksForSale());
        } catch (Exception e) {
            logger.error("Exception when getting artworks for sale for customer");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * REQ3.2: The art gallery system shall allow a customer to add artwork into
     * their own favorite list. Http endpoint for this requirement. TESTED WITH POSTMAN
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/addArtwork/{customerId}/{artworkId}")
    public ResponseEntity<Void> addArtwork(@PathVariable("customerId") int customerId, @PathVariable("artworkId") int artworkId) {
        logger.info("adding artwork");

        if (customerId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            if (customerService.addArtwork(customerId, artworkId))
                return ResponseEntity.status(HttpStatus.OK).build();
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Exception when adding artwork");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * REQ3.2: The art gallery system shall allow a customer to remove artwork into
     * their own favorite list. Http endpoint for this requirement. TESTED WITH POSTMAN
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/removeArtwork/{customerId}/{artworkId}")
    public ResponseEntity<Void> removeArtwork(@PathVariable("customerId") int customerId, @PathVariable("artworkId") int artworkId) {
        logger.info("removing artwork");

        if (customerId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            if (customerService.removeArtwork(customerId, artworkId))
                return ResponseEntity.status(HttpStatus.OK).build();
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Exception when removing artwork");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * REQ3.4: The art gallery system shall allow a customer to decide the mean of
     * delivery of their artwork. Http endpoint for this requirement. TESTED WITH POSTMAN
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/setMeanOfDelivery/{transactionId}")
    public ResponseEntity<Void> setMeanOfDelivery(@PathVariable("transactionId") int transactionId, @Valid @RequestBody DeliveryType deliveryType) {
        logger.info("setting mean of delivery");

        if (transactionId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (deliveryType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            if (customerService.setMeanOfDelivery(transactionId, deliveryType))
                return ResponseEntity.status(HttpStatus.OK).build();
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Exception when setting mean of delivery");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * REQ3.5: The art gallery system shall allow a customer to buy a chosen
     * artwork. Http endpoint for this requirement. TESTED WITH POSTMAN
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/buyArtwork/{customerId}/{artistId}/{artworkId}/{artGalleryId}")
    public ResponseEntity<Void> buyArtwork(@PathVariable("customerId") int customerId, @PathVariable("artistId") int artistId, @PathVariable("artworkId") int artworkId, @PathVariable("artGalleryId") int artGalleryId) {
        logger.info("buying artwork");

		if(customerId == 0){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artistId == 0){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }		
        if(artworkId == 0){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }		
        if(artGalleryId == 0){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		try {
            if (customerService.buyArtwork(customerId, artistId, artworkId, artGalleryId) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            logger.error("Exception when buying artwork " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
  
    /**
     * TESTED WITH POSTMAN
     */
  @PostMapping("/createCustomer")
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer customer) {

        logger.info("creating customer profile");

        if (customer.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (customer.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (customerService.saveCustomer(customer) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            logger.error("Exception when creating a new customer " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Http endpoint to get customer by username
     * 
     * @param username
     * @return Customer object
     */
    @GetMapping("/getCustomer/{username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username) {

        logger.info("get customer by username");

        if (username == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (customerService.getCustomerByUsername(username) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.ok(customerService.getCustomerByUsername(username));
            }
        } catch (Exception e) {
            logger.error("Exception when getting customer by username" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
