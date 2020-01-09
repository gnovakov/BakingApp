package com.example.bakingapp.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.R;
import com.example.bakingapp.steps.RecipeStepsFragment;

public class RecipeDetailActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //Get Fragment Manager as this Activity will need it for multiple fragments.
        fragmentManager = getSupportFragmentManager();

        // Receive Intent Data from Main Activity
        Intent stepsIntent = getIntent();

        if (stepsIntent.hasExtra("recipeName")){

            String recipeName = stepsIntent.getStringExtra("recipeName");
            String recipeSteps = stepsIntent.getStringExtra("recipeSteps");
            String recipeIngredients = stepsIntent.getStringExtra("recipeIngredients");


            Log.d( "TEST", "onCreate: " + "ingredients: " + recipeIngredients);

            //Inflate Recipe Steps Fragment whilst passing data
            inflateRecipeDetailFragment(recipeName, recipeSteps, recipeIngredients);
        }
    }

    private void inflateRecipeDetailFragment(String recipeName, String recipeSteps, String recipeIngredients) {

        Log.d( "TEST", "Recipe Detail Activity: " + recipeName + " " + recipeSteps + " " + recipeIngredients);

    }

}
