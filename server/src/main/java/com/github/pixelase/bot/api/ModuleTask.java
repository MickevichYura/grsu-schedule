package com.github.pixelase.bot.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.pengrad.telegrambot.model.Chat;

public abstract class ModuleTask extends Task implements Configurable {
	private Map<ChatTask, Future<? extends ChatTask>> chatTasks;
	private ExecutorService chatTasksExecutor;
	protected static long moduleTaskDelay;
	private Class<? extends ChatTask> chatTaskClass;

	public ModuleTask(Class<? extends ChatTask> chatTaskClass, String propFilePath) throws IOException {
		this(new HashMap<ChatTask, Future<? extends ChatTask>>(), Executors.newCachedThreadPool(), chatTaskClass,
				propFilePath);
	}

	private ModuleTask(Map<ChatTask, Future<? extends ChatTask>> chatTasksMap, ExecutorService chatTaskExecutor,
			Class<? extends ChatTask> chatTaskClass, String propFilePath) throws IOException {
		super();
		this.chatTasks = chatTasksMap;
		this.chatTasksExecutor = chatTaskExecutor;
		this.chatTaskClass = chatTaskClass;
		configure(propFilePath);
	}

	public static long getModuleTaskDelay() {
		return moduleTaskDelay;
	}

	public static void setModuleTaskDelay(long moduleTaskDelay) {
		ModuleTask.moduleTaskDelay = moduleTaskDelay;
	}

	@SuppressWarnings("unchecked")
	private void startExecution(Class<? extends ChatTask> chatTaskClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		/*
		 * Temp variable
		 */
		ChatTask currentChatTask = null;

		while (isOk) {
			/*
			 * Fix multithreading
			 */
			sleep(moduleTaskDelay);

			/*
			 * If message is null we can't get it fields, so skip this
			 * iteration;
			 */
			if (commonMessage == null || !isCommonMessageUpdated()) {
				continue;
			}

			/*
			 * Create currentChatTask with some chat from message;
			 */
			currentChatTask = chatTaskClass.getConstructor(Chat.class).newInstance(commonMessage.chat());
			currentChatTask.setCurrentMessage(commonMessage);

			/*
			 * If the task for a given chat is already on our list, skip this
			 * iteration;
			 */
			if (chatTasks.containsKey(currentChatTask)) {
				for (ChatTask task : chatTasks.keySet()) {
					if (task.equals(currentChatTask)) {
						task.setCurrentMessage(commonMessage);
						break;
					}
				}

				Future<? extends ChatTask> future = chatTasks.get(currentChatTask);

				if (future.isDone() || future.isCancelled()) {
					chatTasks.remove(currentChatTask);
				} else {
					continue;
				}
			}

			/*
			 * Add task to the executor and add it to our map
			 */
			chatTasks.put(currentChatTask, (Future<? extends ChatTask>) chatTasksExecutor.submit(currentChatTask));
		}
	}

	@Override
	public void run() {
		try {
			startExecution(chatTaskClass);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
