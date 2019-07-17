package com.dynamo.spacexlaunch.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by dhandk2 on 16,July,2019
 */
public class SpaceXLaunchApp extends Application {

    private static SpaceXLaunchApp spaceXLaunchApp;

    public static SpaceXLaunchApp getInstance(){
        return spaceXLaunchApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        spaceXLaunchApp = this;
    }
}
