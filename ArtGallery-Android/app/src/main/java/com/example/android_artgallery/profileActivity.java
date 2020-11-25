package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;

public class profileActivity extends AppCompatActivity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void getProfileInfo(View v){
        // get values from activity
        System.out.println("Getting profile info");
        error = "";
        final TextView tv_name = (TextView) findViewById(R.id.nameText);
        final TextView tv_description = (TextView) findViewById(R.id.descriptionText);
        final TextView tv_phoneNumber = (TextView) findViewById(R.id.phoneNumberText);
        final TextView tv_email = (TextView) findViewById(R.id.emailText);

        // print values for debugging
        System.out.println("name: " + tv_name.getText().toString());
        System.out.println("description: " + tv_description.getText().toString());
        System.out.println("phoneNumber: " + tv_phoneNumber.getText().toString());
        System.out.println("email: " + tv_email.getText().toString());

        JSONObject jsonParams = new JSONObject();

        try{
            okHttpAttempt.getHttpResponseUser("/api/user/getUser/" + Ressources.getUsername());
        } catch (IOException x){
            System.out.println(x);
        }
    }

}