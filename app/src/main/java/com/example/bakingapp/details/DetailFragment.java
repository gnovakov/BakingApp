package com.example.bakingapp.details;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bakingapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

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

        SimpleExoPlayer absPlayerInternal;
        PlayerView pvMain;

        String CONTENT_URL;

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

        // Set Video URL
        if (videoURL.length() == 0) {
            CONTENT_URL = thumbnailURL;
        } else {
            CONTENT_URL = videoURL;
        }

        stepDescription.setText(description);

        //Implement ExoPlayer
        int appNameStringRes = R.string.app_name;

        pvMain = findViewById(R.id.pv_main); // creating player view



        return rootView;
    }

}
