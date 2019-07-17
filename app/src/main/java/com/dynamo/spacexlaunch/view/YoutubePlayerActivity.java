package com.dynamo.spacexlaunch.view;

import android.os.Bundle;

import com.dynamo.spacexlaunch.BuildConfig;
import com.dynamo.spacexlaunch.Util.AppConstants;
import com.dynamo.spacexlaunch.Util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.dynamo.spacexlaunch.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/*
 This activity displays the Past Launch Video and other details based on Recycler View Selection
 */
public class YoutubePlayerActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private String rocketName = null;
    private String details = null;
    private String date = null;
    private String videoLink = null;
    private TextView tvRocketName;
    private TextView tvLaunchDetails;
    private TextView tvLaunchDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_player_activity);
        youTubePlayerView = findViewById(R.id.youtube_view);

        if(getIntent().getExtras() != null){
            rocketName = getIntent().getStringExtra(AppConstants.PlayerIntent.ROCKET_NAME);
            details = getIntent().getStringExtra(AppConstants.PlayerIntent.DETAILS);
            date = getIntent().getStringExtra(AppConstants.PlayerIntent.LAUNCH_DATE);
            videoLink = getIntent().getStringExtra(AppConstants.PlayerIntent.VIDEO_LINK);
        }

        tvRocketName = findViewById(R.id.tv_rocketName);
        tvLaunchDetails = findViewById(R.id.tv_launch_details);
        tvLaunchDate = findViewById(R.id.tv_launch_date);

        if(rocketName != null && rocketName.length() > 0)
            tvRocketName.setText(rocketName);
        if(details != null && details.length() > 0 )
            tvLaunchDetails.setText(details);
        if(date != null && date.length() > 0)
            tvLaunchDate.setText(date);


        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(videoLink != null && videoLink.length() > 0) {
                    String videoParam = Utils.getVideoParameter(videoLink);
                    if(videoParam != null) {
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                        youTubePlayer.loadVideo(videoParam);
                    }
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize(BuildConfig.YOUTUBE_API_KEY,onInitializedListener);
    }


}
