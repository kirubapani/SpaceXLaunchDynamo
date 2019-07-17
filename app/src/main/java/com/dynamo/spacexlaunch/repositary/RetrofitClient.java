package com.dynamo.spacexlaunch.repositary;

import com.dynamo.spacexlaunch.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dhandk2 on 15,July,2019
 *
 */
public class RetrofitClient {

    private static Retrofit INSTANCE = null;

    public static Retrofit getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return INSTANCE;
    }
}
