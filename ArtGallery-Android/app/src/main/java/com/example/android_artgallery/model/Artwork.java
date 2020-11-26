package com.example.android_artgallery.model;

import java.util.Set;

/**
 * {@link Artwork} represents types of artworks.
 * Each object has 3 properties: name, number, and image resource ID.
 */

public class Artwork {
    private String name;
    private enum TypeOfArtwork {
        Sculpture, Painting, Photography, Other
    }
    private String url;
    private int id;
    private double price;
    private String description;
    private Set<Picture> picture;
    private User artist;
    private Set<Transaction> transaction;
    private ArtGallery artGallery;
    private boolean isInStore;
    private boolean forSale;
    private TypeOfArtwork typeOfArtwork;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Picture> getPicture() {
        return picture;
    }

    public void setPicture(Set<Picture> picture) {
        this.picture = picture;
    }

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    public ArtGallery getArtGallery() {
        return artGallery;
    }

    public void setArtGallery(ArtGallery artGallery) {
        this.artGallery = artGallery;
    }

    public boolean isInStore() {
        return isInStore;
    }

    public void setInStore(boolean inStore) {
        isInStore = inStore;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public TypeOfArtwork getTypeOfArtwork() {
        return typeOfArtwork;
    }

    public void setTypeOfArtwork(TypeOfArtwork typeOfArtwork) {
        this.typeOfArtwork = typeOfArtwork;
    }

    // Name of the artwork
    private String mArtworkName;

    // Number of artwork
    private int mArtworkNumber;

    // Drawable resource ID
    private int mImageResourceId;

    /*
     * Create a new artwork object.
     *
     * @param vName is the name of the artwork
     * @param vNumber is the corresponding number of artworks
     * @param image is drawable reference ID that corresponds to the artwork
     * */
    public Artwork(String vName, int vNumber, int imageResourceId)

    {
        mArtworkName = vName;
        mArtworkNumber = vNumber;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the name of the artwork
     */
    public String getArtworkName() {
        return mArtworkName;
    }

    /**
     * Get the  number of artworks
     */
    public int getArtworkNumber() {
        return mArtworkNumber;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

}

