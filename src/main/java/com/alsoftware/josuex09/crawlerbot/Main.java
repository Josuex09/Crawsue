package com.alsoftware.josuex09.crawlerbot;

import com.alsoftware.josuex09.crawlerbot.handlers.CrawlerHandlers;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.TelegramApiException;

import java.io.IOException;


/**
 * Created by josue on 3/7/16.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            //registrar el bot.
            telegramBotsApi.registerBot(new CrawlerHandlers());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }



    }
}