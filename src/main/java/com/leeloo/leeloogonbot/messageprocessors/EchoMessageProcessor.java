package com.leeloo.leeloogonbot.messageprocessors;

public class EchoMessageProcessor implements BotMessageProcessor {
	
	private String message;
	public EchoMessageProcessor(String message) {
		this.message = message.toLowerCase();
	}
	@Override
	public String get() {
		if ( isQuestionAboutMe()) {
			return "I am Leeloo Bot, a weather space cat.";
		}
		return "no entiendo: " +message +". I can only aswer about weather e.g. 'weather Rovaniemi'.";
	}
	
	private boolean isQuestionAboutMe(){
		return ((message.contains("quien") || message.contains("quién")) && message.contains("eres") ||
				message.contains("who") && message.contains("are") && message.contains("you"));
	}

}
