package com.github.mickevichyura.grsu.api.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public interface DateFormat {
	static String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
	
	static String DAY_OF_WEEK_PATTERN = "EEEE";
	
	static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN, new Locale("ru", "RU"));
}
