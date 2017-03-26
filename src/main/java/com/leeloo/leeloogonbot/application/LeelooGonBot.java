package com.leeloo.leeloogonbot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leeloo.leeloogonbot.messageprocessors.BotMessageProcessor;
import com.leeloo.leeloogonbot.messageprocessors.BotMessageProcessorFactory;

/**
 * Error when trying DI
 Caused by: com.google.inject.ConfigurationException: Guice configuration errors:
10:15:57 AM worker.1 |  1) No implementation for org.telegram.telegrambots.generics.BotSession was bound.
10:15:57 AM worker.1 |    while locating org.telegram.telegrambots.generics.BotSession
 */
//@Service
public class LeelooGonBot extends TelegramLongPollingBot {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigureBots.class);
	private BotMessageProcessorFactory factory;

	public LeelooGonBot(BotMessageProcessorFactory factory) {
		this.factory = factory;
	}
	
    @Override
    public void onUpdateReceived(Update update) {
    	// We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
        	Runnable task = () -> {
        		String messsage = update.getMessage().getText();
				logger.info(String.format("Processing message '%s'", messsage));
        		//TimeUnit.SECONDS.sleep(10);
				BotMessageProcessor processor = factory.build(messsage);
				SendMessage responseMessage = new SendMessage().setChatId(update.getMessage().getChatId());
				responseMessage.setText(processor.get());
    		    try {
                    sendMessage(responseMessage);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
    		    logger.info(String.format("End of processing message '%s'", messsage));
    		};
    		Thread thread = new Thread(task);
    		thread.start();
        }
    }

    @Override
    public String getBotUsername() {
        
        return "leeloo_gon_boot";
    }

    @Override
    public String getBotToken() {
    	return "361073867:AAFOYnERRINBZT0HlXAoDf9ccORGgXkvCfY";
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class WeatherResponse{
    	Object main;
    }
    
}