package com.example.bakingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;

import org.json.JSONArray;
import org.json.JSONException;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {


    private Context ctx;
    private JSONArray mRecipesArray;

    public RecipesAdapter(Context ct,JSONArray recipesArray) {
        ctx = ct;
        mRecipesArray = recipesArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recipesView = LayoutInflater.from(ctx)
                .inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(recipesView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            holder.recipeName.setText(mRecipesArray.getJSONObject(position).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {

        return mRecipesArray.length();

    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name_textView);

        }

    }

}
