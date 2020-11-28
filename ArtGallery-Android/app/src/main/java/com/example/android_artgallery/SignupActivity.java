package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Signup activity class
 */
public class SignupActivity extends AppCompatActivity {

    //Class attributes
    private String error = null;

    /**
     * Called on the creation of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    /**
     * Called when the signup button is pressed. Creates the request to signup the user with the parameters. Goes to main activity when done
     * @param v
     */
    public void signupUser(View v) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    error = "";
                    final TextView tv_email = (TextView) findViewById(R.id.signupEmail);
                    final TextView tv_username = (TextView) findViewById(R.id.signupUsername);
                    final TextView tv_password = (TextView) findViewById(R.id.signupPassword);
                    final TextView tv_confirm_password = (TextView) findViewById(R.id.signupConfirmPassword);
                    final TextView tv_error = (TextView) findViewById(R.id.signupError);
                    if (tv_username.getText().toString().equals("") || tv_password.getText().toString().equals("") || tv_confirm_password.getText().toString().equals("") || tv_email.getText().toString().equals("")) {
                        tv_error.setText("Fields can't be empty!");
                        return;
                    }
                    if (!tv_password.getText().toString().equals(tv_confirm_password.getText().toString())) {
                        tv_error.setText("Warning: Passwords are not identical!");
                        return;
                    }
                    if (!tv_email.getText().toString().contains("@")) {
                        tv_error.setText("Email needs to contain @!");
                        return;
                    }
                    JSONObject jsonParams = new JSONObject();
                    try {
                        jsonParams.put("username", tv_username.getText().toString());
                        jsonParams.put("password", tv_password.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    StringEntity entity = null;
                    try {
                        entity = new StringEntity(jsonParams.toString());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                    RadioButton artistButton = radioGroup.findViewById(R.id.artistButton);
                    RadioButton customerButton = radioGroup.findViewById(R.id.customerButton);
                    if (artistButton.isChecked()) {
                        try {
                            tv_error.setText("Creating artist account");
                            okHttpAttempt.postRequest("/api/artist/createArtist", jsonParams, false);
                        } catch (IOException x) {
                            System.out.println(x);
                            tv_error.setText("Something went wrong");
                        }
                    } else if (customerButton.isChecked()) {
                        try {
                            tv_error.setText("Creating customer account");
                            okHttpAttempt.postRequest("/api/customer/createCustomer", jsonParams, false);
                        } catch (IOException x) {
                            System.out.println(x);
                            tv_error.setText("Something went wrong");
                        }
                    } else {
                        try {
                            tv_error.setText("Creating user account");
                            okHttpAttempt.postRequest("/api/user/createUser", jsonParams, false);
                        } catch (IOException x) {
                            System.out.println(x);
                            tv_error.setText("Something went wrong");
                        }
                    }

                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}