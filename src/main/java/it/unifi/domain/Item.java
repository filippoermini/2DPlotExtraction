package it.unifi.domain;

public class Item {

	public class Preview{
		
		private Bbox bbox;

		public Bbox getBbox() {
			return bbox;
		}

		public void setBbox(Bbox bbox) {
			this.bbox = bbox;
		}
		
	}
		private String model;
		private Preview preview;
		private BoxInfo label;
		
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		
		public Preview getPreview() {
			return preview;
		}
		public void setPreview(Preview preview) {
			this.preview = preview;
		}
		public BoxInfo getLabel() {
			return label;
		}
		public void setLabel(BoxInfo label) {
			this.label = label;
		}
		
		
}
