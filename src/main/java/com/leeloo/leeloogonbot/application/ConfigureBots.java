package com.leeloo.leeloogonbot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.leeloo.leeloogonbot.messageprocessors.BotMessageProcessorFactory;

@Configuration
public class ConfigureBots {
	private static final Logger logger = LoggerFactory.getLogger(ConfigureBots.class);
	
	@Autowired
	private BotMessageProcessorFactory factory;
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
        	ApiContextInitializer.init();
	        TelegramBotsApi botsApi = new TelegramBotsApi();
	
	        try {
	        	LeelooGonBot leelooGonBot = new LeelooGonBot(factory);
				botsApi.registerBot(leelooGonBot );
	            logger.info("Bots configured.");
	        } catch (TelegramApiException e) {
	        	logger.error("Error when configuring bots");
	            System.err.println(e.getMessage());
	        }            
        };
    }
}
