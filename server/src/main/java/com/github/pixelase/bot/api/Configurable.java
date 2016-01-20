package com.github.pixelase.bot.api;

import java.io.IOException;

public interface Configurable {
	void configure(String propFilePath) throws IOException;
}
