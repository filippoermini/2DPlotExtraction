package it.unifi;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.io.File;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;




public class Main {

    public static void main(String[] args){

        //----------//
        //  OpenCV  //
        //----------//

        opencv_core.IplImage image = cvLoadImage("graph.png",CV_LOAD_IMAGE_GRAYSCALE);
        CanvasFrame canvas = new CanvasFrame("Test", 1); // gamma=1
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        canvas.setCanvasSize(100, 100);
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        canvas.showImage(converter.convert(image));

        //----------//
        //  Tess4J  //
        //----------//

        File fileImage = new File("graph.png");
        ITesseract instance = new Tesseract();

        try{
            String result = instance.doOCR(fileImage);
            System.out.println(result);

        }catch(TesseractException e){
            System.out.println(e.getMessage());
        }

    }
}
