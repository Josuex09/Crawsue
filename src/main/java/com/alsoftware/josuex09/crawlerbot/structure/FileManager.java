package com.alsoftware.josuex09.crawlerbot.structure;

import com.alsoftware.josuex09.crawlerbot.config.BotConfig;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by josue on 3/7/16.
 */
public class FileManager {

    private String root_dir;

    public FileManager(){
        root_dir = BotConfig.ROOT_DIR;
    }


    public boolean saveNewFile(String content,String urls,String user) throws IOException {
        int id_actual = update_actual_id(user);

        //rutas para guardar los archivos
        String source_path =root_dir+"/"+user+"/"+id_actual+"/"+id_actual+"_Text";
        String links_path = root_dir+"/"+user+"/"+id_actual+"/"+id_actual+"_URLs";

        try {
            FileUtils.writeStringToFile(new File(source_path),content);
            FileUtils.writeStringToFile(new File(links_path),urls);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


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
