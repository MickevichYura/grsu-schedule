package com.github.mickevichyura.grsu.api.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.github.mickevichyura.grsu.api.model.Teacher;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeacherResponse {

	@SerializedName("items")
	@Expose
	private List<Teacher> teachersList;
	
	private final int maxSize = 30;

	public List<Teacher> getItems() {
		return teachersList;
	}

	public String[][] itemsToStringArray() {
		return itemsToStringArray(teachersList.subList(0, maxSize), 3);
	}
	
	public String[][] itemsToStringArray(int m) {
		return itemsToStringArray(teachersList.subList(0, maxSize), m);
	}

	public String[][] itemsToStringArray(List<Teacher> list, int m) {
		if(list.size() > maxSize){
			list = list.subList(0, 0 + maxSize);
		}
		
		if(list.size() == 0){
			list = teachersList;
		}
		String[][] array = new String[(int) Math.ceil(list.size() / (double) m)][];
		int n = array.length;
		
		int count = 0;	
		for (int i = 0; i < n; i++) {
			array[i] = new String[m];
			if (i == n - 1 && list.size() % m != 0) {
				array[i] = new String[list.size() % m];
			}

			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = list.get(count++).toString();
			}
		}
		
		return array;
	}

	public String findId(String fullname) {
		Teacher indexOf = null;

		for (Teacher teacher : teachersList) {
			if (fullname.equals(teacher.getFullname()))
				indexOf = teacher;
		}
		return indexOf == null ? null : indexOf.getId();
	}
	
	public List<Teacher> contains(String name) {
		name = name.toLowerCase();
		List<Teacher> searchList = new ArrayList<>();

		for (Teacher teacher : teachersList) {
			if (teacher.getFullname().toLowerCase().contains(name))
				searchList.add(teacher);
		}
		return searchList.size() == 0 ? teachersList : searchList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Teacher department : teachersList) {
			sb.append(department + "\n");
		}
		
		return sb.toString();
	}
	
}
