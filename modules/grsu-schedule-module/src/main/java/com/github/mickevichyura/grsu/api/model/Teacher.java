package com.github.mickevichyura.grsu.api.model;

public class Teacher implements Comparable<Teacher> {

	private String id;
	private String fullname;
	private String post;

	public String getId() {
		return id;
	}

	public String getFullname() {
		return fullname;
	}

	public String getPost() {
		return post;
	}

	@Override
	public String toString() {		
		return fullname;
	}

	@Override
	public int compareTo(Teacher o) {
		return fullname.compareTo(o.getFullname());
	}

}
