package com.example.android_artgallery.model;

import android.graphics.Bitmap;
import com.example.android_artgallery.okHttpAttempt;
import java.util.Set;

/**
 * {@link Artwork} represents types of artworks.
 * Each object has 3 properties: name, number, and image resource ID.
 */

public class Artwork {
    private enum TypeOfArtwork
    {
        Sculpture, Painting, Photography, Other
    }

    //Artwork attributes
    private String artworkName;
    private String artworkURL;
    private int artworkID;
    private double artworkPrice;
    private String artworkDescription;
    private Set<Picture> artworkPicture;
    private User artworkArtist;
    private Set<Transaction> artworkTransactions;
    private ArtGallery artworkArtGallery;
    private boolean artworkIsInStore;
    private boolean artworkIsForSale;
    private TypeOfArtwork typeOfArtwork;
    private Bitmap bitmap;


    //Getters and setters for access
    /**
     * Get the name of the artwork
     */
    public String getArtworkName() {
        return artworkName;
    }
    /**
     * Set the name of the artwork
     */
    public void setArtworkName(String name)
    {
        this.artworkName = name;
    }

    /**
     * Get the URL of the artwork
     */
    public String getArtworkURL() { return artworkURL; }
    /**
     * Set the URL of the artwork
     */
    public void setArtworkURL(String url)
    {
        this.artworkURL = url;
    }

    /**
     * Get the ID of the artwork
     */
    public int getArtworkID() {
        return artworkID;
    }
    /**
     * Set the ID of the artwork
     */
    public void setArtworkID(int id)
    {
        this.artworkID = id;
    }

    /**
     * Get the price of the artwork
     */
    public double getArtworkPrice() {
        return artworkPrice;
    }
    /**
     * Set the price of the artwork
     */
    public void setArtworkPrice(double price)
    {
        this.artworkPrice = price;
    }

    /**
     * Get the description of the artwork
     */
    public String getArtworkDescription() {
        return artworkDescription;
    }
    /**
     * Set the description of the artwork
     */
    public void setArtworkDescription(String description)
    {
        this.artworkDescription = description;
    }

    /**
     * Get the picture of the artwork
     */
    public Set<Picture> getArtworkPicture() {
        return artworkPicture;
    }
    /**
     * Set the picture of the artwork
     */
    public void setArtworkPicture(Set<Picture> picture)
    {
        this.artworkPicture = picture;
    }

    /**
     * Get the artist of the artwork
     */
    public User getArtworkArtist() {
        return artworkArtist;
    }
    /**
     * Set the artist of the artwork
     */
    public void setArtworkArtist(User artist)
    {
        this.artworkArtist = artist;
    }

    /**
     * Get the transactions of the artwork
     */
    public Set<Transaction> getArtworkTransactions() {
        return artworkTransactions;
    }
    /**
     * Set the transactions of the artwork
     */
    public void setArtworkTransactions(Set<Transaction> transactions)
    {
        this.artworkTransactions = transactions;
    }

    /**
     * Get the art gallery of the artwork
     */
    public ArtGallery getArtworkArtGallery() {
        return artworkArtGallery;
    }
    /**
     * Set the art gallery of the artwork
     */
    public void setArtworkArtGallery(ArtGallery artGallery)
    {
        this.artworkArtGallery = artGallery;
    }

    /**
     * Get the in store boolean of the artwork
     */
    public boolean getArtworkIsInStore() { return artworkIsInStore; }
    /**
     * Set the in store boolean of the artwork
     */
    public void setArtworkIsInStore(boolean inStore)
    {
        this.artworkIsInStore = inStore;
    }

    /**
     * Get the for sale boolean of the artwork
     */
    public boolean getArtworkIsForSale() {
        return artworkIsForSale;
    }
    /**
     * Set the for sale boolean of the artwork
     */
    public void setArtworkIsForSale(boolean forSale)
    {
        this.artworkIsForSale = forSale;
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
        if(this.artworkURL==null){
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
  
    public String getImageResourceId() {
        return url;
    }

//    /*
//     * Create a new artwork object.
//     *
//     * @param vName is the name of the artwork
//     * @param vNumber is the corresponding number of artworks
//     * @param image is drawable reference ID that corresponds to the artwork
//     * */
//    public Artwork(String vName, int vNumber, int imageResourceId)
//
//    {
//        artworkName = vName;
//        mArtworkNumber = vNumber;
//        mImageResourceId = imageResourceId;
//    }
}

