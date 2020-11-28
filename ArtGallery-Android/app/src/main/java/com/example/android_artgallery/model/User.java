package com.example.android_artgallery.model;

import java.util.Set;

/**
 * User class
 */
public class User {

    //User attributes
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int id;
    private String description;
    private Picture picture;
    private String phoneNumber;
    private Set<Artwork> artwork;
    private Set<Transaction> transaction;
    private String bankAccountNumber;
    private long creditCardNumber;

    /**
     * Get the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the description of the user
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the user
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the picture of the user
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * Set the picture of the user
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    /**
     * Get the phone number of the user
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number of the user
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the artwork of the user
     */
    public Set<Artwork> getArtwork() {
        return artwork;
    }

    /**
     * Set the artwork of the user
     */
    public void setArtwork(Set<Artwork> artwork) {
        this.artwork = artwork;
    }

    /**
     * Get the transactions of the user
     */
    public Set<Transaction> getTransaction() {
        return transaction;
    }

    /**
     * Set the transactions of the user
     */
    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    /**
     * Get the bank account number of the user
     */
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    /**
     * Set the bank account number of the user
     */
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    /**
     * Get the credit card number of the user
     */
    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Set the credit card number of the user
     */
    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
