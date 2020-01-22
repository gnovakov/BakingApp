package com.example.bakingapp.steps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.R;

public class recipeScreenActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);

        //Get Fragment Manager as this Activity will need it for multiple fragments.
        fragmentManager = getSupportFragmentManager();

        // Receive Intent Data from Main Activity
        Intent recipeIntent = getIntent();

        if (recipeIntent.hasExtra("recipeName")){

            String recipeName = recipeIntent.getStringExtra("recipeName");
            String recipeSteps = recipeIntent.getStringExtra("recipeSteps");
            String recipeIngredients = recipeIntent.getStringExtra("recipeIngredients");

            Log.d( "TEST", "onCreate: " + "ingredients: " + recipeIngredients);


            if (findViewById(R.id.recipe_steps_fragment) != null) {
                mTwoPane = true;

                Log.d( "TEST", "mTwoPane True: " + mTwoPane);

                //Inflate Recipe Steps Fragment whilst passing data
                inflateRecipeStepsFragment(recipeName, recipeSteps, recipeIngredients);

                //Inflate Details Fragment
                inflateRecipeDetailFragment(recipeName, recipeSteps, recipeIngredients);

            } else {
                mTwoPane = false;
                Log.d( "TEST", "mTwoPane False: " + mTwoPane);

                //Inflate Recipe Steps Fragment whilst passing data
                inflateRecipeStepsFragment(recipeName, recipeSteps, recipeIngredients);
            }
        }


    }


    private void inflateRecipeStepsFragment(String recipeName, String recipeSteps, String recipeIngredients) {


        Fragment stepsFragment = new StepsFragment(); // Get Fragment Instance
        Bundle data = new Bundle(); // Use bundle to pass data

        Log.d( "TEST", "STEPS");

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


    private void inflateRecipeDetailFragment(String recipeName, String recipeSteps, String recipeIngredients) {


        Fragment stepsFragment = new StepsFragment(); // Get Fragment Instance
        Bundle data = new Bundle(); // Use bundle to pass data

        Log.d( "TEST", "DETAILS");

        // Put data into bundle
        data.putString("recipeName", recipeName);
        data.putString("recipeSteps", recipeSteps);
        data.putString("recipeIngredients", recipeIngredients);

        stepsFragment.setArguments(data); // Set argument bundle to our fragment

        // Begin the transaction
        fragmentManager.beginTransaction()
                // Replace the contents of the container with the new fragment
                .add(R.id.recipe_details_fragment, stepsFragment)
                // Complete the changes added above
                .commit();

    }




}
