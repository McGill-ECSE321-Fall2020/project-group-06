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

import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class Ressources {
    public static Response response;
    public static String getBearerToken() {
        return bearerToken;
    }

    public static void setBearerToken(String bearerToken) {
        Ressources.bearerToken = bearerToken;
    }

    public static String bearerToken = "";

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Ressources.username = username;
    }

    public static String username = "";
    public static User user;
    public static User getUser(){return user;}
    public static void setUser(User user){Ressources.user=user;}
    public static Artwork[] allArtworks;
    public static int id;



}
