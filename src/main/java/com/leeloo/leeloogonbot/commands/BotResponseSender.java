package com.leeloo.leeloogonbot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVideo;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.leeloo.leeloogonbot.messageprocessors.BotResponse;

public class BotResponseSender {

	public void send(AbsSender absSender, String chatId, BotResponse botResponse) throws TelegramApiException{
		if (botResponse.hasText()) {
    		SendMessage text = new SendMessage().setChatId(chatId).setText(botResponse.getText());
    		text.enableHtml(true);
    		absSender.sendMessage(text);	
		}
    	
        if (botResponse.hasVideo()) {
        	SendVideo video = new SendVideo().setChatId(chatId).setVideo(botResponse.getVideo());
        	absSender.sendVideo(video );
		}
        
        if (botResponse.hasPhoto()) {
        	SendPhoto photo = new SendPhoto().setChatId(chatId).setPhoto(botResponse.getPhoto());
        	absSender.sendPhoto(photo );
		}
	}
}
