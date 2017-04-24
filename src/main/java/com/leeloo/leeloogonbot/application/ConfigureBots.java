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

import com.leeloo.leeloogonbot.commands.LeelooCommandsHandler;
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
	        try {
//	        	new TelegramBotsApi().registerBot(new LeelooGonBot(factory) );
	        	new TelegramBotsApi().registerBot(new LeelooCommandsHandler() );
	        	
	            logger.info("Leeloo Gon Bot configured.");
	        } catch (TelegramApiException e) {
	        	logger.error("Error when configuring Leeloo Gon Bot.");
	            System.err.println(e.getMessage());
	        }            
        };
    }
}
