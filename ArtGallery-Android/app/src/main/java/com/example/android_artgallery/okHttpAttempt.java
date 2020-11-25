package com.example.android_artgallery;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class okHttpAttempt {
    public static String bearerToken;
    public static String username;

    public static void getHttpResponse(String urlExtension) throws IOException {

        String url = "https://art-gallery-backend.herokuapp.com"+urlExtension;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("cache-control", "no-cache")
                .addHeader("Authorization","Bearer "+bearerToken)
                .build();

//        Response response = client.newCall(request).execute();
//        Log.e(TAG, response.body().string());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                gson.fromJson(response.body().string(),artist.class);

            }
        });
    }
    public static void postRequest(String urlExtension,JSONObject postdata) throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "https://art-gallery-backend.herokuapp.com"+urlExtension;

        OkHttpClient client = new OkHttpClient();



        RequestBody body = RequestBody.create( postdata.toString(),MEDIA_TYPE);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Success");
                bearerToken = response.body().string();
                Log.e(TAG, bearerToken);
            }
        });
    }
}
class artist{

}
