package br.ufc.deti.ecgweb.utils.ecgformat;

import java.util.ArrayList;
import java.util.List;

public class Signal {
	private List<Double> signal;
	private String name;
	private double timeIncrement;	
	private String timeUnit;
	
	private double origin; //linebase
	private String originUnit;
	
	private double scale ; //gain
	private String scaleUnit;
	
	public double getOrigin() {
		return origin;
	}

	public void setOrigin(double origin) {
		this.origin = origin;
	}

	public String getOriginUnit() {
		return originUnit;
	}

	public void setOriginUnit(String originUnit) {
		this.originUnit = originUnit;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public String getScaleUnit() {
		return scaleUnit;
	}

	public void setScaleUnit(String scaleUnit) {
		this.scaleUnit = scaleUnit;
	}

	public Signal(String name){
		this.name = name;
		signal= new ArrayList<Double>();
	}
	
	public void add(Double data){
		signal.add(data);
	}
	
	public double get(int i){
		return signal.get(i);
	}
	
	public int size(){
		return signal.size();
	}
	
	public List<Double> getSignal() {
		return signal;
	}
	public void setSignal(List<Double> signal) {
		this.signal = signal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getTimeIncrement() {
		return timeIncrement;
	}

	public void setTimeIncrement(double timeIncrement) {
		this.timeIncrement = timeIncrement;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}
}
