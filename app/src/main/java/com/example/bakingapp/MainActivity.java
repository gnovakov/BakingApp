package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private JSONArray mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeSortQuery();
    }

    // Build URL
    void makeSortQuery() {
        URL url = NetworkUtils.buildUrl();

        //Connect to the API using the URL
        new GetMoviesTask().execute(url);
    }

    // AsyncTask
    public class GetMoviesTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL myUrl = urls[0];
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(myUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Data Returned from API as a String
            return result;

        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("WWWWWWWWWWWWWWWWWWWWWW", "WWWWWWWWWWWWWWWWWWWWWW " + mRecipes);

            try {
                JSONArray resultsArray = new JSONArray(result);
                mRecipes = resultsArray;

                //mMovieAdapter = new MovieAdapter(MainActivity.this, mMovie, MainActivity.this);


                //mRecyclerView.setAdapter(mMovieAdapter);

                Log.i("WWWWWWWWWWWWWWWWWWWWWW", "WWWWWWWWWWWWWWWWWWWWWW " + mRecipes);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }



}
