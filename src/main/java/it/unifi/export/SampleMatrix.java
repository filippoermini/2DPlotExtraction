package it.unifi.export;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import it.unifi.domain.AnnotationLine;
import it.unifi.domain.Bbox;
import it.unifi.domain.GeneralFigureInfo;
import it.unifi.domain.Model;

public class SampleMatrix {
	
	private Sample[] sampleArray;
	private int index;
	
	public SampleMatrix(int sample,int model) {
		sampleArray = new Sample[sample];
		for(int i=0;i<sample;i++) {
			Sample sampleVector = new Sample(model);
			sampleArray[i] = sampleVector;
		}
	}
	
	public int getIndex(){
		return index;
	}
	
	public Sample[] getSample(){
		return sampleArray;
	}
	
	public void setSampleMatrix(AnnotationLine annotation) {
		if(annotation.getImage_index() == 494){
			System.out.println("cani");
		}
		this.index = annotation.getImage_index();

		Bbox xaxis = null;
		Bbox yaxis = null;
		
		//itero sui modelli 
		for(int i=0;i<annotation.getModels().length;i++) {
			Model model = annotation.getModels()[i];
			GeneralFigureInfo GFI = (GeneralFigureInfo) annotation.getGeneral_figure_info();
			
			xaxis = GFI.getX_axis().getRule().getRule();
			yaxis = GFI.getY_axis().getRule().getRule();
			
			for(int j=0;j<model.getBbox().length;j++) {
				//i campionamenti sono sempre uno in più	
				double max = Double.MIN_VALUE;
				double min = Double.MAX_VALUE;
				for(int k=0;k<model.getY().length;k++) {
					max = (Double) ((Double) model.getY()[k] > max?model.getY()[k]:max);
					min = (Double) ((Double) model.getY()[k] < min?model.getY()[k]:min);
				}
				double normalizedValue;
				if(max == min) {
					//retta orizzontale
					normalizedValue = 1;
				}else {
					double value = ((Double) model.getY()[j] + (Double) model.getY()[j+1]) / 2.0; 
					normalizedValue = value;//(value -min) / (max-min);
				}
				this.sampleArray[j].setBoxAtIndex(model.getBbox()[j], i);
				this.sampleArray[j].setValueAtIndex(normalizedValue, i);
				this.sampleArray[j].setPoitAtIndex((int) model.getBbox()[j].getX(), (int) model.getBbox()[j].getY(), i);
				
			}
		}
		for(int j=0;j<sampleArray.length;j++) {
			this.sampleArray[j].calcVerticalWindow(xaxis,yaxis);
		}
	}

}
