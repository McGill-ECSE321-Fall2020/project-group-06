package ca.mcgill.ecse321.artgallery.dto;

import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Customer;

public class ArtworkCustomerDto {
    public Artwork artwork;
    public Customer customer;

    public ArtworkCustomerDto(Artwork artwork, Customer customer){
        this.artwork = artwork;
        this.customer = customer;
    }
}
