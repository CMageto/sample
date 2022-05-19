package com.example.complaintssample.domain;

public class addtoreportdomain {
	public addtoreportdomain(){

	}
	String type,steps,recommendations,date;

	public addtoreportdomain(String type, String steps, String recommendations, String date) {
		this.type = type;
		this.steps = steps;
		this.recommendations = recommendations;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}
}
