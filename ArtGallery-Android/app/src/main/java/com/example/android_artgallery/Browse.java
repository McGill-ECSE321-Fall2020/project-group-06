package com.example.android_artgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Browse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
    }
    public void login (View V) {

    }
    public void signup (View V) {

    }
    public void Browse (View V) {
        Intent browse = new Intent(getApplicationContext(), Browse.class);
        startActivity(browse);
    }
}