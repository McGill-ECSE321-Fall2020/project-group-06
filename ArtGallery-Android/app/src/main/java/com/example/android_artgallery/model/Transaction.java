package com.example.android_artgallery.model;

import java.sql.Date;

/**
 * Transaction class
 */
public class Transaction{

    //Transaction attributes
    private Date dateOfTransaction;
    private enum DeliveryType {
        PickedUp, Delivered
    }
    private User customer;
    private User artist;
    private Artwork artwork;
    private ArtGallery artGallery;
    private int id;
    private double commisionCut;
    private DeliveryType deliveryType;

    /**
     * Get the date of transaction of the transaction
     */
    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    /**
     * Set the date of transaction of the transaction
     */
    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    /**
     * Get the customer of the transaction
     */
    public User getCustomer() {
        return customer;
    }

    /**
     * Set the customer of the transaction
     */
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    /**
     * Get the artist of the transaction
     */
    public User getArtist() {
        return artist;
    }

    /**
     * Set the artist of the transaction
     */
    public void setArtist(User artist) {
        this.artist = artist;
    }

    /**
     * Get the artwork of the transaction
     */
    public Artwork getArtwork() {
        return artwork;
    }

    /**
     * Set the artwork of the transaction
     */
    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    /**
     * Get the art gallery of the transaction
     */
    public ArtGallery getArtGallery() {
        return artGallery;
    }

    /**
     * Set the art gallery of the transaction
     */
    public void setArtGallery(ArtGallery artGallery) {
        this.artGallery = artGallery;
    }

    /**
     * Get the id of the transaction
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the transaction
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the commission cut of the transaction
     */
    public double getCommisionCut() {
        return commisionCut;
    }

    /**
     * Set the commission cut of the transaction
     */
    public void setCommisionCut(double commisionCut) {
        this.commisionCut = commisionCut;
    }

    /**
     * Get the delivery type of the transaction
     */
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    /**
     * Set the delivery type of the transaction
     */
    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }
}
