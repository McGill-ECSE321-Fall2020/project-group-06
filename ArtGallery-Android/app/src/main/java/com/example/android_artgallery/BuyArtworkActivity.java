package com.example.android_artgallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Buy artwork activity. Enables a customer to buy a specific artwork
 */
public class BuyArtworkActivity extends AppCompatActivity {

    /**
     * Called on the creation of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
    }

    /**
     * Return to view artwork activity
     * @param V
     */
    public void returnToArtwork (View V) {
        Intent returnToArtwork = new Intent(getApplicationContext(), ViewArtworkActivity.class);
        startActivity(returnToArtwork);
    }
}