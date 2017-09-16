package it.unifi;

import org.bytedeco.javacpp.opencv_core;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;




public class Main {

    public static void main(String[] args){
        System.out.println("Tesi Specialistica");
        opencv_core.IplImage image = cvLoadImage("graph.png",CV_LOAD_IMAGE_GRAYSCALE);

    }
}
