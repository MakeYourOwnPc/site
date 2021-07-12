package Controller;

import java.io.File;

public interface ImagePaths {
    String defaultPath = "./images/none.png";
    String uploadPath = System.getenv("CATALINA_HOME") + File.separator + "images" + File.separator;
    String imagePath = "./images/";
}
