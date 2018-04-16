package it.unifi.domain;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;

public class Annotation {

	private String type;
	private Model[] models;
	private GeneralFigureInfo general_figure_info;
	private int image_index;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Model[] getModels() {
		return models;
	}
	public void setModels(Model[] models) {
		this.models = models;
	}
	public Object getGeneral_figure_info() {
		return general_figure_info;
	}
	//public void setGeneral_figure_info(LinkedTreeMap<String, Object> general_figure_info) {
	//	this.general_figure_info = general_figure_info;
	//}
	public int getImage_index() {
		return image_index;
	}
	public void setGeneral_figure_info(GeneralFigureInfo general_figure_info) {
		this.general_figure_info = general_figure_info;
	}
	public void setImage_index(int image_index) {
		this.image_index = image_index;
	}
	private String Casting(Object obj) {
		String out = "";
		try {
			String value = (String) obj;
			out += value ;
		}catch(ClassCastException ex) {
			//provo con il double
			try {
				Double value = (Double) obj;
				out += value ;
			}catch(ClassCastException ex1) {
				//provo con l'arraylist
				try {
					ArrayList<Object> list = (ArrayList<Object>) obj;
					out += "[";
					for(Object el:list) {
						
						out += Casting(el)+",";
					}
					out +="]";
				}catch(ClassCastException ex2) {
					LinkedTreeMap<String, Object> obj1 =  (LinkedTreeMap<String, Object>) obj;
					out += printDeepTreeMap(obj1);
				}
			}
		}
		return out;
	}
	private String printDeepTreeMap(LinkedTreeMap<String, Object> model) {
		String out="";
		for(Map.Entry<String,Object> entry : model.entrySet()) {
			out += entry.getKey()+": ";
			out += Casting(entry.getValue()) + "\n";
		}
		return out;
	}
	
	
}
