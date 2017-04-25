package com.leeloo.leeloogonbot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.logging.BotLogger;

import com.leeloo.leeloogonbot.messageprocessors.BotResponse;

public class WeatherCommand extends BotCommand {
	
	private static final String LOGTAG = "WEATHERCOMMAND";
	private static final Logger logger = LoggerFactory.getLogger(WeatherCommand.class);
	
	public WeatherCommand() {
		super("weather", "Get the weather for any city in the world");
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		String city = "Helsinki";
		
		if (arguments!= null && arguments.length == 1) {
			city = arguments[0];
			if (city.toLowerCase().equals("bcn")) {
				city = "Barcelona";
			}
		}
		String country ="";
		if (arguments!= null && arguments.length == 2) {
			country = arguments[1];
		}
		
		String userName = chat.getUserName();
        if (userName == null || userName.isEmpty()) {
            userName = user.getFirstName() + " " + user.getLastName();
        }
		
        WeatherCommandProcessor weatherCommandProcessor = 
				new WeatherCommandProcessor(userName, city, country);
		
		Runnable task = () -> {
//			try {
//				TimeUnit.SECONDS.sleep(10);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			try{
				BotResponse botResponse = weatherCommandProcessor.get();
				new BotResponseSender().send(absSender, chat.getId().toString(), botResponse);
			} catch (Exception e) {
				//e.printStackTrace();
				BotLogger.error(LOGTAG, e);
				logger.error(e.getMessage());
				//response.setText("Oops! Something is not right. Please, ask me again later.");
			}
			
		};
		
		Thread thread = new Thread(task);
		thread.start();
		
		
	}

}
