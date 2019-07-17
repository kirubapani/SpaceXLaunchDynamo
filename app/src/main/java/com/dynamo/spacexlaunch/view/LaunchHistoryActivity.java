package com.dynamo.spacexlaunch.view;

import android.app.Application;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dynamo.spacexlaunch.R;
import com.dynamo.spacexlaunch.Util.AppConstants;
import com.dynamo.spacexlaunch.Util.Utils;
import com.dynamo.spacexlaunch.application.SpaceXLaunchApp;
import com.dynamo.spacexlaunch.model.resource.ErrorDetail;
import com.dynamo.spacexlaunch.model.resource.SpaceXLaunchResource;
import com.dynamo.spacexlaunch.model.response.LaunchResponse;
import com.dynamo.spacexlaunch.view.adapters.RecyclerViewAdapter;
import com.dynamo.spacexlaunch.viewmodel.ConnectivityLiveData;
import com.dynamo.spacexlaunch.viewmodel.LaunchHistoryViewModel;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LaunchHistoryActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter = null;
    private LaunchHistoryViewModel launchHistoryViewModel;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private Application context;
    private List<LaunchResponse> pastList;
    private ConnectivityLiveData connectivityLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.app_name));
        setDisplayHomeEnabled(false);

        context = SpaceXLaunchApp.getInstance();
        showProgressDialog("Space X Launching");

        // Initiating Recycler View
        recyclerView = findViewById(R.id.rv_pastLaunchlist);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        connectivityLiveData = new ConnectivityLiveData();
        launchHistoryViewModel = ViewModelProviders.of(this).get(LaunchHistoryViewModel.class);

        //This Live data subclass gets called whenever there is CoNNECTION CHANGE
        connectivityLiveData.setConnectivityData(Utils.isNetworkAvailable(context));
        connectivityLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isConnected) {
                if (isConnected) {
                    scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
                    scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            launchHistoryViewModel.loadDataFromWorker(LaunchHistoryActivity.this);
                        }
                    }, 0, 5, TimeUnit.MINUTES);

                } else {
                    hideProgressDialog();
                    ErrorDetail errorDetail = new ErrorDetail(0, AppConstants.Error.CONNECTIVITY_ERR_MSG);
                    showErrorDialog(errorDetail);
                    if (scheduledThreadPoolExecutor != null) {
                        scheduledThreadPoolExecutor.shutdown();
                        scheduledThreadPoolExecutor = null;
                    }
                }

            }
        });

        /*
           This observer is for getting the Status, if success data or error message from view model
         */
        launchHistoryViewModel.getSpaceXLaunchResourceLiveData().observe(this, new Observer<SpaceXLaunchResource>() {
            @Override
            public void onChanged(SpaceXLaunchResource spaceXLaunchResource) {
                if (spaceXLaunchResource != null) {
                    switch (spaceXLaunchResource.getStatus()) {
                        case SpaceXLaunchResource.STATUS_SUCCESS:
                            hideProgressDialog();
                            dismissAlertDialog();
                            if (spaceXLaunchResource.getLaunchResponses() != null &&
                                    spaceXLaunchResource.getLaunchResponses().size() > 0) {
                                launchHistoryViewModel.setPastLaunchesList(spaceXLaunchResource.getLaunchResponses());
                            }
                            break;
                        case SpaceXLaunchResource.STATUS_FAIL:
                            hideProgressDialog();
                            showErrorDialog(spaceXLaunchResource.getErrorDetail());
                            break;
                    }
                }
            }
        });

        /*
         This observer is to load the Recycler View Adapter.
         */
        launchHistoryViewModel.getPastLaunchesList().observe(this, new Observer<List<LaunchResponse>>() {
            @Override
            public void onChanged(List<LaunchResponse> launchResponses) {
                if (recyclerViewAdapter == null && launchResponses != null &&
                        launchResponses.size() > 0) {
                    recyclerViewAdapter = new RecyclerViewAdapter(LaunchHistoryActivity.this, launchHistoryViewModel.getPastLaunchesList().getValue());
                    recyclerView.setAdapter(recyclerViewAdapter);
                } else {
                    recyclerViewAdapter.notifyDataSetChanged();
                }

            }
        });


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (scheduledThreadPoolExecutor != null)
            scheduledThreadPoolExecutor.shutdown();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
