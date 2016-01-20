package com.github.mickevichyura.grsu.api.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.github.mickevichyura.grsu.api.utils.HttpUtils;
import com.google.gson.Gson;

public class GetModels {
	public static <Result> Result getModels(String url, Class<Result> clazz) {
		try {
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			try {
				inputStream = HttpUtils.getInputStream(url);
				inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				bufferedReader = new BufferedReader(inputStreamReader, 8192);

				final Result result = new Gson().fromJson(bufferedReader, clazz);
				return result;
			} finally {
				HttpUtils.close(inputStream);
				HttpUtils.close(inputStreamReader);
				HttpUtils.close(bufferedReader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BaseResponse getModels(String url) {
		try {
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			try {
				inputStream = HttpUtils.getInputStream(url);
				inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				bufferedReader = new BufferedReader(inputStreamReader, 8192);

				final BaseResponse response = new Gson().fromJson(bufferedReader, BaseResponse.class);
				return response;
			} finally {
				HttpUtils.close(inputStream);
				HttpUtils.close(inputStreamReader);
				HttpUtils.close(bufferedReader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
