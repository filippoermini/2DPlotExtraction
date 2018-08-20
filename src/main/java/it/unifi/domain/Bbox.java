package it.unifi.domain;

public class Bbox {

	private double y;
	private double x;
	private double w;
	private double h;
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getW() {
		return w;
	}
	public void setW(double w) {
		this.w = w;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	
	public static boolean isInternal(Bbox box1,Bbox box2){
		//restituisce vero se c'è un'intersezione tra i due box
		if(
				(box2.x >= box1.x && box2.x < (box1.x + box1.w)) &&
				(box2.y >= box1.y && box2.y < (box1.y + box1.h))){
			return true;
		}
		return false;
	}
	
	
}
