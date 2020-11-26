package com.example.android_artgallery.model;

import java.util.Set;

public class ArtGallery{
    private String name;
    private String adress;
    private int id;
    private Set<Transaction> transaction;
    private Set<Artwork> artwork;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    public Set<Artwork> getArtwork() {
        return artwork;
    }

    public void setArtwork(Set<Artwork> artwork) {
        this.artwork = artwork;
    }
}
