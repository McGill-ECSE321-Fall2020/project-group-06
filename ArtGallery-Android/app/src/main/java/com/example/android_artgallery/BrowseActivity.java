package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android_artgallery.model.Artwork;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Browse activity. Displays all the artworks
 */
public class BrowseActivity extends AppCompatActivity {
    public static ArrayList<Artwork> artworks;

    /**
     * Called on the creation of the activity. Sets the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        Gson gson = new Gson();
        Artwork[] artworksArray = gson.fromJson(getIntent().getStringExtra("artworks"), Artwork[].class);
        artworks = new ArrayList<Artwork>(Arrays.asList(artworksArray));

        // Create an {@link ArtworkAdapter}, whose data source is a list of
        // {@link Artwork}s. The adapter knows how to create list item views for each item
        // in the list.
        ArtworkAdapter myAdapter = new ArtworkAdapter(this, artworks);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_artwork);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Called when an artwork is pressed. Goes to the view artwork activity/edit artwork activity when done
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Artwork artwork = artworks.get(i);
                boolean isSame = false;
                for (Artwork art : Ressources.getUser().getArtwork()) {
                    if (art.getId() == artwork.getId()) {
                        isSame = true;
                    }
                }
                Intent prompt;
                if(isSame && Ressources.isArtist){
                    prompt = new Intent(BrowseActivity.this, EditArtworkActivity.class);
                }
                else{
                    prompt = new Intent(BrowseActivity.this, ViewArtworkActivity.class);
                }
                prompt.putExtra("index", i);
                startActivity(prompt);
            }
        });
    }

    /**
     * Returns to previous activity
     * @param v
     */
    public void returnToHome(View v) {
        finish();
    }
}