package com.example.android_artgallery;

public class Ressources {
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

}
