package com.github.mickevichyura.grsu.api.model;

import com.google.gson.annotations.SerializedName;

public class Group {

	private String id;
	private String title;
	
	@SerializedName("students")
	private Integer amountOfStudents;

	public Integer getAmountOfStudents() {
		return amountOfStudents;
	}

	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return title;
	}

}
