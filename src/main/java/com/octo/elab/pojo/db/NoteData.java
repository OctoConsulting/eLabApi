package com.octo.elab.pojo.db;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteData {

	// Initial Assessment attributes
	private String requestType;
	private Integer method;
	private Integer conductedBy;
	
	// K Shoe Note attributes
	private Integer type;
	private Integer style;
	private String brandName;
	private String size;
	private String model;
	
	// K Tire Note attributes
	private Integer pov;
	private String dot;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public Integer getConductedBy() {
		return conductedBy;
	}

	public void setConductedBy(Integer conductedBy) {
		this.conductedBy = conductedBy;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getPov() {
		return pov;
	}

	public void setPov(Integer pov) {
		this.pov = pov;
	}

	public String getDot() {
		return dot;
	}

	public void setDot(String dot) {
		this.dot = dot;
	}

}
