package ImageInversion;
import edu.duke.*;
import java.io.File;

/**
 * Write a description of Part1 here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class Part1 {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage);
        for (Pixel p: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            p.setRed(255 - inPixel.getRed());
            p.setGreen(255 - inPixel.getGreen());
            p.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }
    
    public void convertImages(){
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            ImageResource imageNew = makeInversion(image);
            String fName = image.getFileName();
            String newName = "inverted-" + fName;
            imageNew.setFileName(newName);
            imageNew.save();
            
        }
    }
}
