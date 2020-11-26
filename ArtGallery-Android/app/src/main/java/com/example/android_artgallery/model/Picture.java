package com.example.android_artgallery.model;

import java.util.Set;

public class Picture{
    private String url;
    private int id;
    private Set<Artwork> favorites;
    private User user;

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

    public Set<Artwork> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Artwork> favorites) {
        this.favorites = favorites;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
