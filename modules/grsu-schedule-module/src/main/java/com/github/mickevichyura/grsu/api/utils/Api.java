package com.github.mickevichyura.grsu.api.utils;

public class Api {

	public static final String BASE_URL = "http://api.grsu.by/1.x/app1/";

	public static final String LECTURER_LIST = BASE_URL + "getTeachers?extended=false&teacherId=";

	public static final String DEPARTMENT_LIST = BASE_URL + "getDepartments";

	public static final String FACULTY_LIST = BASE_URL + "getFaculties";

	public static final String GROUP_LIST = BASE_URL + "getGroups?departmentId=2&facultyId=3&course=3";

	public static final String groupList(String departmentId, String facultyId, String course) {
		return BASE_URL + "getGroups?departmentId=" + departmentId + "&facultyId=" + facultyId + "&course=" + course;
	}

	public static final String groupSchedule(String groupId) {
		return BASE_URL + "getGroupSchedule?groupId=" + groupId + "&dateStart=";
	}

	public static final String groupSchedule(String groupId, String dateStart, String dateEnd) {
		return BASE_URL + "getGroupSchedule?groupId=" + groupId + "&dateStart=" + dateStart + "&dateEnd=" + dateEnd;
	}

	public static final String TEACHER_SCHEDULE = BASE_URL + "getTeacherSchedule?teacherId=";
	
	public static final String teacherSchedule(String teacherId) {
		return BASE_URL + "getTeacherSchedule?teacherId=" + teacherId + "&dateStart=";
	}

	public static final String teacherSchedule(String teacherId, String dateStart, String dateEnd) {
		return BASE_URL + "getTeacherSchedule?teacherId=" + teacherId + "&dateStart=" + "&dateStart=" + dateStart
				+ "&dateEnd=" + dateEnd;
	}

	public static final String COURSE_LIST = "https://gist.githubusercontent.com/MickevichYura/33b0f33a499c4ae8f295/raw/24766a76d9a9be7b15c305db1b1a857f43f63282/course.json";

}
