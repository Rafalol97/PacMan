import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pacmann {
    private BufferedImage postac1 ;
    private String path1,path2;
    public Pacmann() {
       path1="C:\\Users\\rafal\\Desktop\\postac.bmp";
        try {
            postac1= ImageIO.read(new File(path1));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
