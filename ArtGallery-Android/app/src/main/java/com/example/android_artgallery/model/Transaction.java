package com.example.android_artgallery.model;

import java.sql.Date;

public class Transaction{
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

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    public ArtGallery getArtGallery() {
        return artGallery;
    }

    public void setArtGallery(ArtGallery artGallery) {
        this.artGallery = artGallery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCommisionCut() {
        return commisionCut;
    }

    public void setCommisionCut(double commisionCut) {
        this.commisionCut = commisionCut;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }
}
