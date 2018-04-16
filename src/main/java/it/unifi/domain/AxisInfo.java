package it.unifi.domain;

public class AxisInfo {

	public class Rule{
		
		private Bbox bbox;

		public Bbox getRule() {
			return bbox;
		}

		public void setRule(Bbox rule) {
			this.bbox = rule;
		}
		
		
	}
	private BoxInfo label;
	private ValueInfo major_labels;
	private ValueInfo major_ticks;
	private ValueInfo minor_ticks;
	private Rule rule;
	
	public BoxInfo getLabel() {
		return label;
	}
	public void setLabel(BoxInfo label) {
		this.label = label;
	}
	public ValueInfo getMajor_labels() {
		return major_labels;
	}
	public void setMajor_labels(ValueInfo major_labels) {
		this.major_labels = major_labels;
	}
	public ValueInfo getMajor_ticks() {
		return major_ticks;
	}
	public void setMajor_ticks(ValueInfo major_ticks) {
		this.major_ticks = major_ticks;
	}
	public ValueInfo getMinor_ticks() {
		return minor_ticks;
	}
	public void setMinor_ticks(ValueInfo minor_ticks) {
		this.minor_ticks = minor_ticks;
	}
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	
	
}
