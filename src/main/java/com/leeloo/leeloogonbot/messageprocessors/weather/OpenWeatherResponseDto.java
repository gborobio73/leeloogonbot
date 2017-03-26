package com.leeloo.leeloogonbot.messageprocessors.weather;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponseDto{
	private ArrayList<WeatherDto> weather;
	private MainDto main;
	private String name;
	private SysDto sys;
	
	public OpenWeatherResponseDto() {}
	
	public ArrayList<WeatherDto> getWeather() {
		return weather;
	}
	public void setWeather(ArrayList<WeatherDto> weather) {
		this.weather = weather;
	}
	public MainDto getMain() {
		return main;
	}
	public void setMain(MainDto main) {
		this.main = main;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SysDto getSys() {
		return sys;
	}
	public void setSys(SysDto sys) {
		this.sys = sys;
	}
}