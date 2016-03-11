package com.alsoftware.josuex09.crawlerbot.structure;

import com.alsoftware.josuex09.crawlerbot.config.BotConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.lang.model.element.Element;
import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by josue on 3/7/16.
 */
public class PageProcessor {

    private FileManager fileManager;
    private UrlValidator urlValidator;

    public PageProcessor(){
        urlValidator = new UrlValidator();
        fileManager = new FileManager();
    }

    public boolean processPage(String url,String user){ // true si fue posible, false si no.
        boolean result = isProccessable(url) && isURL(url);
        if(result){
            try {

                //PROBAR COPYURLTOFILE
                //FileUtils.copyURLToFile(new URL(url),new File("data/a.pdf"),1000,1000);
                Document doc = Jsoup.connect(url).timeout(0).get();
                Elements urls =  doc.select("a");
                String urls_string = "";
                for(org.jsoup.nodes.Element link : urls)  urls_string+= link.absUrl("href")+"\n";
                fileManager.saveNewFile(doc.outerHtml(),urls_string,user);
                //System.out.println(doc.outerHtml());

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }
        return result;
    }


    private boolean isURL(String url){
        return urlValidator.isValid(url);
    }


    private boolean isProccessable(String url){ // Validar si la pagina se puede descargar como texto.
        return url.contains(".html") || url.contains("odt") || url.contains(".pdf");
    }

}
