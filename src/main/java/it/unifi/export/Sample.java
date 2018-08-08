package it.unifi.export;

import it.unifi.domain.Bbox;
import it.unifi.domain.Point;

public class Sample {

	
	private double xCoordinate;
	private int WindowTop;
	private int WindowBottom;
	private double[] ValueObject;
	private Bbox[]   BoundingBox; 
	private Bbox VerticalWindow;
	private Point[] ValuePoint;
	
	public Sample(int Size) {
		this.BoundingBox = new Bbox[Size];
		this.ValueObject = new double[Size];
		this.VerticalWindow = new Bbox();
		this.ValuePoint = new Point[Size];
	}
	

	public int getWindowTop() {
		return WindowTop;
	}


	public void setWindowTop(int windowTop) {
		WindowTop = windowTop;
	}


	public int getWindowBottom() {
		return WindowBottom;
	}


	public void setWindowBottom(int windowBottom) {
		WindowBottom = windowBottom;
	}


	public Point[] getValuePoint() {
		return ValuePoint;
	}

	public void setValuePoint(Point[] valuePoint) {
		ValuePoint = valuePoint;
	}

	public Bbox getVerticalWindow(){
		return this.VerticalWindow;
	}
	
	public double[] getValueObject() {
		return ValueObject;
	}
	
	public double getXaxis(){
		return this.xCoordinate;
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
	
	public void setPoitAtIndex(int x, int y, int index){
		this.ValuePoint[index] = new Point(x,y);
	}
	
	public void calcVerticalWindow(Bbox xaxis, Bbox yaxis){
		// il valore x,y sarà il valore inferiore di quelli del vettore
		this.xCoordinate = this.BoundingBox[0].getX();
		
		int w = (int) this.BoundingBox[0].getW(); //la larghezza è uguale per tutti i campionamenti
		int y = (int) yaxis.getY();
		int h = (int) yaxis.getH();
		int x = (int) this.xCoordinate - (w/2);
				
		this.VerticalWindow.setH(h);
		this.VerticalWindow.setW(w);
		this.VerticalWindow.setX(x);
		this.VerticalWindow.setY(y);
	}
	
	
}
