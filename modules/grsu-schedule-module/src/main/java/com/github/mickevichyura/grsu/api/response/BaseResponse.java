package com.github.mickevichyura.grsu.api.response;

import java.util.List;

import com.github.mickevichyura.grsu.api.model.BaseModel;
import com.github.pixelase.bot.utils.emoji.Emoji;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

	@SerializedName("items")
	@Expose
	private List<BaseModel> items;

	public List<BaseModel> getItems() {
		return items;
	}

	public String[][] itemsToStringArray(int m) {
		String[][] array = new String[(int) Math.ceil(items.size() / (double) m)][];
		int n = array.length;
		
		int count = 0;	
		for (int i = 0; i < n; i++) {
			array[i] = new String[m];
			if (i == n - 1 && items.size() % m != 0) {
				array[i] = new String[items.size() % m];
			}

			for (int j = 0; j < array[i].length; j++) {
				array[i][j] =  items.get(count++).toString();
			}
		}
		return array;
	}

	public String findId(String title) {
		BaseModel indexOf = null;

		for (BaseModel baseModel : items) {
			if (title.equals(baseModel.getTitle()))
				indexOf = baseModel;
		}
		System.out.println(indexOf);
		return indexOf == null ? null : indexOf.getId();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (BaseModel baseModel : items) {
			sb.append(baseModel + "\n");
		}

		return sb.toString();
	}

}
