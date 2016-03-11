package com.alsoftware.josuex09.crawlerbot.structure;

import org.apache.commons.validator.UrlValidator;


/**
 * Created by josue on 3/7/16.
 */
public class PageProcessor {

    private FileManager fileManager;
    private UrlValidator urlValidator;

    public PageProcessor(){
        urlValidator = new UrlValidator(); //Encargado de validar urls
        fileManager = new FileManager();   //Encargado de manejar los archivos
    }


    public boolean processPage(String url,String user){ // true si fue posible, false si no.
        //Chequear que sea un url valido.
        boolean result = isURL(url);
        if(result){
            System.out.println("Crawling "+url+" for "+ user);
            //Guardar el archivo.
            fileManager.saveNewFile(url,user);

        }
        return result;
    }

    //Retorna true si es un url valido, necesita al menos tener el protocolo, ej: https://www.al-software.com
    private boolean isURL(String url){
        return urlValidator.isValid(url);
    }




}
