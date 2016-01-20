package com.github.mickevichyura.grsu.api.response;

import java.util.List;

import com.github.mickevichyura.grsu.api.model.Day;
import com.google.gson.annotations.SerializedName;

public class DayResponse {

	@SerializedName("days")
	private List<Day> items;

	public List<Day> getDays() {
		return items;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Day day : items) {
			sb.append(day + "\n");
		}

		return sb.toString();
	}

}
