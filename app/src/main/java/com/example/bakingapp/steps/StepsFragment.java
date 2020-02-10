package com.example.bakingapp.steps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.details.DetailActivity;
import com.example.bakingapp.details.DetailFragment;
import com.example.bakingapp.models.Ingredient;
import com.example.bakingapp.models.Step;

import java.util.ArrayList;

public class StepsFragment extends Fragment implements StepsAdapter.OnStepClickListener {

    private static final String TAG = StepsFragment.class.getSimpleName();

    RecyclerView stepsRecyclerView;
    StepsAdapter stepsAdapter;

    RecyclerView ingredientsRecyclerView;
    IngredientsAdapter ingredientsAdapter;

    private String recipeNameData;
    private ArrayList<Ingredient> recipeIngredientsData;
    private ArrayList<Step> recipeStepsData;
    private boolean mTwoPane;


    public StepsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false); // Inflate Layout
        TextView recipeName = rootView.findViewById(R.id.recipeName); // Find TextView id

        // Get Argument that was passed from activity
         recipeNameData = getArguments().getString("recipeName");
         recipeStepsData = getArguments().getParcelableArrayList("recipeSteps");
         recipeIngredientsData = getArguments().getParcelableArrayList("recipeIngredients");
         mTwoPane = getArguments().getBoolean("TWO_PANE");

        Log.d( TAG, "onCreateView: " + "recipe Name: " + recipeNameData);
        Log.d( TAG, "onCreateView: " + "recipe Steps: " + recipeStepsData);
        Log.d( TAG, "onCreateView: " + "recipe Ingredients: " + recipeIngredientsData);

        recipeName.setText(recipeNameData); // Set the recipe name

        //Step Recycler View
        stepsRv(rootView, recipeStepsData);

        //Ingredient Recycler View
        ingredientsRv(rootView, recipeIngredientsData);


        return rootView; // return rootView

    }


    //Step Recycler View
    public void stepsRv(View rootView, ArrayList<Step> recipeStepsData) {
        //Step Recycler View

        // Get a reference to recyclerView
        stepsRecyclerView = rootView.findViewById(R.id.rv_recipe_steps);
        stepsRecyclerView.setHasFixedSize(true);

        // Set layoutManger
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Create adaptor and send the data to it
        stepsAdapter = new StepsAdapter(recipeStepsData, StepsFragment.this);

        // Set adapter to the recycler view
        stepsRecyclerView.setAdapter(stepsAdapter);

    }

    //Ingredient Recycler View
    public void ingredientsRv(View rootView, ArrayList<Ingredient> recipeIngredientsData) {
        //Ingredient Recycler View

        // Get a reference to recyclerView
        ingredientsRecyclerView = rootView.findViewById(R.id.rv_recipe_ingredients);
        ingredientsRecyclerView.setHasFixedSize(true);

        // Set layoutManger
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Create adaptor and send the data to it
        ingredientsAdapter = new IngredientsAdapter(recipeIngredientsData);

        // Set adapter to the recycler view
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
    }

    @Override
    public void onStepClick(int clickStepPosition) {

        if(mTwoPane) {

            // Create a bundle to pass the data
            Bundle detailsData = new Bundle(); // Use bundle to pass data

            Log.d( TAG, "STEPS ONE");

            // Put data into bundle
            detailsData.putInt("id", recipeStepsData.get(clickStepPosition).getId());
            detailsData.putString("shortDescription", recipeStepsData.get(clickStepPosition).getShortDescription());
            detailsData.putString("description", recipeStepsData.get(clickStepPosition).getDescription());
            detailsData.putString("videoURL", recipeStepsData.get(clickStepPosition).getVideoURL());
            detailsData.putString("thumbnailURL", recipeStepsData.get(clickStepPosition).getThumbnailURL());



            if (recipeStepsData != null) {

                Fragment detailFragment = new DetailFragment(); // Get Fragment Instance

                detailFragment.setArguments(detailsData); // Set argument bundle to our fragment

                // Begin the transaction
                getFragmentManager().beginTransaction()
                        // Replace the contents of the container with the new fragment
                        .replace(R.id.recipe_details_fragment, detailFragment)
                        // Complete the changes added above
                        .commit();
            }


        } else {

            Intent intent = new Intent(getContext(), DetailActivity.class);

            intent.putExtra("id", recipeStepsData.get(clickStepPosition).getId());
            intent.putExtra("shortDescription", recipeStepsData.get(clickStepPosition).getShortDescription());
            intent.putExtra("description", recipeStepsData.get(clickStepPosition).getDescription());
            intent.putExtra("videoURL", recipeStepsData.get(clickStepPosition).getVideoURL());
            intent.putExtra("thumbnailURL", recipeStepsData.get(clickStepPosition).getThumbnailURL());


            //Start the Activity the Intent is set to when an item is clicked, all data added with intent.putExtra will be sent to that Activity.
            startActivity(intent);

        }
    }

}
