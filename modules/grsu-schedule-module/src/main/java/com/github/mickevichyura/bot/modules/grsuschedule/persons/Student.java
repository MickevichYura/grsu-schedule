package com.github.mickevichyura.bot.modules.grsuschedule.persons;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.github.mickevichyura.grsu.api.model.Day;
import com.github.mickevichyura.grsu.api.response.BaseResponse;
import com.github.mickevichyura.grsu.api.response.DayResponse;
import com.github.mickevichyura.grsu.api.response.GetModels;
import com.github.mickevichyura.grsu.api.utils.Api;
import com.github.mickevichyura.grsu.api.utils.DateFormat;
import com.github.pixelase.bot.utils.emoji.Emoji;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardHide;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Student implements Person {

	private boolean isConfig;

	private List<String> settings; // department, faculty, course, group
	private BaseResponse baseResponse;
	
	public Student() {
		super();
		settings = new ArrayList<String>();
	}
	
	@Override
	public boolean isConfig() {
		return isConfig;
	}
	
	@Override
	public void config(TelegramBot bot, Message currentMessage) {
		String url = null;
		String sendMessage = "";
		if (currentMessage.text().startsWith("/settings")) {
			isConfig = false;
			settings.clear();

		}
		if (baseResponse != null) {
			String reply = currentMessage.text();
			if(settings.size() == 2){
				reply = currentMessage.text().replace(Emoji.values()[1].getSecondChar().toString(), "");
				System.out.println(currentMessage.text().indexOf(Emoji.values()[1].toString()));
			}
			String id = baseResponse.findId(reply);
			if (id != null) {
				settings.add(id);
			}
		}

		switch (settings.size()) {
		
		case 1:
			url = Api.FACULTY_LIST;
			sendMessage = "Выберите факультет";
			break;

		case 2:
			url = Api.COURSE_LIST;
			sendMessage = "Выберите курс";
			break;

		case 3:
			url = Api.groupList(settings.get(0), settings.get(1), settings.get(2));
			sendMessage = "Выберите группу";
			break;

		case 4:
			isConfig = true;
			break;

		default:
			url = Api.DEPARTMENT_LIST;
			sendMessage = "Выберите форму обучения";	
			break;
		}

		Keyboard keyboard = null;

		if (!isConfig) {
			baseResponse = GetModels.getModels(url);
			String[][] array = baseResponse.itemsToStringArray(2);
			
			keyboard = new ReplyKeyboardMarkup(array, true, false, false);
			
			if(settings.size() == 2){
				String keycap = "KEYCAP";
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[i].length; j++) {
						String emoji = "";
						try{
							emoji = Emoji.valueOf(keycap + Integer.parseInt(array[i][j].substring(0, 1))).toString();
						}
						catch(Exception e){
							emoji = "";
						}
						array[i][j] =  array[i][j].replace(array[i][j].substring(0, 1), emoji);
					}
				}
			}
		} else {
			keyboard = new ReplyKeyboardHide();
			sendMessage = currentMessage.text();
		}

		bot.sendMessage(currentMessage.chat().id(), sendMessage, ParseMode.Markdown, null, null, keyboard);
		if (isConfig) {
			sendSchedule(bot, currentMessage, 0);
		}
	}

	@Override
	public void sendSchedule(TelegramBot bot, Message currentMessage, int day) {
		TimeUnit t = TimeUnit.MILLISECONDS;
		long daySeconds = t.convert(1L, TimeUnit.DAYS);
		DateFormat.DATE_FORMAT.applyPattern(DateFormat.DATE_FORMAT_PATTERN);	
		Date date = new Date(t.convert(currentMessage.date(), TimeUnit.SECONDS) + daySeconds * day);
		DayResponse groupSchedule = GetModels.getModels(Api.groupSchedule(settings.get(3)) + DateFormat.DATE_FORMAT.format(date),
				DayResponse.class);
		
		if(currentMessage.text().startsWith("/week")){
			for (Day daySchedule : groupSchedule.getDays()) {
				bot.sendMessage(currentMessage.chat().id(), daySchedule.toString(), ParseMode.Markdown, null, null, null);
			}
			return;
		}
		String sсhedule = groupSchedule.getDays().get(0).toString();
		bot.sendMessage(currentMessage.chat().id(), sсhedule.toString(), ParseMode.Markdown, null, null, null);

	}
}
