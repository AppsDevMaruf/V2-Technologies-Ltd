package com.marufalam.v2technologiesltd.models;

import com.google.gson.annotations.SerializedName;

public class OptionsItem{

	@SerializedName("referTo")
	private String referTo;

	@SerializedName("value")
	private String value;

	public void setReferTo(String referTo){
		this.referTo = referTo;
	}

	public String getReferTo(){
		return referTo;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
	public String toString() {
		return "OptionsItem{" +
				"referTo='" + referTo + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}