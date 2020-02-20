package com.gnova.bakingapp.steps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gnova.bakingapp.R;
import com.gnova.bakingapp.models.Step;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private static final String TAG = StepsAdapter.class.getSimpleName();

    private  ArrayList<Step> mRecipeSteps;

    private OnStepClickListener mOnStepClickListener;

    public interface OnStepClickListener {
        void onStepClick(int clickStepPosition);
    }

    public StepsAdapter(ArrayList<Step> recipeSteps, OnStepClickListener listener) {
        mRecipeSteps = recipeSteps;
        mOnStepClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stepsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_list_item, parent, false);

        return new ViewHolder(stepsView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.stepName.setText(mRecipeSteps.get(position).getShortDescription());
    }


    @Override
    public int getItemCount() {

        return mRecipeSteps.size();
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
