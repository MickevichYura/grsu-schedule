package com.github.pixelase.bot.launcher;

import java.io.IOException;

import com.github.mickevichyura.bot.modules.grsuschedule.GrsuScheduleChatTask;
import com.github.mickevichyura.bot.modules.grsuschedule.GrsuScheduleModuleTask;
import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.Server;
import com.github.pixelase.bot.core.BotServerTask;

public class ServerLauncher {
	public static void main(String[] args) throws IOException, InterruptedException {
		ModuleTask grsuScheduleTask = new GrsuScheduleModuleTask(GrsuScheduleChatTask.class, "test_module.properties");
		Server server = new BotServerTask("server.properties", grsuScheduleTask);
		server.start();
	}
}
