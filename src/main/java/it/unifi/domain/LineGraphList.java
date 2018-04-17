package it.unifi.domain;

import java.util.ArrayList;

public class LineGraphList {

	private ArrayList<AnnotationLine> LineGraph;

	public LineGraphList() {
		this.LineGraph = new ArrayList<AnnotationLine>();
	}
	public ArrayList<AnnotationLine> getLineGraph() {
		return LineGraph;
	}

	public void setLineGraph(ArrayList<AnnotationLine> lineGraph) {
		LineGraph = lineGraph;
	}
	
	
}
