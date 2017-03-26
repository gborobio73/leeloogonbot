package com.leeloo.leeloogonbot.messageprocessors;

import org.springframework.stereotype.Component;

@Component
public class LanguageParser {

	public boolean isAboutWeather(String message){
		return message.toLowerCase().contains("weather") || 
				message.toLowerCase().contains("tiempo") ||
				message.toLowerCase().contains("wea") ||
				message.toLowerCase().contains("tpo");
	}
}
