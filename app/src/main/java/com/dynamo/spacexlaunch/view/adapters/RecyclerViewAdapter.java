package com.dynamo.spacexlaunch.view.adapters;

/**
 * Created by dhandk2 on 16,July,2019
 */

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dynamo.spacexlaunch.R;
import com.dynamo.spacexlaunch.Util.AppConstants;
import com.dynamo.spacexlaunch.Util.Utils;
import com.dynamo.spacexlaunch.model.response.LaunchResponse;
import com.dynamo.spacexlaunch.view.YoutubePlayerActivity;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<LaunchResponse> launchResponseList;
    private String missionNameText = null;
    private String missionDateText = null;

    public RecyclerViewAdapter(Context context, List<LaunchResponse> launchResponseList) {
        this.context = context;
        this.launchResponseList = launchResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.launch_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        if (launchResponseList.get(position).getMissionName() != null &&
                launchResponseList.get(position).getMissionName().length() > 0) {
            viewHolder.missionName.setText(context.getResources().getString(R.string.mission)+launchResponseList.get(position).getMissionName());
        }
        if (launchResponseList.get(position).getLaunchDateUtc() != null &&
                launchResponseList.get(position).getLaunchDateUtc().length() > 0) {
            viewHolder.missionDate.setText(Utils.converttoAppDateFormat(launchResponseList.get(position).getLaunchDateUtc()));
        }

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(context, "Selecetd Item :" + launchResponseList.get(position).getMissionName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), YoutubePlayerActivity.class);
                if (launchResponseList.get(position).getRocket() != null) {
                    if (launchResponseList.get(position).getRocket().getRocketName() != null &&
                            launchResponseList.get(position).getRocket().getRocketName().length() > 0) {
                        intent.putExtra(AppConstants.PlayerIntent.ROCKET_NAME, launchResponseList.get(position).getRocket().getRocketName());
                    }
                }
                if(launchResponseList.get(position).getDetails()!=null &&
                        launchResponseList.get(position).getDetails().length() > 0)
                {
                    intent.putExtra(AppConstants.PlayerIntent.DETAILS, launchResponseList.get(position).getDetails());
                }
                if(viewHolder.missionDate.getText() != null && viewHolder.missionDate.getText().length() > 0)
                {
                    intent.putExtra(AppConstants.PlayerIntent.LAUNCH_DATE, viewHolder.missionDate.getText() );
                }
                if(launchResponseList.get(position).getLinks() != null &&
                       launchResponseList.get(position).getLinks().getVideoLink() != null &&
                        launchResponseList.get(position).getLinks().getVideoLink().length() > 0){
                        intent.putExtra(AppConstants.PlayerIntent.VIDEO_LINK, launchResponseList.get(position).getLinks().getVideoLink());
                }
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return launchResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView missionName;
        TextView missionDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.ll_layout);
            missionName = itemView.findViewById(R.id.tv_mission_name);
            missionDate = itemView.findViewById(R.id.tv_launch_date);
        }
    }
}
