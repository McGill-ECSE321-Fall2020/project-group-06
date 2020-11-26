package com.example.android_artgallery;

import java.sql.Date;
import java.util.Set;

class User {
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
     int userId;
     String description;
     Picture picture;
     String phoneNumber;
     Set<Artwork> artwork;
     Set<Transaction> transaction;
     String bankAccountNumber;
     long creditCardNumber;

}
class Artwork{
    String name;
    enum TypeOfArtwork {
        Sculpture, Painting, Photography, Other
    }
    String url;
    int id;
    double price;
    String description;
    Set<Picture> picture;
    User artist;
    Set<Transaction> transaction;
    ArtGallery artGallery;
    boolean isInStore;
    boolean forSale;
    TypeOfArtwork typeOfArtwork;
}
class ArtGallery{
    String name;
    String adress;
    int id;
    Set<Transaction> transaction;
    Set<Artwork> artwork;
}
class Picture{
    String url;
    int id;
    Set<Artwork> favorites;
    User user;
}
class Transaction{
     Date dateOfTransaction;
     enum DeliveryType {
        PickedUp, Delivered
    }
    User customer;
    User artist;
    Artwork artwork;
    ArtGallery artGallery;
    int id;
    double commisionCut;
    DeliveryType deliveryType;

}
