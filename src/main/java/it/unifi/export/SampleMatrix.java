package it.unifi.export;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import it.unifi.domain.AnnotationLine;
import it.unifi.domain.Model;

public class SampleMatrix {
	
	private Sample[] sampleArray;
	
	public SampleMatrix(int sample,int model) {
		sampleArray = new Sample[sample];
		for(int i=0;i<sample;i++) {
			Sample sampleVector = new Sample(model);
			sampleArray[i] = sampleVector;
		}
	}
	
	public void setSampleMatrix(AnnotationLine annotation) {
		//itero sui modelli 
		for(int i=0;i<annotation.getModels().length;i++) {
			Model model = annotation.getModels()[i];
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
					normalizedValue = (value -min) / (max-min);
				}
				this.sampleArray[j].setBoxAtIndex(model.getBbox()[j], i);
				this.sampleArray[j].setValueAtIndex(normalizedValue, i);
			}
		}
	}

}
