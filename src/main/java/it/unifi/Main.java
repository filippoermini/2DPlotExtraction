package it.unifi;

import it.unifi.annotation.Parser;
import it.unifi.domain.AnnotationLine;
import it.unifi.domain.LineGraphList;
import it.unifi.export.SampleMatrix;
import it.unifi.export.SampleMatrixObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;




public class Main {

    public static void main(String[] args){
    	 
    	
    	boolean legend = true;
		String[] jsonFiles = new String[]{};
		String[] imageDir  = new String[]{};
		String[] types	   = new String[]{};
		String destinationDir = "";
    	if(args.length==0){
            System.out.println("Arguments requires\n");
            System.exit(-1);
        }
    	if(args.length > 0){
    		String arg;
    		int i = 0;
 
    		while (i < args.length && args[i].startsWith("-")) {
                arg = args[i++];
                if(arg.contains("-l")){
                	legend = false;
                }
                if(arg.contains("-j")){
                	if (i < args.length){
                		jsonFiles = args[i++].split(",");
                	}
                }
                if(arg.contains("-d")){
                	if (i < args.length){
                		imageDir = args[i++].split(",");
                	}
                }
                if(arg.contains("-D")){
                	if(i < args.length){
                		destinationDir = args[i++];
                	}
                }
                if(arg.contains("-t")){
                	if(i < args.length){
                		types = args[i++].split(",");
                	}
                }
    		}
    	}
	    LineGraphList graphList = Parser.readStream(legend,jsonFiles,imageDir,destinationDir,types);
    	
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
    		SampleMatrixObject matrixList = new SampleMatrixObject();
    		
    		Map.Entry<Integer,LineGraphList> pair = (Map.Entry<Integer, LineGraphList>)itMap.next();
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
