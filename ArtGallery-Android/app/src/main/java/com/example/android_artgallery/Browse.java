package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Browse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        // Create an ArrayList of Artwork objects
        ArrayList<Dessert> desserts = new ArrayList<Dessert>();

        desserts.add(new Dessert("Donut", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Cookie", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("PieceOfCake", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Pastry", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Donut", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Cookie", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("PieceOfCake", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Pastry", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Donut", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Cookie", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("PieceOfCake", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Pastry", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Donut", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Cookie", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("PieceOfCake", 0, R.drawable.mona_lisa));
        desserts.add(new Dessert("Pastry", 0, R.drawable.mona_lisa));

        // Create an {@link DessertAdapter}, whose data source is a list of
        // {@link Dessert}s. The adapter knows how to create list item views for each item
        // in the list.
        DessertAdapter flavorAdapter = new DessertAdapter(this, desserts);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_dessert);
        listView.setAdapter(flavorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Dessert dessert = desserts.get(i);
                switch(i) {
                    case 0:
                        System.out.println("Looking up an artwork");
                        Intent donut = new Intent(Browse.this, artwork.class);  //different intents?
                        startActivity(donut);
                        break;
                    case 1:
                        System.out.println("Looking up an artwork");
                        Intent cookie = new Intent(Browse.this, artwork.class);
                        startActivity(cookie);
                        break;
                    case 2:
                        System.out.println("Looking up an artwork");
                        Intent pieceOfCake = new Intent(Browse.this, artwork.class);
                        startActivity(pieceOfCake);
                        break;
                    case 3:
                        System.out.println("Looking up an artwork");
                        Intent pastry = new Intent(Browse.this, artwork.class);
                        startActivity(pastry);
                        break;
                }
            }
        });
    }
    public void browse (View V) {
        System.out.println("Started empty browse");
    }
    public void lookupArtwork (View V) {
        System.out.println("Looking up an artwork");
//        Intent lookupArtwork = new Intent(getApplicationContext(), artwork.class);
//        startActivity(lookupArtwork);
    }
}