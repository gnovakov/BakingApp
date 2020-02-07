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
import com.example.bakingapp.main.MainActivity;
import com.example.bakingapp.models.Ingredient;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.models.Step;

import java.util.ArrayList;

public class recipeScreenActivity extends AppCompatActivity {

    private static final String TAG = recipeScreenActivity.class.getSimpleName();

    private boolean mTwoPane;
    private String recipeName;
    private ArrayList<Ingredient> recipeIngredients;
    private ArrayList<Step> recipeSteps;

    public static final String TWO_PANE = "twoPaneArgs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);

        if (findViewById(R.id.recipe_details_fragment) != null) {
            mTwoPane = true; //Inflate steps Fragment & Details Fragment
            Log.d( TAG, "mTwoPane True: " + mTwoPane);

        } else {
            mTwoPane = false; //Only inflate steps Fragment
            Log.d( TAG, "mTwoPane False: " + mTwoPane);
        }

        // Receive Intent Data from Main Activity
        Intent recipeIntent = getIntent();

        if (recipeIntent.hasExtra("recipeName")){

            recipeName = recipeIntent.getStringExtra("recipeName");
            recipeIngredients = recipeIntent.getParcelableArrayListExtra("recipeIngredients");
            recipeSteps = recipeIntent.getParcelableArrayListExtra("recipeSteps");

            Log.d( TAG, "onCreate: " + "recipe Name: " + recipeName);
            Log.d( TAG, "onCreate: " + "recipe Ingredients: " + recipeIngredients);
            Log.d( TAG, "onCreate: " + "recipe Steps: " + recipeSteps);
        }


        //Inflate Recipe Step Fragment whilst passing data
        if (savedInstanceState == null) {

            inflateRecipeStepsFragment();
        }

    }



    private void inflateRecipeStepsFragment() {

        // Create a bundle to pass the data
        Bundle data = new Bundle(); // Use bundle to pass data

        // Put data into bundle
        data.putString("recipeName", recipeName);
        data.putParcelableArrayList("recipeSteps", recipeSteps);
        data.putParcelableArrayList("recipeIngredients", recipeIngredients);
        data.putBoolean("TWO_PANE", mTwoPane);

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
