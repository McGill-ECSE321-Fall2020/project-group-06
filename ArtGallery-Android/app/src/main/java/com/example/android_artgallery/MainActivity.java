package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

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

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("userName", tv_username.getText().toString());
            jsonParams.put("password", tv_password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Params done");
        //Context myContext = new Context();

        try {
            okHttpAttempt.postRequest("/api/cognito/authenticate", jsonParams);
            Ressources.setUsername(tv_username.getText().toString());
        }
        catch (IOException x ){
            System.out.println(x);
        }
    }

    public void signup(View v) {
        System.out.println("Start of signup method");

        try {
            User user=(User)okHttpAttempt.getHttpResponse("/api/artist/getArtist/Raphael",User.class);
        }
        catch (IOException x ){
            System.out.println(x);
        }


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
        Intent browse = new Intent(getApplicationContext(), Browse.class);
        startActivity(browse);
    }

}
