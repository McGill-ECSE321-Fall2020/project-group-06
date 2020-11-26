package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_artgallery.model.Artwork;
import com.example.android_artgallery.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshErrorMessage();


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void login(View v) {
        System.out.println("Start of login method");
        error = "";
        final TextView tv_username = (TextView) findViewById(R.id.username);
        final TextView tv_password = (TextView) findViewById(R.id.password);
        System.out.println("username" + tv_username.getText().toString());

        System.out.println("password" + tv_password.getText().toString());
        Ressources.setUsername(tv_username.getText().toString());
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("userName", tv_username.getText().toString());
            jsonParams.put("password", tv_password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Params done");
        //Context myContext = new Context();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    okHttpAttempt.postRequest("/api/cognito/authenticate", jsonParams,false);
                    Ressources.setUsername(tv_username.getText().toString());

                    Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(home);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    public void signup(View v) {
        System.out.println("Start of signup method");


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Intent signup = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(signup);

    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    public void browse (View V) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Artwork[] myArray = null;
                    myArray = (Artwork[]) okHttpAttempt.getHttpResponse("/api/artgallery/allArtworks", Artwork[].class);
                    Ressources.allArtworks=myArray;
                    System.out.println(myArray[0].getName());
                    Intent browse = new Intent(getApplicationContext(), BrowseActivity.class);
                    startActivity(browse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    public void home( View v) {
        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(home);
    }

}
