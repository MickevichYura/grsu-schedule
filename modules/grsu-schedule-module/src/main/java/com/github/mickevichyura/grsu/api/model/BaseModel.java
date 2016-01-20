package com.github.mickevichyura.grsu.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseModel {

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("title")
	@Expose
	private String title;

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	@Override
	public boolean equals(Object obj) {
		return title.equals(obj);
	}

	@Override
	public String toString() {
		return title;
	}
}
