package com.github.pixelase.bot.api;

import java.io.IOException;

public interface Server extends Configurable {
	public void start() throws IOException;

	public void stop();

	public void restart() throws IOException;

}
