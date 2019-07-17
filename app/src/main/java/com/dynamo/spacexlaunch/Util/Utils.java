package com.dynamo.spacexlaunch.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.net.ConnectivityManager.TYPE_WIFI;

/**
 * Created by dhandk2 on 15,July,2019
 */
public class Utils {

    // Check network connectivity
    public static String converttoAppDateFormat(String missionDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.'000Z'");
        SimpleDateFormat appDateFormat = new SimpleDateFormat("EEE dd MMM YYYY 'Time' HH:mm 'UTC'");
        try {
            Date date = simpleDateFormat.parse(missionDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            calendar.setTime(date);
            return appDateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = null;

        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        return false;

    }

    public static String getVideoParameter(String videoLink){
      String[] splitLink = videoLink.split("=");
      if(splitLink != null && splitLink.length > 0 && splitLink[1] != null && splitLink[1].length() > 0)
      {
          return splitLink[1];
      }
      return  null;
    }
}
