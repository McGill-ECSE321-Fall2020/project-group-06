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

public class BrowseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);


        // Create an ArrayList of Artwork objects
        ArrayList<Artwork> artworks = new ArrayList(Arrays.asList(Ressources.allArtworks));

        // Create an {@link ArtworkAdapter}, whose data source is a list of
        // {@link Artwork}s. The adapter knows how to create list item views for each item
        // in the list.
        ArtworkAdapter myAdapter = new ArtworkAdapter(this, artworks);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_artwork);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
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