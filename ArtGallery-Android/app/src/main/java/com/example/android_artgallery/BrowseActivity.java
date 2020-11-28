package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android_artgallery.model.Artwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Browse activity. Displays all the artworks
 */
public class BrowseActivity extends AppCompatActivity {

    /**
     * Called on the creation of the activity. Sets the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);


        // Create an ArrayList of Artwork objects
        ArrayList<Artwork> artworks = Ressources.allArtworks;
        ArrayList<Artwork> forSaleArtworks = new ArrayList<Artwork>();
        Ressources.forSaleArtworks = new ArrayList<Artwork>();
        for (int i = 0; i < artworks.size(); i++) {
            if (artworks.get(i).getForSale()) {
                forSaleArtworks.add(artworks.get(i));
                Ressources.forSaleArtworks.add(artworks.get(i));
            }
        }

        // Create an {@link ArtworkAdapter}, whose data source is a list of
        // {@link Artwork}s. The adapter knows how to create list item views for each item
        // in the list.
        ArtworkAdapter myAdapter = new ArtworkAdapter(this, forSaleArtworks);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_artwork);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Called when an artwork is pressed. Goes to the view artwork activity when done
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Looking up an artwork");
                Intent donut = new Intent(BrowseActivity.this, ViewArtworkActivity.class);
                donut.putExtra("index", i);
                startActivity(donut);
            }
        });
    }
}