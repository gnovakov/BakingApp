package com.example.bakingapp.steps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.details.DetailFragment;
import com.example.bakingapp.models.Ingredient;
import com.example.bakingapp.models.Recipe;

import java.util.ArrayList;

public class recipeScreenActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private String recipeName;
    private String recipeSteps;
    private ArrayList<Ingredient> recipeIngredients;
    private String TWO_PANE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);

        if (findViewById(R.id.recipe_details_fragment) != null) {
            mTwoPane = true; //Inflate steps Fragment & Details Fragment
            Log.d( "TEST", "mTwoPane True: " + mTwoPane);

        } else {
            mTwoPane = false; //Only inflate steps Fragment
            Log.d( "TEST", "mTwoPane False: " + mTwoPane);
        }

        // Receive Intent Data from Main Activity
        Intent recipeIntent = getIntent();

        if (recipeIntent.hasExtra("recipeName")){

            recipeName = recipeIntent.getStringExtra("recipeName");
            recipeIngredients = recipeIntent.getParcelableArrayListExtra("recipeIngredients");
            //recipeSteps = recipeIntent.getStringExtra("recipeSteps");
            Log.d( "TEST", "onCreate: " + "recipeName: " + recipeName);
            Log.d( "TEST", "onCreate: " + "ingredients: " + recipeIngredients);
        }


        //Inflate Recipe Step Fragment whilst passing data
        if (savedInstanceState == null) {

            inflateRecipeStepsFragment();
        }

    }



    private void inflateRecipeStepsFragment() {

        // Create a bundle to pass the data
        Bundle data = new Bundle(); // Use bundle to pass data

        Log.d( "TEST", "STEPS ONE");

        // Put data into bundle
        data.putString("recipeName", recipeName);
        //data.putString("recipeSteps", recipeSteps);
        //data.putString("recipeIngredients", recipeIngredients);
        //data.putBoolean("TWO_PANE", mTwoPane);

        if (recipeName != null) {
            Fragment stepsFragment = new StepsFragment(); // Get Fragment Instance

            stepsFragment.setArguments(data); // Set argument bundle to our fragment

            // Begin the transaction
            getSupportFragmentManager().beginTransaction()
                    // Replace the contents of the container with the new fragment
                    .add(R.id.recipe_steps_fragment, stepsFragment)
                    // Complete the changes added above
                    .commit();
        } else {

            Toast.makeText(this, "Unable To Launch Fragment Transaction", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

}
