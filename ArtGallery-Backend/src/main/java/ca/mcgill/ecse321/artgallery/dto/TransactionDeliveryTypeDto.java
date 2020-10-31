package ca.mcgill.ecse321.artgallery.dto;

import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;

public class TransactionDeliveryTypeDto {
    public Transaction transaction;
    public DeliveryType deliveryType;

    public TransactionDeliveryTypeDto(Transaction transaction, DeliveryType deliveryType){
        this.transaction = transaction;
        this.deliveryType = deliveryType;
    }
}
