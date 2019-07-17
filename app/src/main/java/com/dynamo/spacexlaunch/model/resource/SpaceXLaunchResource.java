package com.dynamo.spacexlaunch.model.resource;

import androidx.annotation.IntDef;

import com.dynamo.spacexlaunch.model.response.LaunchResponse;

import java.lang.annotation.Retention;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by dhandk2 on 17,July,2019
 */
public class SpaceXLaunchResource {

    public static final int STATUS_SUCCESS = 1, STATUS_FAIL = 2;

    @Retention(SOURCE)
    @IntDef({ STATUS_SUCCESS, STATUS_FAIL})
    @interface Status {
    }
    private ErrorDetail  errorDetail;
    private final int status;
    private List<LaunchResponse> launchResponses;

    public SpaceXLaunchResource(int status, List<LaunchResponse> launchResponses, ErrorDetail errorDetail){
        this.status = status;
        this.errorDetail = errorDetail;
        this.launchResponses = launchResponses;
    }

    public int getStatus() {
        return status;
    }

    public ErrorDetail getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }

    public List<LaunchResponse> getLaunchResponses() {
        return launchResponses;
    }

    public void setLaunchResponses(List<LaunchResponse> launchResponses) {
        this.launchResponses = launchResponses;
    }





}
