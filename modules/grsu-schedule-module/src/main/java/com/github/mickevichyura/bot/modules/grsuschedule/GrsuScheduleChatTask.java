package com.github.mickevichyura.bot.modules.grsuschedule;

import java.util.HashMap;
import java.util.Map;

import com.github.mickevichyura.bot.modules.grsuschedule.persons.Person;
import com.github.mickevichyura.bot.modules.grsuschedule.persons.Student;
import com.github.mickevichyura.bot.modules.grsuschedule.persons.Teacher;
import com.github.pixelase.bot.api.ChatTask;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardHide;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class GrsuScheduleChatTask extends ChatTask {

	String[][] persons;

	private Map<String, Person> personsMap;
	private Person person;

	public GrsuScheduleChatTask(Chat chat) {
		super(chat);
		personsMap = new HashMap<>();
		personsMap.put("Студент", new Student());
		personsMap.put("Преподаватель", new Teacher());
		persons = new String[1][2];
		persons[0] = new String[] { "Студент", "Преподаватель" };
	}

	@Override
	public void run() {
		/*
		 * Example of UserTask implementation
		 */
		while (isRunning()) {
			sleep(chatTaskDelay);

			if (isMessageUpdated()) {
				if (person == null || currentMessage.text().startsWith("/settings")) {
					Keyboard keyboard = new ReplyKeyboardMarkup(persons, true, false, false);
					person = personsMap.get(currentMessage.text());
					if (person == null) {
						bot.sendMessage(currentMessage.chat().id(), "Выберите сторону", ParseMode.Markdown, null, null,
								keyboard);
					}
				}
				if (person != null) {
					if (person.isConfig()) {
						
						int day = 0;
						if (currentMessage.text().startsWith("/tomorrow")) {
							day = 1;
						}
						if (currentMessage.text().startsWith("/yesterday")) {
							day = -1;
						}
						person.sendSchedule(bot, currentMessage, day);
					} else {
						person.config(bot, currentMessage);
					}
				}

				System.out.printf("From %s(%s) task(%s): %s\n", chat.title(), currentMessage.from().username(), this.hashCode(),
						currentMessage.text());
			}
		}
	}

}
