package com.example.android_artgallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_artgallery.model.Artwork;

public class ViewArtworkActivity extends AppCompatActivity {
    Artwork currentArtwork = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork);

        int index = getIntent().getIntExtra("index", 0);
        currentArtwork = BrowseActivity.artworks.get(index);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) findViewById(R.id.artwork_name);
        // Get the version name from the current Artwork object and
        // set this text on the name TextView
        nameTextView.setText(currentArtwork.getName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView artistTextView = (TextView) findViewById(R.id.artwork_artist);
        // Get the version number from the current Artwork object and
        // set this text on the number TextView
        artistTextView.setText(currentArtwork.getArtist().getLastName());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) findViewById(R.id.artwork_picture);
        // Get the image resource ID from the current Artwork object and
        // set the image to iconView
        iconView.setImageBitmap(currentArtwork.getBitmap());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        TextView priceView = (TextView) findViewById(R.id.artwork_price);
        // Get the image resource ID from the current Artwork object and
        // set the image to iconView
        priceView.setText(String.valueOf(currentArtwork.getPrice()));

//        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
//        TextView availabilityView = (TextView) findViewById(R.id.artwork_availability);
//        // Get the image resource ID from the current Artwork object and
//        // set the image to iconView
//        if (currentArtwork.getInStore()) {
//            availabilityView.setText("Available in store");
//        }
//        else
//        {
//            availabilityView.setText("Not available in store");
//        }

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        TextView descriptionView = (TextView) findViewById(R.id.artwork_description);
        // Get the image resource ID from the current Artwork object and
        // set the image to iconView
        descriptionView.setText(currentArtwork.getDescription());
    }

    public void browse (View V) {
        finish();
    }

//    public void buy (View V) {
//        if (currentArtwork.getForSale()) {
//            ImageButton favouriteButton = findViewById(R.id.favouriteButton);
//            favouriteButton.setVisibility(View.GONE);
//            RadioGroup deliveryMethodRadioGroup = findViewById(R.id.deliveryMethod_radioButtons);
//            deliveryMethodRadioGroup.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//        }
//    }
}
