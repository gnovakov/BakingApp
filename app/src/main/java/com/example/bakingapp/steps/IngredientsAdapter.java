package com.example.bakingapp.steps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.main.RecipesAdapter;
import com.example.bakingapp.models.Ingredient;
import com.example.bakingapp.models.Step;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private static final String TAG = IngredientsAdapter.class.getSimpleName();
    private  ArrayList<Ingredient> mIngredientsSteps;

    public IngredientsAdapter( ArrayList<Ingredient> recipeIngredientsData) {
        mIngredientsSteps = recipeIngredientsData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ingredientsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_list_item, parent, false);

        return new ViewHolder(ingredientsView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {

            holder.ingredientsName.setText(mIngredientsSteps.get(position).getQuantity() +
                    " " + mIngredientsSteps.get(position).getMeasure()  +
                    " " + mIngredientsSteps.get(position).getIngredient());




    }

    @Override
    public int getItemCount() {
        return mIngredientsSteps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientsName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientsName = itemView.findViewById(R.id.ingredient_name_textView);
        }
    }
}
