package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_artgallery.model.Artwork;
import com.example.android_artgallery.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;

/**
 * Profile activity class
 */
public class ProfileActivity extends AppCompatActivity {

    //Class attributes
    private String error = null;

    /**
     * Called on the creation of the activity. Sets the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final TextView tv_name = (TextView) findViewById(R.id.nameText);
        final TextView tv_description = (TextView) findViewById(R.id.descriptionText);
        final TextView tv_phoneNumber = (TextView) findViewById(R.id.phoneNumberText);
        final TextView tv_email = (TextView) findViewById(R.id.emailText);
        final Button tv_browse = (Button) findViewById(R.id.artworksButton);
        tv_name.setText(Ressources.getUser().getFirstName());
        tv_description.setText(Ressources.getUser().getDescription());
        tv_phoneNumber.setText(Ressources.getUser().getPhoneNumber());
        tv_email.setText(Ressources.getUser().getEmail());
        if(Ressources.isArtist){
            tv_browse.setText("Browse artwork");
        }
        else{
            tv_browse.setText("Browse favorites");
        }
    }

    public void returnToLogin(View v) {
        finish();
    }

    public void updateProfile(View v){
        error = "";
        final TextView tv_firstName = (TextView) findViewById(R.id.nameText);
        final TextView tv_description = (TextView) findViewById(R.id.descriptionText);
        final TextView tv_phoneNumber = (TextView) findViewById(R.id.phoneNumberText);
        final TextView tv_email = (TextView) findViewById(R.id.emailText);

        // print values for debugging
        System.out.println("username: " + Ressources.getUsername());
        System.out.println("password: " + Ressources.getUser().getPassword());
        System.out.println("Name: " + tv_firstName.getText().toString());
        System.out.println("description: " + tv_description.getText().toString());
        System.out.println("phoneNumber: " + tv_phoneNumber.getText().toString());
        System.out.println("email: " + tv_email.getText().toString());

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("username", Ressources.getUsername());
            jsonParams.put("password", Ressources.getUser().getPassword());
            jsonParams.put("firstName" , tv_firstName.getText().toString());
            jsonParams.put("description", tv_description.getText().toString());
            jsonParams.put("phoneNumber", tv_phoneNumber.getText().toString());
            jsonParams.put("email", tv_email.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    okHttpAttempt.putRequest("/api/user/updateUser", jsonParams,true);
                    Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(home);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public void goToArtwork(View view){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Artwork[] myArray=new Artwork[Ressources.getUser().getArtwork().size()];
                    myArray = Ressources.getUser().getArtwork().toArray(myArray);
                    Gson gson = new Gson();
                    String myJson = gson.toJson(myArray);
                    Intent browse = new Intent(getApplicationContext(), BrowseActivity.class);
                    browse.putExtra("artworks", myJson);
                    startActivity(browse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}