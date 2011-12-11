import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


// test
public class ImageReadAndDisplay extends Component{
	
	//fork is bastard!
	
	BufferedImage img;
	BufferedImage img_result;
	Color paint = new Color(42, 255, 0);
	
	 
    public void paint(Graphics g) {
    	int ow = img.getWidth();
    	int oh = img.getHeight();
    	int oldColor = 0;
    	int newColor = 0;
        g.drawImage(img, 0, 0, null);
 
        for(int y = 0; y < oh; y++) {
        	for(int x = 0; x < ow; x++) {
        		if(x == 0 && y == 0) {
        			oldColor = img.getRGB(x, y);
        		}
        		else {
        			newColor = img.getRGB(x, y);
        			double diff = ColorDifference.findDifference(newColor, oldColor);
        			// System.out.println(diff);
        			// use 30 for a big color difference
        			if( diff > 30) { 
        				img_result.setRGB(x, y, paint.getRGB());
        				oldColor = newColor;
        			}
        		}
        	}
        }
        g.drawImage(img_result, img_result.getWidth() + 10, 0, null);
       
    }
 
    public ImageReadAndDisplay(String input) {
       try {
           img = ImageIO.read(new File(input));
           img_result = ImageIO.read(new File(input));
           
       } catch (IOException e) {
    	   System.out.println("Cannot find image name: " + input);
       }
 
    }
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null) * 2 + 10, img.getHeight(null));
       }
    }
    
	public static void main(String [] args) {

        JFrame f = new JFrame("Load Image Sample");
        
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
 
        f.add(new ImageReadAndDisplay("ABC.jpg"));
        f.pack();
        f.setVisible(true);
		
//		// test color convertor
//		LAB lab = ColorConvertor.RGBtoLAB(22, 33, 11);
//		System.out.println("CIE-LAB: ");
//		System.out.println("L: " + lab.getL() + ", should be around 11.152"); 
//		System.out.println("a: " + lab.geta() + ", should be around -9.896");
//		System.out.println("b: " + lab.getb() + ", should be around 11.864");
		
		
	}
}
