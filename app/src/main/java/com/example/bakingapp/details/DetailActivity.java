package com.example.bakingapp.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.R;

public class DetailActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Get Fragment Manager as this Activity will need it for multiple fragments.
        fragmentManager = getSupportFragmentManager();

        // Receive Intent Data from Main Activity
        Intent stepsIntent = getIntent();

        if (stepsIntent.hasExtra("shortDescription")){

            String id = stepsIntent.getStringExtra("id");
            String shortDescription = stepsIntent.getStringExtra("shortDescription");
            String description = stepsIntent.getStringExtra("description");
            String videoURL = stepsIntent.getStringExtra("videoURL");
            String thumbnailURL = stepsIntent.getStringExtra("thumbnailURL");




            Log.d( "TEST", "onCreate: " + "ID: " + id);

            //Inflate Recipe Steps Fragment whilst passing data
            inflateRecipeDetailFragment( id, shortDescription, description, videoURL, thumbnailURL);
        }
    }

    private void inflateRecipeDetailFragment(String id, String shortDescription, String description, String videoURL, String thumbnailURL) {

        Log.d( "TEST", "Recipe Detail Activity: " + description);
        Log.d( "TEST", "Recipe Detail Activity: " + videoURL);
        Log.d( "TEST", "Recipe Detail Activity: " + thumbnailURL);


        Fragment detailFragment = new DetailFragment(); // Get Fragment Instance
        Bundle data = new Bundle(); // Use bundle to pass data

        // Put data into bundle
        data.putString("id", id);
        data.putString("shortDescription", shortDescription);
        data.putString("description", description);
        data.putString("videoURL", videoURL);
        data.putString("thumbnailURL", thumbnailURL);


        detailFragment.setArguments(data); // Set argument bundle to our fragment

        // Begin the transaction
        fragmentManager.beginTransaction()
                // Replace the contents of the container with the new fragment
                .add(R.id.recipe_detail_fragment, detailFragment)
                // Complete the changes added above
                .commit();

    }

}
