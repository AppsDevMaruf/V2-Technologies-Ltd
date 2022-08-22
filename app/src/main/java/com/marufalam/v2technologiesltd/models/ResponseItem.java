package com.marufalam.v2technologiesltd.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseItem{

	@SerializedName("question")
	private String question;

	@SerializedName("referTo")
	private int referTo;

	@SerializedName("options")
	private List<OptionsItem> options;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	@SerializedName("required")
	private boolean required;

	public String getQuestion(){
		return question;
	}

	public int getReferTo(){
		return referTo;
	}

	public List<OptionsItem> getOptions(){
		return options;
	}

	public int getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public boolean isRequired(){
		return required;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"question = '" + question + '\'' + 
			",referTo = '" + referTo + '\'' + 
			",options = '" + options + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",required = '" + required + '\'' + 
			"}";
		}
}