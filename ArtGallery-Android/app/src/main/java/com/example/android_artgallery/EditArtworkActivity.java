package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android_artgallery.model.ArtGallery;
import com.example.android_artgallery.model.Artwork;
import com.example.android_artgallery.model.Artwork.TypeOfArtwork;
import com.example.android_artgallery.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Edit artwork activity. Enables an artist or admin to edit a specific artwork
 */
public class EditArtworkActivity extends AppCompatActivity {

    //Class attributes
    private String error = null;
    private int index;
    private Artwork currentArtwork;

    /**
     * Called on the creation of the activity. Sets the artwork parameters
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artwork);

        index = getIntent().getIntExtra("index", 0);
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
        artistTextView.setText(Ressources.getUser().getLastName());

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

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        TextView descriptionView = (TextView) findViewById(R.id.artwork_description);
        // Get the image resource ID from the current Artwork object and
        // set the image to iconView
        descriptionView.setText(currentArtwork.getDescription());

        Spinner spinner = (Spinner) findViewById(R.id.type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.edit_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        switch(currentArtwork.getTypeOfArtwork()){
            case Sculpture:
                spinner.setSelection(0);
                break;
            case Painting:
                spinner.setSelection(1);
                break;
            case Photography:
                spinner.setSelection(2);
                break;
            case Other:
                spinner.setSelection(3);
            default:
                break;
        }

        Spinner newSpinner = (Spinner) findViewById(R.id.store);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> newAdapter = ArrayAdapter.createFromResource(this, R.array.for_sale, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        newSpinner.setAdapter(newAdapter);
        if (currentArtwork.getInStore()) {
            newSpinner.setSelection(0);
        } else {
            newSpinner.setSelection(1);
        }
    }

    /**
     * Return to browse activity
     * @param V
     */
    public void browse (View V) {
        Intent browse = new Intent(getApplicationContext(), BrowseActivity.class);
        startActivity(browse);
    }

    /**
     * Called when the edit artwork button is pressed. Creates the request to edit the artwork with the new parameters. Returns to main activity when done
     * @param V
     */
    public void edit (View V) {
        System.out.println("Editing");
        error = "";
        final TextView tv_name = (TextView) findViewById(R.id.artwork_name);
        final TextView tv_price = (TextView) findViewById(R.id.artwork_price);
        final TextView tv_availability = (TextView) findViewById(R.id.artwork_availability);
        final TextView tv_description = (TextView) findViewById(R.id.artwork_description);
        final TextView tv_error = (TextView) findViewById(R.id.editError);
        Spinner isInStore = (Spinner) findViewById(R.id.store);
        Spinner type = (Spinner) findViewById(R.id.type);
        JSONObject jsonParams = new JSONObject();
        User artist = new User();
        artist.setUsername(Ressources.getUsername());
        artist.setId(Ressources.id);
        ArtGallery artGallery = new ArtGallery();
        artGallery.setName("Online Art Gallery");
        artGallery.setId(8988);
        try {
            jsonParams.put("name", tv_name.getText().toString());
            jsonParams.put("description", tv_description.getText().toString());
            jsonParams.put("isInStore", isInStore.getSelectedItem().toString().equals("Yes"));
            jsonParams.put("id", currentArtwork.getId());
            jsonParams.put("price", tv_price.getText().toString());
            jsonParams.put("artist", artist);
            jsonParams.put("artGallery", artGallery);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("Params done");

        try {
            okHttpAttempt.putRequest("/api/artwork/updateArtwork", jsonParams,true);
            tv_error.setText("Successfully edited");
        } catch (IOException x) {
            System.out.println(x);
            tv_error.setText("Something went wrong");
        }

        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);
    }

}