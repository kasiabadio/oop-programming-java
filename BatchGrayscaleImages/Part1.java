package BatchGrayscaleImages;
import edu.duke.*;
import java.io.File;

/**
 * Write a description of Part1 here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class Part1 {
    
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage);
        for (Pixel p: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            int average = ((inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3);
            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        return outImage;
    }
    
    public void convertImages(){
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            ImageResource imageNew = makeGray(image);
            String fName = image.getFileName();
            String newName = "gray-" + fName;
            imageNew.setFileName(newName);
            imageNew.save();
            
        }
    }
}
