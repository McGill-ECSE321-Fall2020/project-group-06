package ca.mcgill.ecse321.artgallery.dto;

import java.sql.Date;

import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;

public class BuyArtworkDto {
    public Artwork artwork;
    public double commissionCut;
    public Customer customer;
    public Date date;
    public DeliveryType deliveryType;

    public BuyArtworkDto(Artwork artwork, double commissionCut, Customer customer, Date date, DeliveryType deliveryType){
        this.artwork = artwork;
        this.commissionCut = commissionCut;
        this.customer = customer;
        this.date = date;
        this.deliveryType = deliveryType;
    }
}
