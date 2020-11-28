package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_artgallery.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Main activity. Displays the login page with login and signup button
 */
public class MainActivity extends AppCompatActivity {

    //Class attributes
    private String error = null;

    /**
     * Called on the creation of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshErrorMessage();
    }

    /**
     * Called when the login button is pressed. Goes to home activity when done
     * @param v
     */
    public void login(View v) {
        error = "";
        final TextView tv_username = (TextView) findViewById(R.id.username);
        final TextView tv_password = (TextView) findViewById(R.id.password);

        Ressources.setUsername(tv_username.getText().toString());
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("userName", tv_username.getText().toString());
            jsonParams.put("password", tv_password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    okHttpAttempt.postRequest("/api/cognito/authenticate", jsonParams,false);
                    Ressources.setUsername(tv_username.getText().toString());
                    if (Ressources.response.code() == 500) {
                        error = "Incorrect Username or Password";
                        refreshErrorMessage();
                    } else {
                        Ressources.setBearerToken(Ressources.response.body().string());
                        okHttpAttempt.getHttpResponse("/api/user/getUser/" + Ressources.getUsername());
                        Gson gson = new Gson();
                        User user = (User) gson.fromJson(Ressources.response.body().string(), User.class);
                        Ressources.setUser(user);
                        okHttpAttempt.getHttpResponse("/api/artist/getArtist/" + Ressources.getUsername());
                        if(Ressources.response.code() == 200){
                            Ressources.isArtist=true;
                        }
                        else{
                            Ressources.isArtist=false;
                        }
                        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(home);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    /**
     * Called when signup button is pressed. Goes to signup activity when done
     * @param v
     */
    public void signup(View v) {


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

    /**
     * Refreshes the error message
     */
    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);
        tvError.setVisibility(View.VISIBLE);
    }
}
