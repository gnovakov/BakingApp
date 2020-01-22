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

    private View rootView;
    private String id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;
    private SimpleExoPlayer absPlayerInternal;
    private PlayerView pvMain;

    private String CONTENT_URL;


    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView stepTitle = rootView.findViewById(R.id.stepTitle); // Find TextView id
        TextView stepDescription = rootView.findViewById(R.id.stepDescription); // Find TextView id
        pvMain = rootView.findViewById(R.id.pv_main); // creating player view

        // Get Argument that was passed from activity
        id = getArguments().getString("id");
        shortDescription = getArguments().getString("shortDescription");
        description = getArguments().getString("description");
        videoURL = getArguments().getString("videoURL");
        thumbnailURL = getArguments().getString("thumbnailURL");


        // Set Title
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

        // Set Description
        stepDescription.setText(description);


        //Initialise Video Player if there is a video URL available, otherwise remove the PlayerView Element
        if (CONTENT_URL.length() == 0) {
            pvMain.setVisibility(PlayerView.GONE);
        } else {
            initialisePlayer();
        }

        Log.d( "TEST", "CONTENT_URL: " + CONTENT_URL);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d( "TEST", "onStop: " + "STOP");

        //stopPlayer();

        pausePlayer(absPlayerInternal);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d( "TEST", "onDestroyView: " + "DESTROY");
        stopPlayer();
    }

    // Player Methods //

    //Implement ExoPlayer
    private void initialisePlayer() {

        int appNameStringRes = R.string.app_name;

        TrackSelector trackSelectorDef = new DefaultTrackSelector();
        absPlayerInternal = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelectorDef); //creating a player instance

        String userAgent = Util.getUserAgent(getContext(), getContext().getString(appNameStringRes));
        DefaultDataSourceFactory defdataSourceFactory = new DefaultDataSourceFactory(getContext(),userAgent);
        Uri uriOfContentUrl = Uri.parse(CONTENT_URL);
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(defdataSourceFactory).createMediaSource(uriOfContentUrl);  // creating a media source

        absPlayerInternal.prepare(mediaSource);
        absPlayerInternal.setPlayWhenReady(true); // start loading video and play it at the moment a chunk of it is available offline

        pvMain.setPlayer(absPlayerInternal); // attach surface to the view
    }


    // Stop & Release Player when we leave the page
    private void stopPlayer() {
        if (absPlayerInternal != null) {
            absPlayerInternal.release();
        }
    }

    private void playPlayer(SimpleExoPlayer player) {
        if (player != null) {
            player.setPlayWhenReady(true);
        }
    }

    private void pausePlayer(SimpleExoPlayer player) {
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }


}
