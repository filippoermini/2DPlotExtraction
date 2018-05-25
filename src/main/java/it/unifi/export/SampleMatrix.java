package it.unifi.export;

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
				//i campionamenti sono sempre uno in pi�
				double value = ((Double) model.getY()[j] + (Double) model.getY()[j+1]) / 2.0; 
				this.sampleArray[j].setBoxAtIndex(model.getBbox()[j], i);
				this.sampleArray[j].setValueAtIndex(value, i);
			}
		}
	}

}
