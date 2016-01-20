package com.github.pixelase.bot.modules.example;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.ChatTask;

public class ExampleModuleTask extends ModuleTask {

	/*
	 * You must implements this constructor
	 */
	public ExampleModuleTask(Class<? extends ChatTask> chatTaskClass, String propFilePath) throws IOException {
		super(chatTaskClass, propFilePath);
	}

	/*
	 * You must override this method
	 */
	@Override
	public void configure(String propFilePath) throws IOException {
		// TODO Auto-generated method stub

	}
}
