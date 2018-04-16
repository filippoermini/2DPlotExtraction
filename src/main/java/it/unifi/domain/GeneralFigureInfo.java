package it.unifi.domain;

public class GeneralFigureInfo {

	public class PlotInfo{
		
		
		private Bbox bbox;

		public Bbox getBbox() {
			return bbox;
		}

		public void setBbox(Bbox bbox) {
			this.bbox = bbox;
		}
	}
	
	public class FigureInfo{
		public class bbox{
			private Bbox bbox;
			
			public Bbox getBbox() {
				return bbox;
			}

			public void setBbox(Bbox bbox) {
				this.bbox = bbox;
			}
		}
		
		private bbox bbox;
		public bbox getBbox() {
			return bbox;
		}

		public void setBbox(bbox bbox) {
			this.bbox = bbox;
		}
	}
	private BoxInfo title;
	private AxisInfo x_axis;
	private Legend legend;
	private ValueInfo x_gridlines;
	private FigureInfo figure_info;
	private ValueInfo y_gridlines;
	private AxisInfo y_axis;
	private PlotInfo plot_info;
	public BoxInfo getTitle() {
		return title;
	}
	public void setTitle(BoxInfo title) {
		this.title = title;
	}
	public AxisInfo getX_axis() {
		return x_axis;
	}
	public void setX_axis(AxisInfo x_axis) {
		this.x_axis = x_axis;
	}
	public Legend getLegend() {
		return legend;
	}
	public void setLegend(Legend legend) {
		this.legend = legend;
	}
	public ValueInfo getX_gridlines() {
		return x_gridlines;
	}
	public void setX_gridlines(ValueInfo x_gridlines) {
		this.x_gridlines = x_gridlines;
	}
	public FigureInfo getFigure_info() {
		return figure_info;
	}
	public void setFigure_info(FigureInfo figure_info) {
		this.figure_info = figure_info;
	}
	public ValueInfo getY_gridlines() {
		return y_gridlines;
	}
	public void setY_gridlines(ValueInfo y_gridlines) {
		this.y_gridlines = y_gridlines;
	}
	public AxisInfo getY_axis() {
		return y_axis;
	}
	public void setY_axis(AxisInfo y_axis) {
		this.y_axis = y_axis;
	}
	public PlotInfo getPlot_info() {
		return plot_info;
	}
	public void setPlot_info(PlotInfo plot_info) {
		this.plot_info = plot_info;
	}
	
	
}
