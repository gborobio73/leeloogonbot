package com.leeloo.leeloogonbot.messageprocessors;

public class EchoMessageProcessor implements BotMessageProcessor {
	
	private String message;
	public EchoMessageProcessor(String message) {
		this.message = message;
	}
	@Override
	public String get() {
		return "no entiendo: " +message;
	}

}
