package it.unifi.domain;

public class Model {

	private String name;
	private String color;
	private String label;
	private Bbox[] bboxes;
	private Object[] x;
	private Object[] y;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Bbox[] getBbox() {
		return bboxes;
	}
	public void setBbox(Bbox[] bbox) {
		this.bboxes = bbox;
	}
	public Object[] getX() {
		return x;
	}
	public void setX(Object[] x) {
		this.x = x;
	}
	public Object[] getY() {
		return y;
	}
	public void setY(Object[] y) {
		this.y = y;
	}
	
	
}
