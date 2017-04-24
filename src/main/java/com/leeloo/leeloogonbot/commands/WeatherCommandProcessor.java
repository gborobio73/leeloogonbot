package com.leeloo.leeloogonbot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.logging.BotLogger;

import com.leeloo.leeloogonbot.messageprocessors.BotResponse;
import com.leeloo.leeloogonbot.messageprocessors.weather.OpenWeatherResponseDto;

public class WeatherCommandProcessor {
	private static final String LOGTAG = "WEATHERCOMMAND";
	private static final Logger logger = LoggerFactory.getLogger(WeatherCommand.class);

//	private AbsSender absSender;
	private String userName;
//	private Long chatId;
	private String city;
	private String country;

//	public WeatherCommandProcessor(AbsSender absSender, String userName, Long chatId, String city, String country) {
//		this.absSender = absSender;
//		this.userName = userName;
//		this.chatId = chatId;
//		this.city = city;
//		this.country = country;
//	}

	public WeatherCommandProcessor(String userName, String city, String country) {
		this.userName = userName;
		this.city = city;
		this.country = country;
	}

//	public void process() {
//		try {
//			
//	    	RestTemplate restTemplate = new RestTemplate();
//    		String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
//    		//TODO: move key to env vars
//    		String apiKey ="appid=ea7dbc2845bec6c84ba389a678bdf977";
//    		String url = String.format("%s%s%s&%s", baseUrl, city, country, apiKey) ;
//    		OpenWeatherResponseDto wr = restTemplate.getForObject(url, OpenWeatherResponseDto.class);
//    		double tempInCelsius = wr.getMain().getTemp() -273.15;
//    		
//    		StringBuilder messageTextBuilder = new StringBuilder("Hello ").append(userName);
//    		messageTextBuilder.append("\n");
//            messageTextBuilder.append(String.format("Weather for %s %s is ", wr.getName(), wr.getSys().getCountry(), tempInCelsius));
//            messageTextBuilder.append(String.format("<b> %.0f °C </b>, %s", tempInCelsius, wr.getWeather().get(0).getDescription()));
//            
//            SendMessage answer = new SendMessage();
//            answer.setChatId(chatId.toString());
//            answer.enableHtml(true);
//            answer.setText(messageTextBuilder.toString());
//
//            absSender.sendMessage(answer);
//            
//		} catch (Exception e) {
//			//e.printStackTrace();
//			BotLogger.error(LOGTAG, e);
//			logger.error(e.getMessage());
//			//response.setText("Oops! Something is not right. Please, ask me again later.");
//		}		
//	}
	
	public BotResponse get() {
		BotResponse response = new BotResponse();
		try {
			
	    	RestTemplate restTemplate = new RestTemplate();
    		String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
    		//TODO: move key to env vars
    		String apiKey ="appid=ea7dbc2845bec6c84ba389a678bdf977";
    		String url = String.format("%s%s%s&%s", baseUrl, city, country, apiKey) ;
    		OpenWeatherResponseDto wr = restTemplate.getForObject(url, OpenWeatherResponseDto.class);
    		double tempInCelsius = wr.getMain().getTemp() -273.15;
    		
    		StringBuilder messageTextBuilder = new StringBuilder("Hello ").append(userName);
    		messageTextBuilder.append("\n");
            messageTextBuilder.append(String.format("Weather for %s %s is ", wr.getName(), wr.getSys().getCountry(), tempInCelsius));
            messageTextBuilder.append(String.format("<b> %.0f °C, %s</b>", tempInCelsius, wr.getWeather().get(0).getDescription()));
            
            response.setText(messageTextBuilder.toString());            
            
//            SendMessage answer = new SendMessage();
//            answer.setChatId(chatId.toString());
//            answer.enableHtml(true);
//            answer.setText(messageTextBuilder.toString());
//
//            absSender.sendMessage(answer);
            
		} catch (Exception e) {
			//e.printStackTrace();
			BotLogger.error(LOGTAG, e);
			logger.error(e.getMessage());
			//response.setText("Oops! Something is not right. Please, ask me again later.");
		}	
		return response;
	}
}
