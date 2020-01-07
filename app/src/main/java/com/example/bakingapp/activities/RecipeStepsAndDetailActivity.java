package com.example.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import com.example.bakingapp.R;
import com.example.bakingapp.fragments.RecipeDetailsFragment;

import org.json.JSONArray;

public class RecipeStepsAndDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps_and_detail);

        // Receive Intent Data from Main Activity
        Intent recipeIntent = getIntent();

        if (recipeIntent.hasExtra("recipeName")){

            String recipeName = recipeIntent.getStringExtra("recipeName");
            String recipeIngredients = recipeIntent.getStringExtra("recipeIngredients");
            String recipeSteps = recipeIntent.getStringExtra("recipeSteps");


            Log.d( "TEST", "onCreate: " + "ingredients: " + recipeIngredients);

        }

        inflateRecipeStepsAndDetailFragment();


    }

    private void inflateRecipeStepsFragment() {

    }

    private void inflateRecipeStepsAndDetailFragment() {


    }


}
