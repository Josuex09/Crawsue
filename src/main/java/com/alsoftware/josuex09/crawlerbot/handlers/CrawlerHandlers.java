package com.alsoftware.josuex09.crawlerbot.handlers;

import com.alsoftware.josuex09.crawlerbot.config.BotConfig;
import com.alsoftware.josuex09.crawlerbot.structure.PageProcessor;
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

        //Cada vez que llega un nuevo mensaje.
        public void onUpdateReceived(Update update) {
        //Capturar el texto del mensaje.
        String data = update.getMessage().getText();
        //Crear un mensaje de respuesta, ya de error o de logro.
        SendMessage send = new SendMessage();
        send.setChatId(update.getMessage().getChatId().toString());
        if(!update.hasMessage()){        //Validar que sea un texto y no un envio multimedia o mensaje null.
            //Enviar mensaje mostrando el error.
            send.setText(BotConfig.MSG_ERROR_MESSAGE);
        }
        else {
            //Obtener el username de quien envi'o el username
            String user = update.getMessage().getFrom().getUserName();
            //Si logra procesar el url y guardar los links entra aqui
            if (pageProcessor.processPage(data,user)){
                //Enviar mensaje de que se logr'o correctamente el proceso.
                send.setText(BotConfig.SUCCESS_MESSAGE);
            }
            else // Si no, mostrar el error al usuario.
                send.setText(BotConfig.FILE_ERROR_MESSAGE);
        }
        try {
            sendMessage(send);
        } catch (TelegramApiException e) {}
        //System.out.println(data);
    }

    public String getBotUsername() {
        return BotConfig.USERNAME;
    }

}

