package it.unifi.annotation;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
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

import javax.imageio.ImageIO;

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

	public static LineGraphList readStream(boolean legend,String[] jsonAnnotationFiles, String[] imageDirs, String destinationDir,String[] types) {
		LineGraphList graphList = new LineGraphList();
		try {
			int index = 0;
			int prog = 1;
			for(String type: types) {
				File destFolder = new File(destinationDir+"/"+type);
				if(!destFolder.exists()){
					destFolder.mkdirs();
				}
			}
			
			
	    	for(String json: jsonAnnotationFiles){
	    		String imageDir = imageDirs[index];
		    	String type = types[index];
		    	
		    	index++;
		    	String _destinationDir = destinationDir +"/"+type;
		    	
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
		            		int numLines = annotationLine.getModels().length;
	            			if(image.exists()){
	            				//creo la cartella relativa al numero di linee se non esiste gi�
	            				File destFolder = new File(_destinationDir+"/"+numLines);
	            				if(!destFolder.exists()){
	            					destFolder.mkdirs();
	            				}
	            				String __destinationDir = _destinationDir+"/"+numLines;
			            		if(!legend){
			            			//controllo se la legenda � interna la grafico 
			            			GeneralFigureInfo plotInfo = (GeneralFigureInfo) annotationLine.getGeneral_figure_info();
			            			Bbox plotBox = plotInfo.getPlot_info().getBbox();
			            			Bbox legendBox = plotInfo.getLegend().getBbox();
			            			if(!Bbox.isInternal(plotBox, legendBox)){
			            				graphList.getLineGraph().add(annotationLine);
			            				//sposto l'immagine nella cartella di destinazione e modifico l'indice dell'immagine
			            				String pathTO = __destinationDir + "/" + annotationLine.getImage_index() +".png";
			            				String pathFrom = imageDir+"/"+annotationLine.getImage_index()+".png";
			            				
			            				//faccio il resize dell'immagine 
			            				Image img = ImageIO.read(new File(pathFrom));
			            				BufferedImage tempPNG = resizeImage(img, 600, 400);
			            				pathFrom = imageDir+"/"+annotationLine.getImage_index()+"_resize.png";
			            				File ImgResize = new File(pathFrom);
			            				ImageIO.write(tempPNG, "png", ImgResize);
			            				
			            				copyFile(pathFrom, pathTO);
			            				annotationLine.setImage_index(prog++);
			            				System.out.println("immagine "+pathTO+" creata");
			            			}
			            		}else{
			            			graphList.getLineGraph().add(annotationLine);
			            			//sposto l'immagine nella cartella di destinazione e modifico l'indice dell'immagine
		            				String pathTO = __destinationDir + "/" + annotationLine.getImage_index() +".png";
		            				String pathFrom = imageDir+"/"+annotationLine.getImage_index()+".png";
		            				
		            				//faccio il resize dell'immagine 
		            				Image img = ImageIO.read(new File(pathFrom));
		            				BufferedImage tempPNG = resizeImage(img, 600, 400);
		            				pathFrom = imageDir+"/"+annotationLine.getImage_index()+"_resize.png";
		            				File ImgResize = new File(pathFrom);
		            				ImageIO.write(tempPNG, "png", ImgResize);
		  
		            				copyFile(pathFrom, pathTO);
		            				ImgResize.delete();
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
	
	public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
}

