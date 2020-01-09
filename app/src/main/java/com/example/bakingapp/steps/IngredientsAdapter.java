package com.example.bakingapp.steps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;

import org.json.JSONArray;
import org.json.JSONException;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private JSONArray mIngredientsSteps;

    public IngredientsAdapter(JSONArray recipeIngredientsData) {
        mIngredientsSteps = recipeIngredientsData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ingredientsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_ingredients_list_item, parent, false);

        return new ViewHolder(ingredientsView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {

        try {
            holder.ingredientsName.setText(mIngredientsSteps.getJSONObject(position).getString("quantity") +
                    " " + mIngredientsSteps.getJSONObject(position).getString("measure")  +
                    " " + mIngredientsSteps.getJSONObject(position).getString("ingredient"));
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return mIngredientsSteps.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientsName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientsName = itemView.findViewById(R.id.ingredient_name_textView);
        }
    }
}
