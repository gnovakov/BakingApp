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

import org.json.JSONArray;
import org.json.JSONException;

public class StepsFragment extends Fragment implements StepsAdapter.OnStepClickListener {

    RecyclerView stepsRecyclerView;
    StepsAdapter stepsAdapter;

    RecyclerView ingredientsRecyclerView;
    IngredientsAdapter ingredientsAdapter;

    private String recipeNameData;
    private String recipeStepsData;
    private String recipeIngredientsData;

    private JSONArray recipeStepsDataArray;


    public StepsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false); // Inflate Layout
        TextView recipeName = rootView.findViewById(R.id.recipeName); // Find TextView id

        // Get Argument that was passed from activity
         recipeNameData = getArguments().getString("recipeName");
         recipeStepsData = getArguments().getString("recipeSteps");
         recipeIngredientsData = getArguments().getString("recipeIngredients");

        try {
            recipeStepsDataArray = new JSONArray(recipeStepsData);
            JSONArray recipeIngredientsDataArray = new JSONArray(recipeIngredientsData);


            Log.d( "TEST", "TEST: " + "recipeIngredients: " + recipeIngredientsData);

            recipeName.setText(recipeNameData);

            //Steps Recycler View
            stepsRv(rootView, recipeStepsDataArray);

            //Ingredients Recycler View
            ingredientsRv(rootView, recipeIngredientsDataArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView; // return rootView

    }


    //Steps Recycler View
    public void stepsRv(View rootView, JSONArray recipeStepsDataArray) {
        //Steps Recycler View

        // Get a reference to recyclerView
        stepsRecyclerView = rootView.findViewById(R.id.rv_recipe_steps);
        stepsRecyclerView.setHasFixedSize(true);

        // Set layoutManger
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Create adaptor and send the data to it
        stepsAdapter = new StepsAdapter(recipeStepsDataArray, StepsFragment.this);

        // Set adapter to the recycler view
        stepsRecyclerView.setAdapter(stepsAdapter);

    }

    //Ingredients Recycler View
    public void ingredientsRv(View rootView, JSONArray recipeIngredientsDataArray) {
        //Ingredients Recycler View

        // Get a reference to recyclerView
        ingredientsRecyclerView = rootView.findViewById(R.id.rv_recipe_ingredients);
        ingredientsRecyclerView.setHasFixedSize(true);

        // Set layoutManger
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Create adaptor and send the data to it
        ingredientsAdapter = new IngredientsAdapter(recipeIngredientsDataArray);

        // Set adapter to the recycler view
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
    }

    @Override
    public void onStepClick(int clickStepPosition) {

        Intent intent = new Intent(getContext(), DetailActivity.class);

        try {
            intent.putExtra("id", recipeStepsDataArray.getJSONObject(clickStepPosition).getString("id"));
            intent.putExtra("shortDescription", recipeStepsDataArray.getJSONObject(clickStepPosition).getString("shortDescription"));
            intent.putExtra("description", recipeStepsDataArray.getJSONObject(clickStepPosition).getString("description"));
            intent.putExtra("videoURL", recipeStepsDataArray.getJSONObject(clickStepPosition).getString("videoURL"));
            intent.putExtra("thumbnailURL", recipeStepsDataArray.getJSONObject(clickStepPosition).getString("thumbnailURL"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Start the Activity the Intent is set to when an item is clicked, all data added with intent.putExtra will be sent to that Activity.
        startActivity(intent);

    }

}
