package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_artgallery.model.Artwork;
import com.example.android_artgallery.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void returnToLogin(View v) {
        Intent returnToLogin = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(returnToLogin);
    }

    public void profile(View v) {
        System.out.println("Start of profile method");

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Before get");
                    okHttpAttempt.getHttpResponse("/api/user/getUser/" + Ressources.getUsername(), User.class);
                    System.out.println("BEARER TOKEN: " + Ressources.getBearerToken());
                    System.out.println("Response Inside Profile: " + Ressources.response);
                    Gson gson = new Gson();
                    User user = (User) gson.fromJson(Ressources.response.body().string(), User.class);
                    Ressources.setUser(user);
                    System.out.println("Out of get, user first name is"+user.getFirstName());

                    Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(profile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    public void browse (View V) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Artwork[] myArray = null;
                    okHttpAttempt.getHttpResponse("/api/artgallery/allArtworks", Artwork[].class);
                    System.out.println("Response Inside browse: " + Ressources.response);
                    Gson gson = new Gson();
                    myArray = (Artwork [])gson.fromJson(Ressources.response.body().string(), Artwork[].class);
                    ArrayList<Artwork> myNewArray = new ArrayList<Artwork>(Arrays.asList(myArray));
                    Ressources.allArtworks = myNewArray;
                    Intent browse = new Intent(getApplicationContext(), BrowseActivity.class);
                    startActivity(browse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}