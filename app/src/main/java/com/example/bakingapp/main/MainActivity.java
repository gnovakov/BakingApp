package com.example.bakingapp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Printer;
import android.widget.Toast;

import com.example.bakingapp.api.JsonRecipesApi;
import com.example.bakingapp.models.Ingredient;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.steps.recipeScreenActivity;
import com.example.bakingapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  RecipesAdapter.OnRecipeClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private ArrayList<Recipe> recipes;

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipesRVLoad();
    }

    private void recipesRVLoad() {
        recipesRecyclerView = findViewById(R.id.rv_recipes);
        recipesRecyclerView.setHasFixedSize(true);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Runs the Query to Fetch Results
        loadJSON();
    }

    @Override
    public void onRecipeClick(int clickRecipePosition) {

        Log.d(TAG, "onRecipeClick: clicked Name: " + recipes.get(clickRecipePosition).getName());
        Log.d(TAG, "onRecipeClick: clicked Ingredients: " + recipes.get(clickRecipePosition).getIngredients());
        Log.d(TAG, "onRecipeClick: clicked Steps: " + recipes.get(clickRecipePosition).getSteps());

        //Set the intent to the Activity the data is to be sent to
        Intent intent = new Intent(this, recipeScreenActivity.class);


        intent.putExtra("recipeName", recipes.get(clickRecipePosition).getName());
        intent.putParcelableArrayListExtra("recipeIngredients", recipes.get(clickRecipePosition).getIngredients());
        intent.putParcelableArrayListExtra("recipeSteps", recipes.get(clickRecipePosition).getSteps());


        //Start the Activity the Intent is set to when an item is clicked, all data added with intent.putExtra will be sent to that Activity.
        startActivity(intent);

    }


    public void loadJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonRecipesApi jsonRecipesApi = retrofit.create(JsonRecipesApi.class);
        Call<ArrayList<Recipe>> call = jsonRecipesApi.getRecipes();

        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {


                recipes = response.body();

                recipesRecyclerView.setAdapter(new RecipesAdapter(MainActivity.this, recipes, MainActivity.this));


            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();

                Log.d( TAG, "Retrofit Failure: " + t);

            }
        });

    }



}
