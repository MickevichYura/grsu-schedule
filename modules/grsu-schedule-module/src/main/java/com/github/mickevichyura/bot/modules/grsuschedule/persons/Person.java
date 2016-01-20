package com.github.mickevichyura.bot.modules.grsuschedule.persons;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;

public interface Person {

	void config(TelegramBot bot, Message currentMessage);
	
	void sendSchedule(TelegramBot bot, Message currentMessage, int day);
	
	boolean isConfig();
}
