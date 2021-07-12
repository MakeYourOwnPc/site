package Controller;

import java.io.File;

public interface ImagePaths {
    String defaultPath = "."+File.separator+"images"+File.separator+"none.png";
    String uploadPath = System.getenv("CATALINA_HOME") + File.separator + "images" + File.separator;
    String imagePath = "."+File.separator+"images"+File.separator;
}
