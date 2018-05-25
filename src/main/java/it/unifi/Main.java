package it.unifi;

import it.unifi.annotation.Parser;
import it.unifi.domain.AnnotationLine;
import it.unifi.domain.LineGraphList;
import it.unifi.export.SampleMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Main {

    public static void main(String[] args){

    	LineGraphList graphList = Parser.readStream("annotations.json");
   
        //----------------------------------//
    	//  Statistiche sul numero di linee	//
    	//----------------------------------//
    	ArrayList<SampleMatrix> matrixList = new ArrayList();
    	HashMap<Integer, LineGraphList> lineMap = new HashMap<Integer, LineGraphList>();
    	Iterator<AnnotationLine> it = graphList.getLineGraph().iterator();
    	AnnotationLine annotationLine;
    	while(it.hasNext()) {
    		annotationLine = it.next();
    		//inizializzo la matrice dei campionamenti
    		int models = annotationLine.getModels().length;
    		int samples = annotationLine.getModels()[0].getBbox().length;
    		SampleMatrix sampleMatrix = new SampleMatrix(samples, models);
    		//popolo la matrice con i campionamenti
    		sampleMatrix.setSampleMatrix(annotationLine);
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
