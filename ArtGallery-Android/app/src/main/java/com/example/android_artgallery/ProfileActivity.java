package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_artgallery.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final TextView tv_name = (TextView) findViewById(R.id.nameText);
        final TextView tv_description = (TextView) findViewById(R.id.descriptionText);
        final TextView tv_phoneNumber = (TextView) findViewById(R.id.phoneNumberText);
        final TextView tv_email = (TextView) findViewById(R.id.emailText);
        tv_name.setText(Ressources.getUser().getFirstName()+Ressources.getUser().getLastName());
        tv_description.setText(Ressources.getUser().getDescription());
        tv_phoneNumber.setText(Ressources.getUser().getPhoneNumber());
        tv_email.setText(Ressources.getUser().getEmail());
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
            okHttpAttempt.getHttpResponse("/api/user/getUser/" + Ressources.getUsername(), User.class);
        } catch (IOException x){
            System.out.println(x);
        }
    }

    public void updateProfile(View v){
        System.out.println("Start of update profile method");
        error = "";
        final TextView tv_name = (TextView) findViewById(R.id.nameText);
        final TextView tv_description = (TextView) findViewById(R.id.descriptionText);
        final TextView tv_phoneNumber = (TextView) findViewById(R.id.phoneNumberText);
        final TextView tv_email = (TextView) findViewById(R.id.emailText);

        // print values for debugging
        System.out.println("username: " + tv_name.getText().toString());
        System.out.println("description: " + tv_description.getText().toString());
        System.out.println("phoneNumber: " + tv_phoneNumber.getText().toString());
        System.out.println("email: " + tv_email.getText().toString());

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("username", tv_name.getText().toString());
            jsonParams.put("description", tv_description.getText().toString());
            jsonParams.put("phoneNumber", tv_phoneNumber.getText().toString());
            jsonParams.put("email", tv_email.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Params done");

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Updating the profile......");
                    okHttpAttempt.putRequest("/api/user/updateUser", jsonParams,true);;
                    Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(profile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

}