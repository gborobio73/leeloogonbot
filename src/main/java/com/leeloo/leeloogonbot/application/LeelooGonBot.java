package com.leeloo.leeloogonbot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVideo;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.leeloo.leeloogonbot.messageprocessors.BotMessageProcessor;
import com.leeloo.leeloogonbot.messageprocessors.BotMessageProcessorFactory;
import com.leeloo.leeloogonbot.messageprocessors.BotResponse;

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
				BotResponse botResponse = processor.get();
				
    		    try {
    		    	Long chatId = update.getMessage().getChatId();
					if (botResponse.hasText()) {
    		    		SendMessage text = new SendMessage().setChatId(chatId).setText(botResponse.getText());
                        sendMessage(text);	
					}
    		    	
                    if (botResponse.hasVideo()) {
                    	SendVideo video = new SendVideo().setChatId(chatId).setVideo(botResponse.getVideo());
    					sendVideo(video );
					}
                    
                    if (botResponse.hasPhoto()) {
                    	SendPhoto photo = new SendPhoto().setChatId(chatId).setPhoto(botResponse.getPhoto());
    					sendPhoto(photo );
					}
                    
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
}