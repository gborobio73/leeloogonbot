package com.leeloo.leeloogonbot.messageprocessors;

public class EchoMessageProcessor implements BotMessageProcessor {
	
	private String message;
	public EchoMessageProcessor(String message) {
		this.message = message.toLowerCase();
	}
	@Override
	public String get() {
		if ((message.contains("quien") || message.contains("quién"))&& message.contains("eres") ) {
			return "I am Leeloo Bot";
		}
		return "no entiendo: " +message +". I can only aswer about weather e.g. 'weather Rovaniemi'";
	}

}
