package com.example.bakingapp.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bakingapp.R;

public class DetailFragment extends Fragment {

    private String id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;



    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        TextView stepTitle = rootView.findViewById(R.id.stepTitle); // Find TextView id
        TextView stepDescription = rootView.findViewById(R.id.stepDescription); // Find TextView id

        // Get Argument that was passed from activity
        id = getArguments().getString("id");
        shortDescription = getArguments().getString("shortDescription");
        description = getArguments().getString("description");
        videoURL = getArguments().getString("videoURL");
        thumbnailURL = getArguments().getString("thumbnailURL");

        if ("0".equals(id)) {
            stepTitle.setText(shortDescription);
        } else {
            stepTitle.setText("Step " + id + ": " + shortDescription);
        }

        stepDescription.setText(description);

        return rootView;
    }

}
