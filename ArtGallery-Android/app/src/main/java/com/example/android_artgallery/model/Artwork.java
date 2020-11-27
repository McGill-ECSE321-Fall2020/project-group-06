package com.example.android_artgallery.model;

import android.graphics.Bitmap;
import com.example.android_artgallery.okHttpAttempt;
import java.util.Set;

/**
 * {@link Artwork} represents types of artworks.
 * Each object has 3 properties: name, number, and image resource ID.
 */

public class Artwork {
    public enum TypeOfArtwork
    {
        Sculpture, Painting, Photography, Other
    }

    //Artwork attributes
    private String name;
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
    private Bitmap bitmap;


    //Getters and setters for access
    /**
     * Get the name of the artwork
     */
    public String getName() {
        return name;
    }
    /**
     * Set the name of the artwork
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get the URL of the artwork
     */
    public String getUrl() { return url; }
    /**
     * Set the URL of the artwork
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * Get the ID of the artwork
     */
    public int getId() {
        return id;
    }
    /**
     * Set the ID of the artwork
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Get the price of the artwork
     */
    public double getPrice() {
        return price;
    }
    /**
     * Set the price of the artwork
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Get the description of the artwork
     */
    public String getDescription() {
        return description;
    }
    /**
     * Set the description of the artwork
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Get the picture of the artwork
     */
    public Set<Picture> getPicture() {
        return picture;
    }
    /**
     * Set the picture of the artwork
     */
    public void setPicture(Set<Picture> picture)
    {
        this.picture = picture;
    }

    /**
     * Get the artist of the artwork
     */
    public User getArtist() {
        return artist;
    }
    /**
     * Set the artist of the artwork
     */
    public void setArtist(User artist)
    {
        this.artist = artist;
    }

    /**
     * Get the transactions of the artwork
     */
    public Set<Transaction> getTransaction() {
        return transaction;
    }
    /**
     * Set the transactions of the artwork
     */
    public void setTransaction(Set<Transaction> transactions)
    {
        this.transaction = transactions;
    }

    /**
     * Get the art gallery of the artwork
     */
    public ArtGallery getArtGallery() {
        return artGallery;
    }
    /**
     * Set the art gallery of the artwork
     */
    public void setArtGallery(ArtGallery artGallery)
    {
        this.artGallery = artGallery;
    }

    /**
     * Get the in store boolean of the artwork
     */
    public boolean getInStore() { return isInStore; }
    /**
     * Set the in store boolean of the artwork
     */
    public void setInStore(boolean inStore)
    {
        this.isInStore = inStore;
    }

    /**
     * Get the for sale boolean of the artwork
     */
    public boolean getForSale() {
        return forSale;
    }
    /**
     * Set the for sale boolean of the artwork
     */
    public void setForSale(boolean forSale)
    {
        this.forSale = forSale;
    }

    /**
     * Get the type of the artwork
     */
    public TypeOfArtwork getTypeOfArtwork() {
        return typeOfArtwork;
    }
    /**
     * Set the type of the artwork
     */
    public void setTypeOfArtwork(TypeOfArtwork typeOfArtwork)
    {
        this.typeOfArtwork = typeOfArtwork;
    }

    /**
     * Get the bit map of the artwork picture
     */
    public Bitmap getBitmap() {
        if(this.url ==null){
            return null;
        }
        if(bitmap==null){
            okHttpAttempt.getImageBitmap(this);
            return bitmap;
        }
        return bitmap;
    }
    /**
     * Set the bit map of the artwork picture
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
  
//    public String getImageResourceId() {
//        return artworkURL;
//    }

//    /*
//     * Create a new artwork object.
//     *
//     * @param vName is the name of the artwork
//     * @param vNumber is the corresponding number of artworks
//     * @param image is drawable reference ID that corresponds to the artwork
//     * */
//    public Artwork(String vName, User vArtist)
//    {
//        artworkName = vName;
//        artworkArtist = vArtist;
//        //artworkPicture = imageResourceId;
//    }
}

