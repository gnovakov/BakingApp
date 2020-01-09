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

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private JSONArray mRecipeSteps;

    private OnStepClickListener mOnStepClickListener;

    public interface OnStepClickListener {
        void onStepClick(int clickStepPosition);
    }

    public StepsAdapter(JSONArray recipeSteps, OnStepClickListener listener) {
        mRecipeSteps = recipeSteps;
        mOnStepClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stepsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_steps_list_item, parent, false);

        return new ViewHolder(stepsView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            holder.stepName.setText(mRecipeSteps.getJSONObject(position).getString("shortDescription"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {

        return mRecipeSteps.length();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stepName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepName = itemView.findViewById(R.id.steps_name_textView);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            mOnStepClickListener.onStepClick(getAdapterPosition());

        }
    }
}
