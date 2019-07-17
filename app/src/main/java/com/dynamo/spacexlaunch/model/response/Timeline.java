package com.dynamo.spacexlaunch.model.response;

import com.google.gson.annotations.SerializedName;

public class Timeline{

	@SerializedName("webcast_liftoff")
	private int webcastLiftoff;

	public void setWebcastLiftoff(int webcastLiftoff){
		this.webcastLiftoff = webcastLiftoff;
	}

	public int getWebcastLiftoff(){
		return webcastLiftoff;
	}

	@Override
 	public String toString(){
		return 
			"Timeline{" + 
			"webcast_liftoff = '" + webcastLiftoff + '\'' + 
			"}";
		}
}