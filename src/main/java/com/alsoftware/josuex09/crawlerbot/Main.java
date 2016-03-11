package com.alsoftware.josuex09.crawlerbot;

import com.alsoftware.josuex09.crawlerbot.handlers.CrawlerHandlers;
import com.sun.javafx.tk.FileChooserType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.spi.FileTypeDetector;

/**
 * Created by josue on 3/7/16.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new CrawlerHandlers());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

//        //FileUtils.copyURLToFile(new URL("http://www.snee.com/xml/xslt/sample.doc"),new File("data/hola2"),1000,1000);
//        System.out.println(Files.probeContentType(new File("data/tarea0asd1.pdf").toPath()));

    }
}