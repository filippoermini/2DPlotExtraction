package it.unifi;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import it.unifi.annotation.Parser;
import it.unifi.domain.AnnotationLine;
import it.unifi.domain.LineGraphList;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;




public class Main {

    public static void main(String[] args){

    	String index = args[0];
    	LineGraphList graphList = Parser.readStream(index);
    	
        //----------------------------------//
    	//  Statistiche sul numero di linee	//
    	//----------------------------------//
    	
    	HashMap<Integer, LineGraphList> lineMap = new HashMap<Integer, LineGraphList>();
    	Iterator<AnnotationLine> it = graphList.getLineGraph().iterator();
    	AnnotationLine annotationLine;
    	while(it.hasNext()) {
    		annotationLine = it.next();
    		if(lineMap.get(annotationLine.getModels().length) == null) {
    			lineMap.put(annotationLine.getModels().length, new LineGraphList());
    		}
    		lineMap.get(annotationLine.getModels().length).getLineGraph().add(annotationLine);
    		
    	}
    	
    	Iterator itMap = lineMap.entrySet().iterator();
    	while (itMap.hasNext()) {
	        HashMap.Entry<Integer,LineGraphList> pair = (HashMap.Entry)itMap.next();
	        System.out.println("Linee " + pair.getKey() + " : " + pair.getValue().getLineGraph().size()+" Grafici");
    	}
    }
}
