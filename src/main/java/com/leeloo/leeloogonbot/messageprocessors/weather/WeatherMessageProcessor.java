package com.leeloo.leeloogonbot.messageprocessors.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.leeloo.leeloogonbot.messageprocessors.BotMessageProcessor;

public class WeatherMessageProcessor implements BotMessageProcessor{
	//private static final Logger logger = LoggerFactory.getLogger(WeatherMessageProcessor.class);
	private String message;
	
	public WeatherMessageProcessor(String message) {
		this.message = message;
		
	}
	
	public String get() {
		String response ="";
		try {
			String city = "Helsinki";
			if (this.message.split(" ").length >1) {
				String optionalCity =this.message.split(" ")[1];
				city = (optionalCity== null || optionalCity.isEmpty()) ? city : optionalCity;
				if (city.toLowerCase().equals("bcn")) {
					city = "Barcelona";
				}
			}			
			String country ="";
			if (this.message.split(" ").length >2) {
				String optionalCountry = this.message.split(" ")[2];
				country = (optionalCountry== null || optionalCountry.isEmpty()) ? "" : ","+optionalCountry;
			}
	    	RestTemplate restTemplate = new RestTemplate();
    		String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
    		//TODO: move key to env vars
    		String apiKey ="appid=ea7dbc2845bec6c84ba389a678bdf977";
    		String url = String.format("%s%s%s&%s", baseUrl, city, country, apiKey) ;
    		OpenWeatherResponseDto wr = restTemplate.getForObject(url, OpenWeatherResponseDto.class);
    		double tempInCelsius = wr.getMain().getTemp() -273.15;
            response = String.format("%s %s, %.0f °C, %s", wr.getName(), wr.getSys().getCountry(), tempInCelsius, 
            		wr.getWeather().get(0).getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	/***
	 * parse also as LinkedHashMap<String, Object> weatherResponse = restTemplate.getForObject(url, LinkedHashMap.class);
	 */
}
