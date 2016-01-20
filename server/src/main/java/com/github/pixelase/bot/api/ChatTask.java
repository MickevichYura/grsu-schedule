package com.github.pixelase.bot.api;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;

public abstract class ChatTask extends Task {

	protected final Chat chat;
	protected Message currentMessage;

	protected static long chatTaskDelay;
	private static long chatTaskTimeout;
	private boolean isRunning;
	private Thread stopWathThread;
	private Long id;
	private Message tempMessage;

	public ChatTask(Chat chat) {
		super();
		this.chat = chat;
		this.id = chat.id();
		isRunning = true;

		stopWathThread = new Thread(new Runnable() {
			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				long difference = 0;
				long checkDelay = 60000;// Check very minute

				while (difference / chatTaskTimeout != 1) {
					sleep(checkDelay);
					difference = System.currentTimeMillis() - startTime;
				}
				isRunning = false;
			}
		});
	}

	protected boolean isRunning() {
		if (!stopWathThread.isAlive()) {
			stopWathThread.setDaemon(true);
			stopWathThread.start();
		}

		return isRunning;
	}

	public Chat getChat() {
		return chat;
	}

	public Message getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(Message currentMessage) {
		this.currentMessage = currentMessage;
	}

	public static long getTaskTimeout() {
		return chatTaskTimeout;
	}

	public static void setTaskTimeout(long taskTimeout) {
		ChatTask.chatTaskTimeout = taskTimeout;
	}

	public static long getChatTaskTimeout() {
		return chatTaskTimeout;
	}

	public static void setChatTaskTimeout(long chatTaskTimeout) {
		ChatTask.chatTaskTimeout = chatTaskTimeout;
	}

	public void InteruptStopWathThread() {
		stopWathThread.interrupt();
	}

	public static long getChatTaskDelay() {
		return chatTaskDelay;
	}

	public static void setChatTaskDelay(long chatTaskDelay) {
		ChatTask.chatTaskDelay = chatTaskDelay;
	}

	protected boolean isMessageUpdated() {
		if (currentMessage.equals(tempMessage)) {
			return false;
		}

		tempMessage = currentMessage;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChatTask))
			return false;
		ChatTask other = (ChatTask) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
