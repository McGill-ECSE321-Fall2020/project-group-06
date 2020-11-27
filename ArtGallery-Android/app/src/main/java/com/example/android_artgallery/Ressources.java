package com.example.android_artgallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.android_artgallery.model.Artwork;
import com.example.android_artgallery.model.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.content.ContentValues.TAG;

/**
 * Ressources class
 */
public class Ressources {

    //Class attributes
    public static String bearerToken = "";
    public static String username = "";
    public static User user;
    public static Artwork[] allArtworks;

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
