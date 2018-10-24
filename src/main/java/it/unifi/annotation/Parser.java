package it.unifi.annotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.lowagie.text.pdf.codec.Base64.InputStream;

import it.unifi.domain.Annotation;
import it.unifi.domain.AnnotationLine;
import it.unifi.domain.Bbox;
import it.unifi.domain.GeneralFigureInfo;
import it.unifi.domain.LineGraphList;
import it.unifi.export.SampleMatrix;


public class Parser {

	public static LineGraphList readStream(boolean legend,String[] jsonAnnotationFiles, String[] imageDirs, String destinationDir) {
		LineGraphList graphList = new LineGraphList();
		try {
			int index = 0;
			int prog = 1;
			
			File destFolder = new File(destinationDir);
			if(!destFolder.exists()){
				destFolder.mkdirs();
			}
			
	    	for(String json: jsonAnnotationFiles){
	    		String imageDir = imageDirs[index++];
		    	FileInputStream isInputStream = new FileInputStream(new File(json));
		        JsonReader reader = new JsonReader(new InputStreamReader(isInputStream, "UTF-8"));
		        Gson gson = new GsonBuilder().create();
	
		        // Read file in stream mode
		        reader.beginArray();
		        while (reader.hasNext()) {
		            // Read data into object model
		            try {
		            	AnnotationLine annotationLine = gson.fromJson(reader, AnnotationLine.class);
		            	if(annotationLine.getType().contentEquals("line")) {
		            		//controllo l'esistenza dell'immagine
		            		File image = new File(imageDir+"/"+annotationLine.getImage_index()+".png");
	            			if(image.exists()){
	            				
			            		if(!legend){
			            			//controllo se la legenda è interna la grafico 
			            			GeneralFigureInfo plotInfo = (GeneralFigureInfo) annotationLine.getGeneral_figure_info();
			            			Bbox plotBox = plotInfo.getPlot_info().getBbox();
			            			Bbox legendBox = plotInfo.getLegend().getBbox();
			            			if(!Bbox.isInternal(plotBox, legendBox)){
			            				graphList.getLineGraph().add(annotationLine);
			            				//sposto l'immagine nella cartella di destinazione e modifico l'indice dell'immagine
			            				String pathTO = destinationDir + "/" + prog +".png";
			            				String pathFrom = imageDir+"/"+annotationLine.getImage_index()+".png";
			            				copyFile(pathFrom, pathTO);
			            				annotationLine.setImage_index(prog++);
			            				System.out.println("immagine "+pathTO+" creata");
			            			}
			            		}else{
			            			graphList.getLineGraph().add(annotationLine);
			            			//sposto l'immagine nella cartella di destinazione e modifico l'indice dell'immagine
		            				String pathTO = destinationDir + "/" + prog +".png";
		            				String pathFrom = imageDir+"/"+annotationLine.getImage_index()+".png";
		            				copyFile(pathFrom, pathTO);
		            				System.out.println("immagine "+pathTO+" creata");
		            				annotationLine.setImage_index(prog++);
		            				
			            		}
	            			}
		            	}
		            }catch(Exception e) {
		            	System.out.println(e.getLocalizedMessage());
		            }
		        }
		        reader.close();
		        System.out.println("immagini elaborate: "+prog);
	    	}
		   } catch (UnsupportedEncodingException ex) {
			   System.err.println(ExceptionUtils.getStackTrace(ex));
	    } catch (IOException ex) {
	    	System.err.println(ExceptionUtils.getStackTrace(ex));
	    }
	    return graphList;
	}
	
	public static void copyFile(String origin, String destination) throws IOException {
        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        //overwrite the destination file if it exists, and copy
        // the file attributes, including the rwx permissions
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        }; 
        Files.copy(FROM, TO, options);
    }
}
