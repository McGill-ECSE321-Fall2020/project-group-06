package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

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
//        RequestParams loginParams = new RequestParams();
//        loginParams.put("userName", tv_username.getText().toString());
//        loginParams.put("password", tv_password.getText().toString());
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("userName", tv_username.getText().toString());
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
        System.out.println("Params done");
        //Context myContext = new Context();
        HttpUtils.post(getApplicationContext(), "api/cognito/authenticate", entity, "application/json", new JsonHttpResponseHandler() {
        //HttpUtils.post("api/cognito/authenticate", loginParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response /*JSONObject response*/) {
                //System.out.println("===================================================");
                //System.out.println(response.toString());
                //System.out.println(response.toString());
                System.out.println("===================================================");
                refreshErrorMessage();
//                tv.setText("");
            }


//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                System.out.println("Failure" + statusCode);
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String string, Throwable throwable) {
                System.out.println("Failure" + statusCode);
                System.out.println("Headers" + headers);
                System.out.println("string" + string);
                //System.out.println("throwable" + throwable.toString());
                refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                System.out.println("Failure" + statusCode);
                System.out.println("Headers" + headers);
                System.out.println("jsonObject" + response.toString());
                //System.out.println("throwable" + throwable.toString());
                refreshErrorMessage();
            }
        });

    }

    public void signup(View v) {
        System.out.println("Start of signup method");

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


}