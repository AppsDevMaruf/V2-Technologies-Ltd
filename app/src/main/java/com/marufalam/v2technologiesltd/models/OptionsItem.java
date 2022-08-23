package com.marufalam.v2technologiesltd.models;

import com.google.gson.annotations.SerializedName;

public class OptionsItem{

	@SerializedName("referTo")
	private int referTo;

	@SerializedName("value")
	private String value;

	public void setReferTo(int referTo){
		this.referTo = referTo;
	}

	public int getReferTo(){
		return referTo;
	}

	public void setValue(String value){
		this.value = value;
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