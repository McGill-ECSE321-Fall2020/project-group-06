package com.example.android_artgallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.android_artgallery.model.Artwork;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Handles http calls
 */
public class okHttpAttempt {

    //Class attributes
    public static String bearerToken;

    /**
     * Calls a get request on a url extension with a response class
     * @param urlExtension
     * @throws IOException
     */
    public static void getHttpResponse(String urlExtension) throws IOException {

        String url = "https://art-gallery-backend.herokuapp.com"+urlExtension;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("cache-control", "no-cache")
                .addHeader("Authorization","Bearer "+Ressources.getBearerToken())
                .build();

        Response response=client.newCall(request).execute();
        Ressources.response = response;

    }

    /**
     *
     * Calls a post request on a url extension with some data. The bearer token field specifies if it is needed
     * @param urlExtension
     * @param postdata
     * @param putBearerToken
     * @throws IOException
     */
    public static void postRequest(String urlExtension, JSONObject postdata,boolean putBearerToken) throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "https://art-gallery-backend.herokuapp.com"+ urlExtension;

        OkHttpClient client = new OkHttpClient();



        RequestBody body = RequestBody.create(postdata.toString(),MEDIA_TYPE);
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
                    .addHeader("Authorization","Bearer "+Ressources.getBearerToken())
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
        }
        Response response=client.newCall(request).execute();
        Ressources.response = response;
    }

    /**
     *
     * Calls a put request on a url extension with some data. The bearer token field specifies if it is needed
     * @param urlExtension
     * @param postdata
     * @param putBearerToken
     * @throws IOException
     */
    public static void putRequest(String urlExtension, JSONObject postdata,boolean putBearerToken) throws IOException {
        System.out.println(postdata.toString());
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "https://art-gallery-backend.herokuapp.com"+ urlExtension;

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(postdata.toString(),MEDIA_TYPE);

        Request request;
        if(!putBearerToken) {
            request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
        }
        else{
            request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Authorization","Bearer "+Ressources.getBearerToken())
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
        }
        Response response=client.newCall(request).execute();
        Ressources.response = response;
    }

    /**
     * Gets the image bitmap of an artwork
     * @param artwork
     */
    public static void getImageBitmap(Artwork artwork){
        final Request request = new Request.Builder().url(artwork.getUrl()).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    artwork.setBitmap(bitmap);
                }else {

                }
            }
        });
    }
}
