package com.example.android_artgallery;

import android.util.Log;

import com.example.android_artgallery.model.User;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class okHttpAttempt {
    public static String bearerToken;
    public static String username;

    public static Object getHttpResponse(String urlExtension, Class responseClass) throws IOException {

        String url = "https://art-gallery-backend.herokuapp.com"+urlExtension;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("cache-control", "no-cache")
                .addHeader("Authorization","Bearer "+bearerToken)
                .build();

//        Response response = client.newCall(request).execute();
//        Log.e(TAG, response.body().string());

        Gson gson = new Gson();
        Object object;
        Response response=client.newCall(request).execute();
        String responseBody=response.body().string();
        System.out.println(responseBody);
        object =gson.fromJson(responseBody,responseClass);
        System.out.println(object.getClass());
        System.out.println("Printed Object 0");
        return object;
    }
    public static void postRequest(String urlExtension, JSONObject postdata,boolean putBearerToken) throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "https://art-gallery-backend.herokuapp.com"+ urlExtension;

        OkHttpClient client = new OkHttpClient();



        RequestBody body = RequestBody.create( postdata.toString(),MEDIA_TYPE);
        Request request;
        if(!putBearerToken) {
            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
        }
        else{
            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Authorization","Bearer "+bearerToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
        }
        Response response=client.newCall(request).execute();
        System.out.println("Success");
        bearerToken = response.body().string();
        System.out.println("Bearer Token"+bearerToken);
        Ressources.setBearerToken(bearerToken);
        Log.e(TAG, bearerToken);
    }
}
