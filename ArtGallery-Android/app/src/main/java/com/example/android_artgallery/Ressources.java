package com.example.android_artgallery;

import com.example.android_artgallery.model.User;

import okhttp3.Response;

/**
 * Ressources class
 */
public class Ressources {

    //Class attributes
    public static String bearerToken = "";
    public static String username = "";
    public static User user;
    public static Response response;
    public static boolean isArtist;
    public static int id;


    /**
     * Get the bearer token
     * @return
     */
    public static String getBearerToken() {
        return bearerToken;
    }

    /**
     * Set the bearer token
     * @param bearerToken
     */
    public static void setBearerToken(String bearerToken) {
        Ressources.bearerToken = bearerToken;
    }

    /**
     * Get the username
     * @return
     */
    public static String getUsername() {
        return username;
    }

    /**
     * Set the username
     * @param username
     */
    public static void setUsername(String username) {
        Ressources.username = username;
    }

    /**
     * Get the user
     * @return
     */
    public static User getUser(){return user;}


    /**
     * Set the user
     * @param user
     */
    public static void setUser(User user){Ressources.user=user;}

}
