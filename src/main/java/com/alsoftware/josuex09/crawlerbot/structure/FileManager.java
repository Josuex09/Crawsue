package com.alsoftware.josuex09.crawlerbot.structure;

import com.alsoftware.josuex09.crawlerbot.config.BotConfig;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

/**
 * Created by josue on 3/7/16.
 */
public class FileManager {

    private String root_dir;

    public FileManager(){
        root_dir = BotConfig.ROOT_DIR;
    }

    //Guardar el archivo con la informacion de la pagina
    //Ademas guarda los links en otro archivo
    public boolean saveNewFile(String url,String user) {
        int id_actual = 0;
        try {
            //Obtener id nuevo
            id_actual = update_actual_id(user);
            //Rutas donde se guardara los archivos
            String source_path =root_dir+"/"+user+"/"+id_actual+"/"+id_actual+"_Text";
            String links_path = root_dir+"/"+user+"/"+id_actual+"/"+id_actual+"_URLs";

            File file = new File(source_path);
            //Guardar url en archivo
            FileUtils.copyURLToFile(new URL(url), file,0,0);
            //Verificar el tipo de archivo
            String file_type = Files.probeContentType(file.toPath());
            //Si es texto o html
            if(file_type.contains("html")){
                //Sacar los urls dentro de el utilizando Jsoup
                Document doc = Jsoup.parse(FileUtils.readFileToString(file),url);
                Elements urls =  doc.select("a");
                String urls_string = "";
                //Agregar todos los links en un string
                for(org.jsoup.nodes.Element link : urls){
                    urls_string+= link.absUrl("href")+"\n";
                }
               // System.out.println(urls_string);
                //Guardar los links
                FileUtils.writeStringToFile(new File(links_path),urls_string);
            }
            else if(file_type.contains("pdf") || file_type.contains("msword") || file_type.contains("opendocument")){
                //no ha sido implementado buscar links en estos formatos. pdf, doc  , odt
            }
            return true;

        } catch (IOException e) {
            return false;
        }

    }


    //Metodo que actualiza el id actual presente en el archivo ID_ACTUAL de la carpeta del usuario y lo devuelve
    private int update_actual_id(String user) throws IOException {
        int id_actual=1;
        String id_actual_path = root_dir+"/"+user+"/ID_Actual";
        //chequear si existe la carpeta
        File f = new File(root_dir+"/"+user);
        if(f.exists() && f.isDirectory()){ // Si es asi cargo el id actual
            id_actual = Integer.parseInt(FileUtils.readFileToString(new File(id_actual_path)))+1; // aumentar el id
        }
        //guardo el nuevo id actual, si no existe se guardara el 1.
        FileUtils.writeStringToFile(new File(id_actual_path),id_actual+"");
        return id_actual;
    }
}
