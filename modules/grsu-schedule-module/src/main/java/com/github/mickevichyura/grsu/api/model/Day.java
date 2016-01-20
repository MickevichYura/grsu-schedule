package com.github.mickevichyura.grsu.api.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.github.mickevichyura.grsu.api.utils.DateFormat;

public class Day {

	private String date;
	private List<Lesson> lessons;

	public String getDate() {
		return date;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		DateFormat.DATE_FORMAT.applyPattern(DateFormat.DAY_OF_WEEK_PATTERN + " " + DateFormat.DATE_FORMAT_PATTERN);
		
		try {
			String formatDate = DateFormat.DATE_FORMAT.format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
			sb.append("*" +  formatDate.toUpperCase() + "*" + "\n");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (Lesson lesson : lessons) {
			if (!(lesson.getTitle().contains("Физическая культура")
					|| lesson.getTitle().contains("Русский язык как иностранный")))
				sb.append(lesson).append("\n");
		}

		return sb.toString();
	}

}
