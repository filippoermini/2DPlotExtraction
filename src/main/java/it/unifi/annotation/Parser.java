package it.unifi.annotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.lowagie.text.pdf.codec.Base64.InputStream;

import it.unifi.domain.Annotation;
import it.unifi.domain.AnnotationLine;
import it.unifi.domain.LineGraphList;
import it.unifi.export.SampleMatrix;


public class Parser {

	public static LineGraphList readStream(String fileName) {
		LineGraphList graphList = new LineGraphList();
		try {
	    	
	    	FileInputStream isInputStream = new FileInputStream(new File(fileName));
	        JsonReader reader = new JsonReader(new InputStreamReader(isInputStream, "UTF-8"));
	        Gson gson = new GsonBuilder().create();

	        // Read file in stream mode
	        reader.beginArray();
	        while (reader.hasNext()) {
	            // Read data into object model
	            try {
	            	AnnotationLine annotationLine = gson.fromJson(reader, AnnotationLine.class);
	            	if(annotationLine.getType().contentEquals("line")) {
	            		graphList.getLineGraph().add(annotationLine);
	            		
	            	}
	            }catch(Exception e) {
	            	System.out.println(e.getLocalizedMessage());
	            }
	        }
	        reader.close();
	    } catch (UnsupportedEncodingException ex) {
	        
	    } catch (IOException ex) {
	        
	    }
	    return graphList;
	}
}
