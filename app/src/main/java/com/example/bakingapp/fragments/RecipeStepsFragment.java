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

import com.example.bakingapp.R;

public class RecipeStepsFragment extends Fragment {


    public RecipeStepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false); // Inflate Layout
        TextView recipeName = rootView.findViewById(R.id.recipeName); // Find TextView id

        // Get Argument that was passed from activity
        String recipeNameData = getArguments().getString("recipeName");
        String recipeStepsData = getArguments().getString("recipeSteps");
        String recipeIngredientsData = getArguments().getString("recipeIngredients");

        Log.d( "TEST", "onCreateView: " + "recipeIngredients: " + recipeIngredientsData);

        recipeName.setText(recipeNameData);

        return rootView; // return rootView

    }


}
