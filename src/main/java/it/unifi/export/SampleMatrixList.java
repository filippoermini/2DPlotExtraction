package it.unifi.export;

import java.util.ArrayList;
import java.util.Iterator;

public class SampleMatrixList {

	private ArrayList<SampleMatrix> matrixList;

	public SampleMatrixList(){
		this.matrixList = new ArrayList<SampleMatrix>();
	}
	
	public ArrayList<SampleMatrix> getMatrixList() {
		return matrixList;
	}

	public void setMatrixList(ArrayList<SampleMatrix> matrixList) {
		this.matrixList = matrixList;
	}
	
	public SampleMatrix getSampleByIndex(int index){
		Iterator<SampleMatrix> it = matrixList.iterator();
		SampleMatrix sm;
		while(it.hasNext()){
			sm = it.next();
			if(sm.getIndex() == index){
				return sm;
			}
		}
		return null;
	}
}
