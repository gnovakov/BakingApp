package com.example.bakingapp.steps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.R;

public class StepsAndDetailActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_and_detail);

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


        Fragment stepsFragment = new StepsFragment(); // Get Fragment Instance
        Bundle data = new Bundle(); // Use bundle to pass data

        // Put data into bundle
        data.putString("recipeName", recipeName);
        data.putString("recipeSteps", recipeSteps);
        data.putString("recipeIngredients", recipeIngredients);

        stepsFragment.setArguments(data); // Set argument bundle to our fragment

        // Begin the transaction
        fragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        .add(R.id.recipe_steps_fragment, stepsFragment)
        // Complete the changes added above
        .commit();

    }


    private void inflateRecipeDetailFragment() {

    }




}
