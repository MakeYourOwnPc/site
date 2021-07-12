package Controller;

import java.io.File;

public class ImagePaths {
    public static final String defaultPath = "."+ File.separator+"images"+File.separator+"none.png";
    public static final String uploadPath = System.getenv("CATALINA_HOME") + File.separator + "images" + File.separator;
    public static final String imagePath = "."+File.separator+"images"+File.separator;
    public static void checkFile(String path){
        String fileName = path.substring(path.lastIndexOf(File.separator)+1);
        if(fileName.equals("none.png")) return;
        File file = new File(uploadPath+fileName);
        if(file.exists())
            file.delete();
    }
}
