package com.github.mickevichyura.bot.modules.grsuschedule;

import java.io.IOException;

import com.github.pixelase.bot.api.ChatTask;
import com.github.pixelase.bot.api.ModuleTask;

public class GrsuScheduleModuleTask extends ModuleTask {

	/*
	 * You must implements this constructor
	 */
	public GrsuScheduleModuleTask(Class<? extends ChatTask> chatTaskClass, String propFilePath) throws IOException {
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
