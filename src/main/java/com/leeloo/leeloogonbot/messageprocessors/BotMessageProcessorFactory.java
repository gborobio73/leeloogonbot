package com.leeloo.leeloogonbot.messageprocessors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leeloo.leeloogonbot.messageprocessors.weather.WeatherMessageProcessor;

@Component
public class BotMessageProcessorFactory {
	private LanguageParser parser;
	
	@Autowired
	public BotMessageProcessorFactory( LanguageParser parser) {
		this.parser = parser;

	}
	public BotMessageProcessor build(String message){
//		if (parser.isACommand(message)) {
//			return new CommandMessageProcessor(message);
//		}
		
		if (parser.isAboutWeather(message)) {
			return new WeatherMessageProcessor(message);
		}
		else
			return new DefaultMessageProcessor(message);
	}
}
