package com.github.mickevichyura.grsu.api.model;

import java.util.List;

public class Lesson {

	private String timeStart;
	private String timeEnd;
	private Teacher teacher;
	private String type;
	private String title;
	private String address;
	private String room;
	private Group subgroup;

	private List<Group> groups;

	public String getTimeStart() {
		return timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getAddress() {
		return address;
	}

	public String getRoom() {
		return room;
	}

	public Group getSubgroup() {
		return subgroup;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("*------------------------------------------------------------------------*").append("\n");
		sb.append("_" + timeStart + " - " + timeEnd + ": " + "_").append("\n");
		sb.append(title);
		sb.append(" ").append(type);
		sb.append("\n").append(address);
		sb.append(" -- ").append(room);
		if (teacher != null) {
			sb.append("\n").append(teacher);
			

			if (!"".equals(subgroup.getTitle())) {
				sb.append("\n").append(subgroup);
			}
		}
		else{
			sb.append("\nГруппы: ");
			for (Group group : groups) {
				sb.append(group).append("; ");
			}
		}
		return sb.toString();
	}

}
