package it.unifi.export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class SampleMatrixObject {

	private Gson gson;
	private SampleMatrixList matrixList;
	
	public SampleMatrixObject() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		this.matrixList = new SampleMatrixList();
	}
	
	public void addMatrix(SampleMatrix sample) {
		this.matrixList.getMatrixList().add(sample);
	}
	
	public String serialize() {
		try {
			return gson.toJson(matrixList);
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
