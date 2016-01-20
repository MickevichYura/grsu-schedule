package com.github.pixelase.bot.modules.example;

import java.util.Random;

import com.github.pixelase.bot.api.ChatTask;
import com.github.pixelase.bot.utils.emoji.Emoji;
import com.pengrad.telegrambot.model.Chat;

public class ExampleChatTask extends ChatTask {

	public ExampleChatTask(Chat chat) {
		super(chat);
	}

	@Override
	public void run() {
		/*
		 * Example of ChatTask implementation
		 */
		while (isRunning()) {
			sleep(chatTaskDelay);
			Random random = new Random();
			if (isMessageUpdated()) {
				bot.sendMessage(currentMessage.chat().id(),
						Emoji.values()[random.nextInt(Emoji.values().length - 1)].toString(), null, null, null, null);
				System.out.printf("From %s task(%s): %s - %s\n", chat.username(), this.hashCode(), currentMessage.text(), currentMessage.messageId());
			}
		}
	}

}
