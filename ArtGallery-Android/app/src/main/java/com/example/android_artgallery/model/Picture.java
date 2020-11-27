package com.example.android_artgallery.model;

import java.util.Set;

/**
 * Picture class
 */
public class Picture{

    //Picture attributes
    private String url;
    private int id;
    private Set<Artwork> favorites;
    private User user;

    /**
     * Get the url of the picture
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the url of the picture
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the id of the picture
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the picture
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the favorites of the picture
     */
    public Set<Artwork> getFavorites() {
        return favorites;
    }

    /**
     * Set the favorites of the picture
     */
    public void setFavorites(Set<Artwork> favorites) {
        this.favorites = favorites;
    }

    /**
     * Get the user of the picture
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user of the picture
     */
    public void setUser(User user) {
        this.user = user;
    }
}
