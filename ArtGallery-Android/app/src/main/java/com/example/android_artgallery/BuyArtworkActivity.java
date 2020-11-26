package com.example.android_artgallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BuyArtworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
    }

    public void returnToArtwork (View V) {
        Intent returnToArtwork = new Intent(getApplicationContext(), ViewArtworkActivity.class);
        startActivity(returnToArtwork);
    }
}