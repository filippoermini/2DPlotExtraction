package it.unifi;

import it.unifi.annotation.Parser;
import it.unifi.domain.AnnotationLine;
import it.unifi.domain.LineGraphList;
import it.unifi.export.SampleMatrix;
import it.unifi.export.SampleMatrixList;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;




public class Main {

	
    public static void main(String[] args){

	    	LineGraphList graphList = Parser.readStream("annotations.json");
	    	
	    //-----------------------------------//
	    	//  Statistiche sul numero di linee	//
	    	//-----------------------------------//
	    
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
	    		SampleMatrixList matrixList = new SampleMatrixList();
	    		HashMap.Entry<Integer,LineGraphList> pair = (HashMap.Entry)itMap.next();
	        System.out.println("Linee " + pair.getKey() + " : " + pair.getValue().getLineGraph().size()+" Grafici");
	        Iterator<AnnotationLine> itGraph = pair.getValue().getLineGraph().iterator();
	    		AnnotationLine annotationLineGraph;
	    		while(itGraph.hasNext()) {
	    			annotationLineGraph = itGraph.next();
	    			//inizializzo la matrice dei campionamenti
		    		int models = annotationLineGraph.getModels().length;
		    		int samples = annotationLineGraph.getModels()[0].getBbox().length;
		    		SampleMatrix sampleMatrix = new SampleMatrix(samples, models);
		    		//popolo la matrice con i campionamenti
		    		sampleMatrix.setSampleMatrix(annotationLineGraph);
		    		matrixList.addMatrix(sampleMatrix);
			}
		    String json = matrixList.serialize();
		    try {
				PrintWriter out = new PrintWriter("serialize"+pair.getKey()+"Line.json");
				out.print(json);
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	}
	}
    
 
}
