package com.dynamo.spacexlaunch.model.resource;

/**
 * Created by dhandk2 on 17,July,2019
 */
public class ErrorDetail {

    private int errorCode;
    private String errorDetail;

    public ErrorDetail(int errorCode, String errorDetail){
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

}
