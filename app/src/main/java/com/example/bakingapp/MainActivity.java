package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.Adapters.RecipesAdapter;
import com.example.bakingapp.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recipesRecyclerView;

    private RecipesAdapter recipesAdapter;

    private JSONArray recipesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipesRecyclerView = findViewById(R.id.rv_recipes);
        recipesRecyclerView.setHasFixedSize(true);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Runs the Query to Fetch Results
        makeSortQuery();
    }

    // Build the URL
    void makeSortQuery() {
        URL url = NetworkUtils.buildUrl();

        //Connect to the API using the URL
        new GetMoviesTask().execute(url);
    }

    // AsyncTask for building out the URL
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

            Log.i("URL BEFORE PARSING", "URL RESULTS " + recipesArray);

            try {
                JSONArray resultsArray = new JSONArray(result);
                recipesArray = resultsArray;

                recipesAdapter = new RecipesAdapter(MainActivity.this, recipesArray);


                recipesRecyclerView.setAdapter(recipesAdapter);

                Log.i("URL AFTER PARSING", "URL RESULTS " + recipesArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }



}
