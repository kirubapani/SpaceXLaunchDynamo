package com.dynamo.spacexlaunch.model.response;

import com.google.gson.annotations.SerializedName;

public class LaunchFailureDetails{

	@SerializedName("altitude")
	private Object altitude;

	@SerializedName("reason")
	private String reason;

	@SerializedName("time")
	private double time;

	public void setAltitude(Object altitude){
		this.altitude = altitude;
	}

	public Object getAltitude(){
		return altitude;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setTime(double time){
		this.time = time;
	}

	public double getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"LaunchFailureDetails{" + 
			"altitude = '" + altitude + '\'' + 
			",reason = '" + reason + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}