package com.example.android_artgallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_artgallery.model.Artwork;

public class ViewArtworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork);

        int index = getIntent().getIntExtra("index", 0);
        Artwork currentArtwork = Ressources.allArtworks[index];

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) findViewById(R.id.artwork_name);
        // Get the version name from the current Artwork object and
        // set this text on the name TextView
        nameTextView.setText(currentArtwork.getArtworkName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) findViewById(R.id.artwork_artist);
        // Get the version number from the current Artwork object and
        // set this text on the number TextView
        numberTextView.setText((int) currentArtwork.getArtworkPrice());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) findViewById(R.id.artwork_picture);
        // Get the image resource ID from the current Artwork object and
        // set the image to iconView
        iconView.setImageBitmap(currentArtwork.getBitmap());
    }

    public void browse (View V) {
        Intent browse = new Intent(getApplicationContext(), BrowseActivity.class);
        startActivity(browse);
    }
    public void buy (View V) {
        Button deliveryButton = (Button) findViewById(R.id.delivery);
        deliveryButton.setVisibility(View.VISIBLE);
    }
}
