package com.leeloo.leeloogonbot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendVideo;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

public class StartCommand extends BotCommand {

    public static final String LOGTAG = "STARTCOMMAND";

    public StartCommand() {
        super("start", "With this command you can start the Bot");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    	
    	Runnable task = () -> {
    		StringBuilder messageBuilder = new StringBuilder();

            String userName = user.getFirstName() + " " + user.getLastName();
            messageBuilder.append("Welcome ").append(userName).append("\n");
            messageBuilder.append("I am Leeloo Bot, a space weather cat. ");
            messageBuilder.append("Type /help to know more about me.");		
            
            SendMessage answer = new SendMessage();
            answer.setChatId(chat.getId().toString());
            answer.setText(messageBuilder.toString());
            
            String videoUrl = "https://media.giphy.com/media/O0oQygeklvnX2/giphy.gif";
            SendVideo video = new SendVideo().setChatId(chat.getId().toString()).setVideo(videoUrl);
            
            try {
                absSender.sendMessage(answer);
                absSender.sendVideo(video);
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
                
            }	
    	};
    	Thread thread = new Thread(task);
		thread.start();
    }
}
