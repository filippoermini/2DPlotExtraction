package it.unifi.export;

import it.unifi.domain.Bbox;

public class Sample {

	
	private double[] ValueObject;
	private Bbox[]   BoundingBox;
	
	public Sample(int Size) {
		this.BoundingBox = new Bbox[Size];
		this.ValueObject = new double[Size];
	}

	public double[] getValueObject() {
		return ValueObject;
	}

	public void setValueObject(double[] valueObject) {
		ValueObject = valueObject;
	}

	public Bbox[] getBoundingBox() {
		return BoundingBox;
	}

	public void setBoundingBox(Bbox[] boundingBox) {
		BoundingBox = boundingBox;
	}
	
	public void setValueAtIndex(double value,int index) {
		this.ValueObject[index] = value;
	}
	
	public void setBoxAtIndex(Bbox box, int index) {
		this.BoundingBox[index] = box;
	}
	
	
	
}
