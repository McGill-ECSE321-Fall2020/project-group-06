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

    /**
     * REQ3.2: The art gallery system shall allow a customer to add artwork into
     * their own favorite list. Http endpoint for this requirement
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/addArtwork")
    public ResponseEntity<Void> addArtwork(@Valid @RequestBody ArtworkCustomerDto artworkCustomer) {
        logger.info("adding artwork");

        if (artworkCustomer.customer.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkCustomer.customer.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkCustomer.artwork.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkCustomer.artwork.getArtGallery() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkCustomer.artwork.getArtist() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            if (customerService.addArtwork(artworkCustomer.artwork, artworkCustomer.customer))
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
     * their own favorite list. Http endpoint for this requirement
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/removeArtwork")
    public ResponseEntity<Void> removeArtwork(@Valid @RequestBody ArtworkCustomerDto artworkCustomer) {
        logger.info("removing artwork");

        if (artworkCustomer.customer.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkCustomer.customer.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkCustomer.artwork.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkCustomer.artwork.getArtGallery() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkCustomer.artwork.getArtist() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            if (customerService.removeArtwork(artworkCustomer.artwork, artworkCustomer.customer))
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
     * delivery of their artwork. Http endpoint for this requirement
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/setMeanOfDelivery")
    public ResponseEntity<Void> setMeanOfDelivery(@Valid @RequestBody TransactionDeliveryTypeDto transactionDeliveryType) {
        logger.info("setting mean of delivery");

        if (transactionDeliveryType.transaction.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transactionDeliveryType.transaction.getCustomer() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transactionDeliveryType.transaction.getArtist() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transactionDeliveryType.transaction.getArtwork() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transactionDeliveryType.transaction.getArtGallery() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            if (customerService.setMeanOfDelivery(transactionDeliveryType.transaction, transactionDeliveryType.deliveryType))
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
     * artwork. Http endpoint for this requirement
     * 
     * @author Noah Chamberland
     */
    @PostMapping("/buyArtwork")
    public ResponseEntity<Void> buyArtwork(@Valid @RequestBody BuyArtworkDto buyArtworkDto) {
        logger.info("buying artwork");

        if (buyArtworkDto.customer.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (buyArtworkDto.customer.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (buyArtworkDto.customer.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (buyArtworkDto.artwork.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (buyArtworkDto.artwork.getArtGallery() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (buyArtworkDto.artwork.getArtist() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            if (customerService.buyArtwork(buyArtworkDto.artwork, buyArtworkDto.commissionCut, buyArtworkDto.customer, buyArtworkDto.date, buyArtworkDto.deliveryType))
                return ResponseEntity.status(HttpStatus.CREATED).build();
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Exception when buying artwork");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
