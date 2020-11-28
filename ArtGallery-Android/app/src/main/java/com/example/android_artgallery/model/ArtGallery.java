package com.example.android_artgallery.model;

import java.util.Set;

/**
 * Art Gallery class
 */
public class ArtGallery{

    //Art Gallery attributes
    private String name;
    private String adress;
    private int id;
    private Set<Transaction> transaction;
    private Set<Artwork> artwork;

    /**
     * Get the name of the Art Gallery
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the Art Gallery
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the address of the Art Gallery
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Set the address of the Art Gallery
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Get the id of the Art Gallery
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the Art Gallery
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the transactions of the Art Gallery
     */
    public Set<Transaction> getTransaction() {
        return transaction;
    }

    /**
     * Set the transactions of the Art Gallery
     */
    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    /**
     * Get the artwork of the Art Gallery
     */
    public Set<Artwork> getArtwork() {
        return artwork;
    }

    /**
     * Set the artwork of the Art Gallery
     */
    public void setArtwork(Set<Artwork> artwork) {
        this.artwork = artwork;
    }
}
