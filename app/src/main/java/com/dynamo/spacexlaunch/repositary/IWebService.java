package com.dynamo.spacexlaunch.repositary;

import com.dynamo.spacexlaunch.model.response.LaunchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dhandk2 on 15,July,2019
 * Retrofit Interface
 */
public interface IWebService {
  @GET(EndPointURL.PAST_LAUNCH)
  Call<List<LaunchResponse>> fetchPastLaunches();
}

