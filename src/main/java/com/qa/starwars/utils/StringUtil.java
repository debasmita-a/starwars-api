package com.qa.starwars.utils;

public class StringUtil {

	public static String getAPIEndPointsFromResponseBody(String s) {
		String[] splitStrings = s.split("/", 5);
		return splitStrings[4];
		}
	}

