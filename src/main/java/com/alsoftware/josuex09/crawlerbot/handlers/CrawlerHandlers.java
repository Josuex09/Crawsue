package com.alsoftware.josuex09.crawlerbot.handlers;

import com.alsoftware.josuex09.crawlerbot.config.BotConfig;
import com.alsoftware.josuex09.crawlerbot.structure.PageProcessor;
import org.apache.commons.validator.UrlValidator;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


/**
 * Created by josue on 3/7/16.
 */

public class CrawlerHandlers extends TelegramLongPollingBot {

    private PageProcessor pageProcessor;
    public CrawlerHandlers(){
        super();
        pageProcessor = new PageProcessor();
    }


    @Override
    public String getBotToken() {
        return  BotConfig.TOKEN;

    }


        public void onUpdateReceived(Update update) {
        String data = update.getMessage().getText();
        SendMessage send = new SendMessage();
        send.setChatId(update.getMessage().getChatId().toString());
        if(!update.hasMessage()){        //VALIDAR QUE SEA URL
            //SI ES ERRONEO AVISAR
            send.setText(BotConfig.MSG_ERROR_MESSAGE);
        }
        else {
            String user = update.getMessage().getFrom().getUserName();
            //GUARDAR LINKS
            if (pageProcessor.processPage(data,user)){
                //ENVIAR MSJ DE ACCION COMPLETA
                send.setText(BotConfig.SUCCESS_MESSAGE);
            }
            else // SI LA URL NO ES UN ARCHIVO DE TEXTO PLANO.
                send.setText(BotConfig.FILE_ERROR_MESSAGE);
        }
        try {
            sendMessage(send);
        } catch (TelegramApiException e) {}
        System.out.println(data);
    }

    public String getBotUsername() {
        return BotConfig.USERNAME;
    }

}

