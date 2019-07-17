package com.dynamo.spacexlaunch.viewmodel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.dynamo.spacexlaunch.application.SpaceXLaunchApp;

/**
 * Created by dhandk2 on 16,July,2019
 * This class registers an broadcast reciever for connectivity change
 */
public class ConnectivityLiveData extends LiveData<Boolean> {

    private Context context;

    public ConnectivityLiveData() {
       // this.context = context;
    }

    @Override
    protected void onActive() {
        super.onActive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        SpaceXLaunchApp.getInstance().registerReceiver(networkReceiver, filter);

    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if(networkReceiver!= null)
            SpaceXLaunchApp.getInstance().unregisterReceiver(networkReceiver);
    }

    private BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                NetworkInfo activeNetwork = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    switch (activeNetwork.getType()) {
                        case ConnectivityManager.TYPE_WIFI:
                            postValue(true);
                            break;
                        case ConnectivityManager.TYPE_MOBILE:
                            postValue(true);
                            break;
                    }
                } else {
                    postValue(false);
                }
            }
        }
    };

  public void setConnectivityData(boolean isConnected){
       setValue(isConnected);
  }
}


