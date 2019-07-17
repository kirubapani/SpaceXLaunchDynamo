package com.dynamo.spacexlaunch.Util;

/**
 * Created by dhandk2 on 17,July,2019
 */
public class AppConstants {
    public interface PlayerIntent{
        String ROCKET_NAME ="rocket name";
        String DETAILS ="details";
        String LAUNCH_DATE = "launch date";
        String VIDEO_LINK = "video link";
    }

    public interface  Error{
        String API_ERROR_TITLE ="Unable to Load Data";
        String CONNECTIVTY_TITLE = "No Network Connection";
        String API_ERROR_MSG ="Data not Loading at an moment. Please try after some time";
        String CONNECTIVITY_ERR_MSG= "Please turn on Internet Connection to Load Data";
    }

    public static final String FRAGMENT_TAG="LOADER_FRAGMENT";
}
