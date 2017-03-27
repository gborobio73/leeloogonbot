package com.leeloo.leeloogonbot.messageprocessors;

public class DefaultMessageProcessor implements BotMessageProcessor {
	
	private String message;
	public DefaultMessageProcessor(String message) {
		this.message = message.toLowerCase();
	}
	@Override
	public BotResponse get() {
		BotResponse response = new BotResponse();
		if ( isQuestionAboutMe() || isHello()) {
			response.setText("Hello, I am Leeloo Bot, a space weather cat. If you want to know about the weather, just type 'weather CITY'.");
			response.setVideo("https://media.giphy.com/media/O0oQygeklvnX2/giphy.gif");
		}
		else{
			response.setText(String.format("No comprendo '%s'. I can only aswer about weather e.g. 'weather Rovaniemi'.", message));
		} 
		return response;
	}
	
	private boolean isHello() {		
		return message.equals("hello") || message.contains("hola");
	}
	private boolean isQuestionAboutMe(){
		return ((message.contains("quien") || message.contains("quién")) && message.contains("eres") ||
				message.contains("who") && message.contains("are") && message.contains("you"));
	}

}
