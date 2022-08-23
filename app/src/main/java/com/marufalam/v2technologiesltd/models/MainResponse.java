package com.marufalam.v2technologiesltd.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MainResponse{

	@SerializedName("MainResponse")
	private List<MainResponseItem> mainResponse;

	public void setMainResponse(List<MainResponseItem> mainResponse){
		this.mainResponse = mainResponse;
	}

	public List<MainResponseItem> getMainResponse(){
		return mainResponse;
	}

	@Override
 	public String toString(){
		return 
			"MainResponse{" + 
			"mainResponse = '" + mainResponse + '\'' + 
			"}";
		}
}