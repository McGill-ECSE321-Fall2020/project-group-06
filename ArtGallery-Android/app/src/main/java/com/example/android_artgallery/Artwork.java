package com.example.android_artgallery;


/**
 * {@link Artwork} represents types of artworks.
 * Each object has 3 properties: name, number, and image resource ID.
 */

public class Artwork {

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