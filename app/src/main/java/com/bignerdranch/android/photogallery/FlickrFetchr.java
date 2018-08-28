package com.bignerdranch.android.photogallery;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrFetchr {

    private static final String TAG = "FlickrFetchr";

    private static final String API_KEY = "4d982737188c770715ebaa0b9b6af86d";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems() {

        List<GalleryItem> items = new ArrayList<>();

        try {
            String url = Uri.parse("https://api.flickr.com/services/rest/")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            Gson gson = new Gson();
            Wrapper wrapper = gson.fromJson(jsonString, Wrapper.class);
            parseItems(items, wrapper);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }

        return items;
    }

    public void parseItems(List<GalleryItem> items, Wrapper wrapper) {
        for (Wrapper.Photo photo : wrapper.photos.photo) {
            GalleryItem item = new GalleryItem();
            item.setId(photo.id);
            item.setCaption(photo.title);

            if (photo.url_s == null) {
                continue;
            }

            item.setUrl(photo.url_s);
            items.add(item);
        }
        Log.i(TAG, "items.size(): " + items.size());
    }

    class Wrapper {

        Photos photos;

        class Photos {
            Photo[] photo;
        }

        class Photo {
            String id;
            String title;
            String url_s;
        }
    }
}
