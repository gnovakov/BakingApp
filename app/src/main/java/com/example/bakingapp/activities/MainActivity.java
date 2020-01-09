package com.example.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.Adapters.RecipesAdapter;
import com.example.bakingapp.R;
import com.example.bakingapp.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements  RecipesAdapter.OnRecipeClickListener{


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

    @Override
    public void onRecipeClick(int clickRecipePosition) {

        try {
            Log.i("ON RECIPE CLICK", "onRecipeClick: clicked " + recipesArray.getJSONObject(clickRecipePosition).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Set the intent to the Activity the data is to be sent to
        Intent intent = new Intent(this, RecipeStepsAndDetailActivity.class);

        try {

            intent.putExtra("recipeName", recipesArray.getJSONObject(clickRecipePosition).getString("name"));
            intent.putExtra("recipeIngredients", recipesArray.getJSONObject(clickRecipePosition).getString("ingredients"));
            intent.putExtra("recipeSteps", recipesArray.getJSONObject(clickRecipePosition).getString("steps"));

            Log.i("ON RECIPE CLICK", "onRecipeClick: INTENT " + recipesArray.getJSONObject(clickRecipePosition).getString("ingredients"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Start the Activity the Intent is set to when an item is clicked, all data added with intent.putExtra will be sent to that Activity.
        startActivity(intent);

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

                recipesAdapter = new RecipesAdapter(MainActivity.this, recipesArray, MainActivity.this);


                recipesRecyclerView.setAdapter(recipesAdapter);

                Log.i("URL AFTER PARSING", "URL RESULTS " + recipesArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }



}
