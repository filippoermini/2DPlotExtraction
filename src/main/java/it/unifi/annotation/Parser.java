package it.unifi.annotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.lowagie.text.pdf.codec.Base64.InputStream;

import it.unifi.domain.Annotation;
import it.unifi.domain.AnnotationLine;
import it.unifi.domain.LineGraphList;


public class Parser {

	public static void readStream(String Index) {
	    try {
	    		FileInputStream isInputStream = new FileInputStream(new File("annotations.json"));
	        JsonReader reader = new JsonReader(new InputStreamReader(isInputStream, "UTF-8"));
	        Gson gson = new GsonBuilder().create();

	        // Read file in stream mode
	        LineGraphList graphList = new LineGraphList();
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
	}
}
