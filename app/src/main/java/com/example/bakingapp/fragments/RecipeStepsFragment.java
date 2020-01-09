package com.example.bakingapp.fragments;

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

import com.example.bakingapp.Adapters.RecipesAdapter;
import com.example.bakingapp.Adapters.StepsAdapter;
import com.example.bakingapp.R;

import org.json.JSONArray;
import org.json.JSONException;

public class RecipeStepsFragment extends Fragment {


    public RecipeStepsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RecyclerView stepsRecyclerView;
        StepsAdapter stepsAdapter;

        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false); // Inflate Layout
        TextView recipeName = rootView.findViewById(R.id.recipeName); // Find TextView id

        // Get Argument that was passed from activity
        String recipeNameData = getArguments().getString("recipeName");
        String recipeStepsData = getArguments().getString("recipeSteps");

        try {
            JSONArray recipeStepsDataArray = new JSONArray(recipeStepsData);


            Log.d( "TEST", "onCreateView: " + "recipeIngredients: " + recipeNameData);

            recipeName.setText(recipeNameData);

            // Get a reference to recyclerView
            stepsRecyclerView = rootView.findViewById(R.id.rv_recipe_steps);
            stepsRecyclerView.setHasFixedSize(true);

            // Set layoutManger
            stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            //Create adaptor and send the data to it
            stepsAdapter = new StepsAdapter(recipeStepsDataArray);

            // Set adapter to the recycler view
            stepsRecyclerView.setAdapter(stepsAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView; // return rootView

    }


}
