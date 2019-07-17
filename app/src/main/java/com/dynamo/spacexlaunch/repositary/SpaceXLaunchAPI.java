package com.dynamo.spacexlaunch.repositary;

import com.dynamo.spacexlaunch.Util.AppConstants;
import com.dynamo.spacexlaunch.model.resource.ErrorDetail;
import com.dynamo.spacexlaunch.model.resource.SpaceXLaunchResource;
import com.dynamo.spacexlaunch.model.response.LaunchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dhandk2 on 15,July,2019
 * API Class for fetching data
 */
public class SpaceXLaunchAPI {

    private static IWebService IWEBSERVICE_INSTANCE = null;
    private static SpaceXLaunchAPI INSTANCE = null;
    private List<LaunchResponse> pastLaunches;
    private SpaceXLaunchResource spaceXLaunchResource;
    private SpaceXLaunchAPI() {
        if (IWEBSERVICE_INSTANCE == null)
            IWEBSERVICE_INSTANCE = RetrofitClient.getInstance().create(IWebService.class);
    }

    public static SpaceXLaunchAPI getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpaceXLaunchAPI();
        }
        return INSTANCE;
    }

    public SpaceXLaunchResource fetchPastLaunchAPI() {
        // spaceXLaunchResourcep = (new SpaceXLaunchResource(SpaceXLaunchResource.STATUS_LOADING,null,null));

       pastLaunches = new ArrayList<>();
        Call<List<LaunchResponse>> spaceXRequest = IWEBSERVICE_INSTANCE.fetchPastLaunches();
        try {
            Response<List<LaunchResponse>> spaceXResponse = spaceXRequest.execute();
            if (spaceXResponse.isSuccessful()) {
                if (spaceXResponse.code() == 200 && spaceXResponse.body() != null && spaceXResponse.body().size() > 0) {
                    pastLaunches = spaceXResponse.body();
                     spaceXLaunchResource = new SpaceXLaunchResource(SpaceXLaunchResource.STATUS_SUCCESS,pastLaunches,null);
                }
            }else
            {
                ErrorDetail errorDetail = new ErrorDetail(spaceXResponse.code(), AppConstants.Error.API_ERROR_MSG);
                spaceXLaunchResource = new SpaceXLaunchResource(SpaceXLaunchResource.STATUS_FAIL,null,errorDetail);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return spaceXLaunchResource;
    }

}
