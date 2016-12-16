import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;


public class ImageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
            //URL url = new URL("http://wstatic.naver.com/w9/lg_naver_v3.gif");
        	//URL url = new URL("http://upload.enews24.net/News/NewsThumbnail/20160403/62596461.jpg");        	
        	URL url = new URL("http://s.iimg.me/interest.php?url=http://upload.enews24.net/News/NewsThumbnail/20160403/62596461.jpg&rt=120");
        	
        		
            Image image = ImageIO.read(url);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            System.out.println("width = " + width + ", height = " + height);
            
        } catch (Exception e) {
            System.out.println("파일이 없습니다.");
        }
	}

}
