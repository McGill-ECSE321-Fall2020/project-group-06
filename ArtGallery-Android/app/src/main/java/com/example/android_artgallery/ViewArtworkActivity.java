package com.example.android_artgallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ViewArtworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork);
    }

    public void browse (View V) {
        Intent browse = new Intent(getApplicationContext(), Browse.class);
        startActivity(browse);
    }
    public void buy (View V) {
        Intent buyArtwork = new Intent(getApplicationContext(), BuyArtworkActivity.class);
        startActivity(buyArtwork);
    }
}
