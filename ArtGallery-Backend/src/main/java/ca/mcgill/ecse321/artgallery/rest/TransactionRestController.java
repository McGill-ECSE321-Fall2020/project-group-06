package ca.mcgill.ecse321.artgallery.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.dto.TransactionDto;

import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.services.TransactionService;

/**
 * Art Gallery REST controller class
 * @author Sen Wang
 * @author Noah Chamberland
 * @author Justin Legrand
 * @author Olivier Normandin
 * @author Andre-Walter Panzini
 */

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRestController.class);

    @Autowired
    TransactionService transactionService;

    /**
     * It shall be possible to create a transaction.
     * 
     * @param int customerId The customer's ID
     * @param int artistId The artist's ID
     * @param int artworkId The artwork's ID
     * @param int artGalleryId The art gallery's ID
     */
    @PostMapping("/createTransaction/{customerId}/{artistId}/{artworkId}/{artGalleryId}")
    public ResponseEntity<Void> createTransaction(@PathVariable("customerId") int customerId,
            @PathVariable("artistId") int artistId, @PathVariable("artworkId") int artworkId,
            @PathVariable("artGalleryId") int artGalleryId) {
        logger.info("creating transaction");

        if (customerId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artistId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artGalleryId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (artistId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artworkId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (artGalleryId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (transactionService.saveTransaction(customerId, artistId, artworkId, artGalleryId) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            logger.error("Exception when creating a new transaction " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * It shall be possible to update a transaction.
     * 
     * @param TransactionDto transactionDto The data transfer object relating to the transaction
     */
    @PutMapping("/updateTransaction")
    public ResponseEntity<Void> updateTransaction(@Valid @RequestBody TransactionDto transactionDto) {

        logger.info("updating Transaction profile");
        if (transactionDto.getCustomerId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transactionDto.getArtistId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transactionDto.getArtworkId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (transactionDto.getArtGalleryId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (transactionService.updateTransaction(transactionDto) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception e) {
            logger.error("Exception when updating Transaction" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * It shall be possible to remove a transaction.
     * 
     * @param int transactionId The transaction ID
     */
    @PostMapping("/removeTransaction/{transactionId}")
    public ResponseEntity<Void> removeTransaction(@PathVariable("transactionId") int transactionId) {
        logger.info("removing transaction");

        if (transactionId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (transactionService.removeTransaction(transactionId) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception e) {
            logger.error("Exception when removing a transaction " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * It shall be possible to get a transaction by its ID.
     * @param int id The transaction id
     * @return Transaction The transaction
     */
    @GetMapping("/getTransaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") int id) {

        logger.info("get transaction by id");

        if (id == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            if (transactionService.getTransactionById(id) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.ok(transactionService.getTransactionById(id));
            }
        } catch (Exception e) {
            logger.error("Exception when getting transaction by id " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
