package com.marufalam.v2technologiesltd.models;

import com.google.gson.annotations.SerializedName;

public class OptionsItem{

	@SerializedName("referTo")
	private int referTo;

	@SerializedName("value")
	private String value;

	public int getReferTo(){
		return referTo;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"OptionsItem{" + 
			"referTo = '" + referTo + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}