package com.example.android_artgallery;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_artgallery.model.Artwork;
import com.example.android_artgallery.model.User;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

/**
 * View artwork activity class
 */
public class ViewArtworkActivity extends AppCompatActivity {
    Artwork currentArtwork = null;
    int index;

    /**
     * Called on the creation of the activity. Sets the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork);

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
        if(Ressources.isArtist){
            artistTextView.setText(Ressources.getUser().getLastName());
        }
        else {
            artistTextView.setText(currentArtwork.getArtist().getLastName());
        }

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

        ImageButton favButton = (ImageButton) findViewById(R.id.favouriteButton);

        if(Ressources.isArtist){
            favButton.setVisibility(View.GONE);
        }
        else {
            boolean isFavorited = false;
            for (Artwork art : Ressources.getUser().getArtwork()) {
                    if (art.getId() == currentArtwork.getId()) {
                        isFavorited = true;
                    }
            }

            if (isFavorited) {
                favButton.setImageResource(R.drawable.favourites_full);
                favButton.setTag(R.drawable.favourites_full);
            } else {
                favButton.setImageResource(R.drawable.favourites_empty);
                favButton.setTag(R.drawable.favourites_empty);
            }
        }
    }

    /**
     * Called when the browse button is pressed. Goes to browse activity
     * @param V
     */
    public void browse (View V) {
        finish();
    }

    public void favorite(View V) throws IOException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ImageButton favButton = (ImageButton) findViewById(R.id.favouriteButton);
                    Integer resource = (Integer) favButton.getTag();

                    boolean isFavorited=false;
                    if(!Ressources.isArtist) {
                        for(Artwork art:Ressources.getUser().getArtwork()){
                            if(art.getId()==currentArtwork.getId()){
                                isFavorited=true;
                            }
                        }
                        JSONObject js=new JSONObject();
                        if(!resource.equals(R.drawable.favourites_full)) {
                            okHttpAttempt.postRequest("/api/customer/addArtwork/" + Ressources.user.getId() + "/" + currentArtwork.getId(), js, true);
                        }
                        else{
                            okHttpAttempt.postRequest("/api/customer/removeArtwork/" + Ressources.user.getId() + "/" + currentArtwork.getId(), js, true);
                        }
                        Artwork currentArtwork = BrowseActivity.artworks.get(index);
                        okHttpAttempt.getHttpResponse("/api/user/getUser/" + Ressources.getUsername());
                        Gson gson = new Gson();
                        User user = (User) gson.fromJson(Ressources.response.body().string(), User.class);
                        Ressources.setUser(user);

                        if( resource.equals(R.drawable.favourites_full)){
                            favButton.setImageResource(R.drawable.favourites_empty);
                            favButton.setTag(R.drawable.favourites_empty);
                            System.out.println("is unfavorited");
                        }
                        else {
                            favButton.setImageResource(R.drawable.favourites_full);
                            favButton.setTag(R.drawable.favourites_full);
                            System.out.println("is favorited");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
