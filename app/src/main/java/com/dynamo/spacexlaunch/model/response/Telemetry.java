package com.dynamo.spacexlaunch.model.response;

import com.google.gson.annotations.SerializedName;

public class Telemetry{

	@SerializedName("flight_club")
	private Object flightClub;

	public void setFlightClub(Object flightClub){
		this.flightClub = flightClub;
	}

	public Object getFlightClub(){
		return flightClub;
	}

	@Override
 	public String toString(){
		return 
			"Telemetry{" + 
			"flight_club = '" + flightClub + '\'' + 
			"}";
		}
}