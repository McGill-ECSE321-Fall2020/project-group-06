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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * artworks for sale Http endpoint for this requirement
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

    /**REQ3.2: The art gallery system shall allow a customer to add artwork into their own favorite list.
     * Http endpoint for this requirement
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/addArtwork")
    public ResponseEntity<Void> addArtwork(@Valid @RequestBody Artwork artwork, @Valid @RequestBody Customer customer){
        logger.info("adding artwork");

        if (customer.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (customer.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (customer.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getArtGallery() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getArtist() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            customerService.addArtwork(artwork, customer);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Exception when adding artwork");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**REQ3.2: The art gallery system shall allow a customer to remove artwork into their own favorite list.
     * Http endpoint for this requirement
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/removeArtwork")
    public ResponseEntity<Void> removeArtwork(@Valid @RequestBody Artwork artwork, @Valid @RequestBody Customer customer){
        logger.info("removing artwork");

        if (customer.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (customer.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (customer.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getArtGallery() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getArtist() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            customerService.removeArtwork(artwork, customer);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Exception when removing artwork");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**REQ3.4: The art gallery system shall allow a customer to decide the mean of delivery of their artwork.
     * Http endpoint for this requirement
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/setMeanOfDelivery")
    public ResponseEntity<Void> setMeanOfDelivery(@Valid @RequestBody Transaction transaction, @Valid @RequestBody DeliveryType deliveryType){
        logger.info("setting mean of delivery");

        if (transaction.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transaction.getCustomer() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transaction.getArtist() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(transaction.getArtwork() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(transaction.getArtGallery() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            customerService.setMeanOfDelivery(transaction, deliveryType);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Exception when setting mean of delivery");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**REQ3.5: The art gallery system shall allow a customer to buy a chosen artwork.
     * Http endpoint for this requirement
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/buyArtwork")
    public ResponseEntity<Void> buyArtwork(@Valid @RequestBody Artwork artwork, @Valid @RequestBody double commissionCut, @Valid @RequestBody Customer customer, @Valid @RequestBody Date date, @Valid @RequestBody DeliveryType deliveryType, @Valid @RequestBody int id){
        logger.info("buying artwork");

        if (customer.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (customer.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (customer.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getArtGallery() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(artwork.getArtist() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(id == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            customerService.buyArtwork(artwork, commissionCut, customer, date, deliveryType, id);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Exception when buying artwork");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
