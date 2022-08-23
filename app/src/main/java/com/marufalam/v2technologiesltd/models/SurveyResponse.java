package com.marufalam.v2technologiesltd.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyResponse {

	@SerializedName("question")
	private String question;

	@SerializedName("referTo")
	private String referTo;

	@SerializedName("options")
	private List<OptionsItem> options;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	@SerializedName("required")
	private boolean required;

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setReferTo(String referTo){
		this.referTo = referTo;
	}

	public String getReferTo(){
		return referTo;
	}

	public void setOptions(List<OptionsItem> options){
		this.options = options;
	}

	public List<OptionsItem> getOptions(){
		return options;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setRequired(boolean required){
		this.required = required;
	}

	public boolean isRequired(){
		return required;
	}

	@Override
	public String toString() {
		return "SurveyResponse{" +
				"question='" + question + '\'' +
				", referTo='" + referTo + '\'' +
				", options=" + options +
				", id=" + id +
				", type='" + type + '\'' +
				", required=" + required +
				'}';
	}
}