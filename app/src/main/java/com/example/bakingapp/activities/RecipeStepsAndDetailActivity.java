package com.example.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.R;
import com.example.bakingapp.fragments.RecipeStepsFragment;

public class RecipeStepsAndDetailActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps_and_detail);

        //Get Fragment Manager as this Activity will need it for multiple fragments.
        fragmentManager = getSupportFragmentManager();

        // Receive Intent Data from Main Activity
        Intent recipeIntent = getIntent();

        if (recipeIntent.hasExtra("recipeName")){

            String recipeName = recipeIntent.getStringExtra("recipeName");
            String recipeSteps = recipeIntent.getStringExtra("recipeSteps");
            String recipeIngredients = recipeIntent.getStringExtra("recipeIngredients");


            Log.d( "TEST", "onCreate: " + "ingredients: " + recipeIngredients);

            //Inflate Recipe Steps Fragment whilst passing data
            inflateRecipeStepsFragment(recipeName, recipeSteps, recipeIngredients);
        }


    }


    private void inflateRecipeStepsFragment(String recipeName, String recipeSteps, String recipeIngredients) {


        Fragment recipeStepsFragment = new RecipeStepsFragment(); // Get Fragment Instance
        Bundle data = new Bundle(); // Use bundle to pass data

        // Put data into bundle
        data.putString("recipeName", recipeName);
        data.putString("recipeSteps", recipeSteps);

        recipeStepsFragment.setArguments(data); // Set argument bundle to our fragment

        // Begin the transaction
        fragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        .add(R.id.recipe_steps_fragment, recipeStepsFragment)
        // Complete the changes added above
        .commit();

    }


    private void inflateRecipeDetailFragment() {

    }




}
