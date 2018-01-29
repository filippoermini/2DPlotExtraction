package it.unifi.domain;

public class Annotation {

	private String type;
	private Object[] models;
	private Object general_figure_info;
	private int image_index;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object[] getModels() {
		return models;
	}
	public void setModels(Object[] models) {
		this.models = models;
	}
	public Object getGeneral_figure_info() {
		return general_figure_info;
	}
	public void setGeneral_figure_info(Object general_figure_info) {
		this.general_figure_info = general_figure_info;
	}
	public int getImage_index() {
		return image_index;
	}
	public void setImage_index(int image_index) {
		this.image_index = image_index;
	}
	
	
}
