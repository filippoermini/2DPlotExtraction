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


public class Parser {

	public static void readStream(String Index) {
	    try {
	    		FileInputStream isInputStream = new FileInputStream(new File("annotations.json"));
	        JsonReader reader = new JsonReader(new InputStreamReader(isInputStream, "UTF-8"));
	        Gson gson = new GsonBuilder().create();

	        // Read file in stream mode
	        reader.beginArray();
	        while (reader.hasNext()) {
	            // Read data into object model
	            Annotation annotation = gson.fromJson(reader, Annotation.class);
	            if(annotation.getImage_index() == Integer.parseInt(Index)) {
	            		System.out.println("Stream mode: " + annotation.toString());    
	            }
	        }
	        reader.close();
	    } catch (UnsupportedEncodingException ex) {
	        
	    } catch (IOException ex) {
	        
	    }
	}
}
