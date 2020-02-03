package com.example.bakingapp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bakingapp.api.JsonRecipesApi;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.steps.recipeScreenActivity;
import com.example.bakingapp.R;
import com.example.bakingapp.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  RecipesAdapter.OnRecipeClickListener{


    private RecyclerView recipesRecyclerView;

    private List<Recipe> recipes;

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipesRecyclerView = findViewById(R.id.rv_recipes);
        recipesRecyclerView.setHasFixedSize(true);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Runs the Query to Fetch Results
        getRecipesRetro();
    }

    @Override
    public void onRecipeClick(int clickRecipePosition) {

        Log.i("ON RECIPE CLICK", "onRecipeClick: clicked " + recipes.get(clickRecipePosition).getName());

        //Set the intent to the Activity the data is to be sent to
        Intent intent = new Intent(this, recipeScreenActivity.class);

        intent.putExtra("recipeName", recipes.get(clickRecipePosition).getName());
        //intent.putExtra("recipeIngredients", recipes.get(clickRecipePosition).getIngredients());
        //intent.putExtra("recipeSteps", recipesArray.getJSONObject(clickRecipePosition).getString("steps"));

        //Log.i("ON RECIPE CLICK", "onRecipeClick: INTENT " + recipesArray.getJSONObject(clickRecipePosition).getString("ingredients"));

        //Start the Activity the Intent is set to when an item is clicked, all data added with intent.putExtra will be sent to that Activity.
        startActivity(intent);

    }

    public void getRecipesRetro() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonRecipesApi jsonRecipesApi = retrofit.create(JsonRecipesApi.class);

        Call<List<Recipe>> call = jsonRecipesApi.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                recipes = response.body();

                //recipesRecyclerView.setAdapter(new RecipesAdapter(MainActivity.this, recipes, MainActivity.this));

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(" + recipes, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
