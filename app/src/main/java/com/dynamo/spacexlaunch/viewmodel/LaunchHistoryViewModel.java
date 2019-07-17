package com.dynamo.spacexlaunch.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dynamo.spacexlaunch.model.resource.SpaceXLaunchResource;
import com.dynamo.spacexlaunch.model.response.LaunchResponse;
import com.dynamo.spacexlaunch.repositary.SpaceXLaunchAPI;

import java.util.List;

/**
 * Created by dhandk2 on 15,July,2019
 */
public class LaunchHistoryViewModel extends ViewModel {

    MutableLiveData<SpaceXLaunchResource> spaceXLaunchResourceMutableLiveData = new MutableLiveData<SpaceXLaunchResource>();
    MutableLiveData<List<LaunchResponse>> pastLaunches = new MutableLiveData<>();
    SpaceXLaunchAPI spaceXLaunchAPI = SpaceXLaunchAPI.getInstance();

    public void loadDataFromWorker(LifecycleOwner lifecycleOwner) {
        spaceXLaunchResourceMutableLiveData.postValue(spaceXLaunchAPI.fetchPastLaunchAPI());
    }

    public LiveData<SpaceXLaunchResource> getSpaceXLaunchResourceLiveData() {
        return spaceXLaunchResourceMutableLiveData;
    }

    public LiveData<List<LaunchResponse>> getPastLaunchesList() {
        return pastLaunches;
    }

    public void setPastLaunchesList(List<LaunchResponse> data) {
         pastLaunches.setValue(data);
    }

}
